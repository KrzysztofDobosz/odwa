package org.pwr.odwa.server.engine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.pwr.odwa.common.result.DBRow;
import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.Axis;
import org.pwr.odwa.common.selection.AxisElement;
import org.pwr.odwa.common.selection.DimensionEl;
import org.pwr.odwa.common.selection.DimensionElSet;
import org.pwr.odwa.common.selection.Measure;
import org.pwr.odwa.common.selection.Method;
import org.pwr.odwa.common.selection.Path;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.server.metadata.Dimension;
import org.pwr.odwa.server.metadata.Level;
import org.pwr.odwa.server.metadata.Member;
import org.pwr.odwa.server.metadata.Meta;
import org.pwr.odwa.server.metadata.Metadata;
import org.pwr.odwa.server.metadata.UID;
import org.pwr.odwa.server.structure.DBField;
import org.pwr.odwa.server.structure.DBStructure;
import org.pwr.odwa.server.structure.DBTable;



/**
 *
 *
 */
public class DBEngine
{
	Connection conn;

	/**
	 * Connects to Database under given URL, as a given user with given password
	 * 
	 * @param url
	 *            database adress
	 * @param user
	 *            user login
	 * @param password
	 */
	public void connect(String url, String user, String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(DriverManager.getDrivers());
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Ends connection with database
	 */
	public void disconnect()
	{
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Executes Query in Database
	 * 
	 * 
	 * @param usrQuery
	 *            User Query
	 * @return Result of User Query
	 */
	public DBResult executeQuery(UserSelection usrQuery)
	{

		try
		{
			// Create MySQL query
			SQLQuery query = new MySQLQuery();

			Metadata meta = new Metadata();
			meta
					.loadMetadata("./opt/metadata.xml");

			Measure meas = usrQuery.getMeasure();
			String measUID = meas.getMeasureUid();
			org.pwr.odwa.server.metadata.Measure aMeasure = (org.pwr.odwa.server.metadata.Measure) meta
					.getElement(new UID(measUID));

			// getting fact Table
			// name---------------------------------------------------------------
			String factTable = aMeasure.getTable();
			query.addToFromClause(factTable); // add fact table to from
												// fields...

			String measure = aMeasure.getFunction() + "(" + factTable + "."
					+ aMeasure.getField() + ")";// metadata module
			query.addMeasureResField(measure);
			// ---------------------------------------------------------------------------------------
			ArrayList<String> whereClauses = new ArrayList<String>();
			ArrayList<Axis> axis = new ArrayList<Axis>();
			axis.add(usrQuery.getColumn());
			axis.add(usrQuery.getRow());
			for (Axis aAxis : axis)
			{
				for (int i = 0; i < aAxis.getAxisElementAmount(); i++)
				{

					AxisElement currEl = aAxis.getAxisElement(i);
					DimensionElSet dimElSet = currEl.getDimensionElSet();
					for (int j = 0; j < dimElSet.getDimensionElAmount(); j++)
					{
						DimensionEl dimEl = dimElSet.getDimensionEl(j);
						Method met = dimEl.getMethod();
						System.out.println(met.getMethodId());
						String methodId = met.getMethodId();
						Path path = dimEl.getPath();

						String dimTable = new String();
						String dimTablePrimaryKey = new String();
						// Dimension table primary key name in fact table
						String foreignKeyName = new String();
						String dimLevel = new String();

						if (methodId == null || !methodId.equals("members"))
						{

							Member aMeta = (Member) meta.getElement(new UID(
									path.getPath()));
							Dimension currDim = (Dimension) meta
									.getElement(aMeta.getDimension());
							dimTable = currDim.getTable();
							dimTablePrimaryKey = currDim.getPrimary();
							// Dimension table primary key name in fact table
							foreignKeyName = currDim.getForeign();

							Level aLevel = (Level) meta.getElement(aMeta
									.getLevel());
							dimLevel = aLevel.getField();
							String memberName = aMeta.getItem();

							whereClauses.add(dimTable + "." + dimLevel + "='"
									+ memberName + "'");

						} else //FIXME: HIERARCHY support!
						{
							Level aLevel = (Level) meta.getElement(new UID(path
									.getPath()));
							Dimension currDim = (Dimension) meta
									.getElement(aLevel.getDimension());
							dimTable = currDim.getTable();
							dimTablePrimaryKey = currDim.getPrimary();
							// Dimension table primary key name in fact table
							foreignKeyName = currDim.getForeign();
							dimLevel = aLevel.getField();

						}
						query.addResField(dimTable + "." + dimLevel);
						query.addToFromClause(dimTable,
								SQLJoinOperator.INNER_JOIN, dimTable + "."
										+ dimTablePrimaryKey, factTable + "."
										+ foreignKeyName);
						query.addToGroupByClause(dimTable + "." + dimLevel);

					}

				}
			}
			if (whereClauses.size() != 0)
			{
				query.addToWhereClause(whereClauses, SQLLogicOperator.OR);
			}

			DimensionElSet aSlice = usrQuery.getSlice();
			for (int i = 0; i < aSlice.getDimensionElAmount(); i++)
			{
				DimensionEl dimEl = aSlice.getDimensionEl(i);
				Method met = dimEl.getMethod();
				System.out.println(met.getMethodId());
				String methodId = met.getMethodId();
				Path path = dimEl.getPath();
				Member aMeta = (Member) meta
						.getElement(new UID(path.getPath()));
				Dimension currDim = (Dimension) meta.getElement(aMeta
						.getDimension());
				String dimTable = currDim.getTable();
				String dimTablePrimaryKey = currDim.getPrimary();
				// Dimension table primary key name in fact table
				String foreignKeyName = currDim.getForeign();

				Level aLevel = (Level) meta.getElement(aMeta.getLevel());
				String dimLevel = aLevel.getField();
				String memberName = aMeta.getItem();
				query.addToFromClause(dimTable, SQLJoinOperator.INNER_JOIN,
						dimTable + "." + dimTablePrimaryKey, factTable + "."
								+ foreignKeyName);
				query.addToWhereClause(dimTable + "." + dimLevel + "='"
						+ memberName + "'", SQLLogicOperator.AND);
			}

			try
			{

				System.out.println(query.getQuery());
				Statement db = conn.createStatement(); // ResultSet result =
				ResultSet result = db.executeQuery(query.getQuery());

				ArrayList<String> colNames = new ArrayList<String>();
				ArrayList<String> fieldTypes = new ArrayList<String>();

				int columnCount = (result.getMetaData().getColumnCount());

				for (int i = 1; i <= columnCount; i++)
				{
					colNames.add(result.getMetaData().getColumnName(i));
					fieldTypes.add(result.getMetaData().getColumnTypeName(i));
				}

				ArrayList<DBRow> rows = new ArrayList<DBRow>();

				while (result.next())
				{
					ArrayList<String> rowObjects = new ArrayList<String>();
					for (int i = 1; i <= columnCount; i++)
					{
						rowObjects.add(result.getObject(i).toString());
					}
					rows.add(new DBRow(rowObjects));
				}
				if (rows.size() == 0)
				{
					return null;
				}

				DBResult res = new DBResult(rows, colNames, fieldTypes, query
						.getQuery());
				return res;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all available databases. First use connect for getting
	 * connections. Returns only databases visible for logged user.
	 * 
	 * @return list of structures of all databases visible for logged user. See
	 *         {@link DBStructure}
	 */
	public ArrayList<DBStructure> getDatabases()
	{
		try
		{
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet catalogs = meta.getCatalogs();

			ArrayList<DBStructure> result = new ArrayList<DBStructure>();

			// adding databases
			while (catalogs.next())
			{
				String catName = catalogs.getString(1);
				ResultSet tables = meta.getTables(catName, null, null, null);

				ArrayList<DBTable> dbTables = new ArrayList<DBTable>();

				// adding tables
				while (tables.next())
				{
					String tabName = tables.getString(3);
					ResultSet fields = meta.getColumns(catName, null, tabName,
							null);

					ArrayList<DBField> dbFields = new ArrayList<DBField>();

					// preparing list of primary keys in table
					ResultSet primaryKeys = meta.getPrimaryKeys(catName, null,
							tabName);
					ArrayList<String> primKeys = new ArrayList<String>();

					while (primaryKeys.next())
					{
						primKeys.add(primaryKeys.getString(4));
					}

					// creating foreign keys list
					ResultSet foreignKeys = meta.getImportedKeys(catName, null,
							tabName);
					ArrayList<String> foreKeys = new ArrayList<String>();
					ArrayList<ForeKeyCont> foreKeysDesc = new ArrayList<ForeKeyCont>();

					while (foreignKeys.next())
					{
						foreKeys.add(foreignKeys.getString(8));
						foreKeysDesc.add(new ForeKeyCont(foreignKeys
								.getString(8), foreignKeys.getString(3),
								foreignKeys.getString(4)));
					}

					// adding fields
					while (fields.next())
					{
						String fieldName = fields.getString(4);
						String foreTable = null;
						String foreColumn = null;

						if (foreKeys.contains(fieldName))
						{
							int i = foreKeys.indexOf(fieldName);
							ForeKeyCont cont = foreKeysDesc.get(i);
							foreTable = cont.getForeTableName();
							foreColumn = cont.getForeColumnName();
						}

						DBField field = new DBField(fieldName, primKeys
								.contains(fieldName), foreKeys
								.contains(fieldName), foreTable, foreColumn,
								fields.getString(6), fields.getString(13),
								fields.getBoolean(11));

						dbFields.add(field);
					}

					DBTable table = new DBTable(tabName, dbFields);
					dbTables.add(table);
				}

				DBStructure struct = new DBStructure(catName, dbTables);
				result.add(struct);
			}
			return result;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}

/**
 * Container for Foreign Key Data, usage only in getStructure()
 */
class ForeKeyCont
{
	private String name;

	private String foreTableName;

	private String foreColumnName;

	/**
	 * Creates new ForeKeyKont
	 * 
	 * @param n
	 *            Foreign Key name
	 * @param t
	 *            Referd table name
	 * @param c
	 *            Refered column name
	 */
	public ForeKeyCont(String n, String t, String c)
	{
		name = n;
		foreTableName = t;
		foreColumnName = c;
	}

	public String getName()
	{
		return name;
	}

	public String getForeTableName()
	{
		return foreTableName;
	}

	public String getForeColumnName()
	{
		return foreColumnName;
	}
}
