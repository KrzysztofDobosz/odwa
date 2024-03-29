package org.pwr.odwa.server.engine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
import org.pwr.odwa.server.metadata.Child;
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
 * Class responsible for handling database queries. It can translate
 * UserSelection into query and then execute it on database, finally returning
 * ready-to-serialize DBResult instance. It also manages db connections.
 * 
 * @author Maciek Kupczak macku30@gmail.com
 * @author Mateusz Lis mateusz.lis@gmail.com
 * 
 */
public class DBEngine
{
	protected Metadata meta;
	protected Connection conn;
	
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
			throws SQLException, ClassNotFoundException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(DriverManager.getDrivers());
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/**
	 * Ends connection with database
	 */
	public void disconnect() throws SQLException
	{
		try
		{
			if (conn != null)
				conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Returns all available databases. First use connect for getting
	 * connections. Returns only databases visible for logged user.
	 * 
	 * @return list of structures of all databases visible for logged user. See
	 *         {@link DBStructure}
	 */
	public ArrayList<DBStructure> getDatabases() throws SQLException
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
			throw e;
		}
	}
	
	/**
	 * Executes Query in Database. In fact function does three important things: -
	 * it translates UserSelection to SQL code, then it executes the query on
	 * database engine, and finally translates the result into DBResult
	 * Ready-To-Serialize-And-Send form.
	 * 
	 * It is really heavy commented, however to understand/edit this function
	 * you need to know exactly how UserSelection works and how it is connected
	 * with metadata (classes from org.odwa.server.metadata tree are especially
	 * important because most of the code is dealing with proper type (is it
	 * dimension or set of dimensions? Is it hierarchy or just one element...))
	 * 
	 * @param usrQuery
	 *            User Query
	 * @return Result of User Query
	 */
	public DBResult executeQuery(UserSelection usrQuery) throws SQLException,
			Exception
	{
		
		try
		{
			// Create MySQL query
			SQLQuery query = new MySQLQuery();
			WhereClause finalWhereClause = new MySQLWhereClause();
			
			meta = new Metadata();
			meta.loadMetadata(System.getenv("ODWA_METADATA"));
			
			Measure meas = usrQuery.getMeasure();
			String measUID = meas.getMeasureUid();
			org.pwr.odwa.server.metadata.Measure aMeasure = (org.pwr.odwa.server.metadata.Measure) meta
					.getElement(new UID(measUID));
			
			// getting fact Table
			// name
			String factTable = aMeasure.getTable();
			query.addToFromClause(factTable); // add fact table to from
			// fields...
			
			String measure = aMeasure.getFunction() + "(" + factTable + "."
					+ aMeasure.getField() + ")";// metadata module
			query.addMeasureResField(measure);
			
			ArrayList<Axis> axis = new ArrayList<Axis>();
			axis.add(usrQuery.getRow());
			axis.add(usrQuery.getColumn());
			
			ArrayList<String> resultRows = new ArrayList<String>();
			ArrayList<String> resultCols = new ArrayList<String>();
			int rowscols = 0;
			
			// iterate over every axis...
			for (Axis aAxis : axis)
			{
				// hash map of where clauses
				// this was Macieks idea for preventing mixing up different
				// where clauses, we store them all in hash map indexed by
				// different
				// keys so we can finally (when all are finnished) create one
				// big where clause.
				HashMap<String, WhereClause> whereClausesMap = new HashMap<String, WhereClause>();
				
				// ...subaxis and...
				for (int i = 0; i < aAxis.getAxisElementAmount(); i++)
				{
					
					AxisElement currEl = aAxis.getAxisElement(i);
					DimensionElSet dimElSet = currEl.getDimensionElSet();
					// ... now iterate over dimensions
					for (int j = 0; j < dimElSet.getDimensionElAmount(); j++)
					{
						DimensionEl dimEl = dimElSet.getDimensionEl(j);
						Method met = dimEl.getMethod();
						String methodId = met.getMethodId();
						Path path = dimEl.getPath();
						
						String dimTable = new String();
						String dimTablePrimaryKey = new String();
						// Dimension table primary key name in fact table
						String foreignKeyName = new String();
						String dimLevel = new String();
						
						String memberName = new String();
						System.out.println(path.getPath());
						// meta.getElement(uid)
						
						// check if we have to deal with hierarchy
						// if so, we'll have to iterate on every level ancestors
						// otherwise we just consider current element
						if ((meta.getElement(new UID(path.getPath()))
								.getClass() == (new Child()).getClass()))
						{
							// <where clause> start
							WhereClause whereClause = new MySQLWhereClause();
							
							// </where clause>
							
							Child aChild = (Child) meta.getElement(new UID(path
									.getPath()));
							ArrayList<UID> parentsUIDs = aChild.getPath();
							
							// add parents to result clause in select
							for (UID parentUID : parentsUIDs)
							{
								Member parent = (Member) meta
										.getElement(parentUID);
								Dimension currDim = (Dimension) meta
										.getElement(parent.getDimension());
								dimTable = currDim.getTable();
								dimTablePrimaryKey = currDim.getPrimary();
								// dimElDDimension table primary key name in
								// fact
								// table
								foreignKeyName = currDim.getForeign();
								
								Level aLevel = (Level) meta.getElement(parent
										.getLevel());
								dimLevel = aLevel.getField();
								memberName = parent.getItem();
								
								whereClause.addStr(dimTable + "." + dimLevel
										+ "='" + memberName + "'",
										SQLLogicOperator.AND);
								
								query.addResField(dimTable + "." + dimLevel);
								
								query.addToFromClause(dimTable,
										SQLJoinOperator.INNER_JOIN, dimTable
												+ "." + dimTablePrimaryKey,
										factTable + "." + foreignKeyName);
								query.addToGroupByClause(dimTable + "."
										+ dimLevel);
								
								if (rowscols == 0)
								{ // add to rows
									resultRows.add(memberName);
								} else
								{ // add to cols
									resultCols.add(memberName);
								}
							}
							
							// add current element to select clause
							Member child = aChild.getMember();
							
							Dimension currDim = (Dimension) meta
									.getElement(child.getDimension());
							dimTable = currDim.getTable();
							dimTablePrimaryKey = currDim.getPrimary();
							// Dimension table primary key name in fact
							// table
							foreignKeyName = currDim.getForeign();
							
							Level aLevel = (Level) meta.getElement(child
									.getLevel());
							dimLevel = aLevel.getField();
							memberName = child.getItem();
							
							// where clause
							whereClause.addStr(dimTable + "." + dimLevel + "='"
									+ memberName + "'", SQLLogicOperator.AND);
							String dimHierarchy = child.getHierarchy()
									.get_uid();
							String mapKey = dimTable + "." + dimHierarchy;
							if (whereClausesMap.containsKey(mapKey))
							{
								WhereClause aWhereClause = whereClausesMap
										.get(mapKey);
								aWhereClause.addWhereClause(whereClause,
										SQLLogicOperator.OR);
							} else
							{
								whereClausesMap.put(mapKey, whereClause);
							}
							
							if (rowscols == 0)
							{ // add to rows
								resultRows.add(memberName);
							} else
							{ // add to cols
								resultCols.add(memberName);
							}
							
						} else
						{
							
							if (methodId == null || !methodId.equals("members"))
							{
								
								Member aMeta = (Member) meta
										.getElement(new UID(path.getPath()));
								
								Dimension currDim = (Dimension) meta
										.getElement(aMeta.getDimension());
								dimTable = currDim.getTable();
								dimTablePrimaryKey = currDim.getPrimary();
								// Dimension table primary key name in fact
								// table
								foreignKeyName = currDim.getForeign();
								
								Level aLevel = (Level) meta.getElement(aMeta
										.getLevel());
								dimLevel = aLevel.getField();
								memberName = aMeta.getItem();
								
								String mapKey = dimTable + "." + dimLevel;
								if (whereClausesMap.containsKey(mapKey))
								{
									WhereClause whereClause = whereClausesMap
											.get(mapKey);
									whereClause.addStr(dimTable + "."
											+ dimLevel + "='" + memberName
											+ "'", SQLLogicOperator.OR);
									System.out.println("272:"
											+ whereClause.getClause());
								} else
								{
									WhereClause whereClause = new MySQLWhereClause();
									System.out.println("276:"
											+ whereClause.getClause());
									
									whereClause.addStr(dimTable + "."
											+ dimLevel + "='" + memberName
											+ "'", SQLLogicOperator.OR);
									whereClausesMap.put(mapKey, whereClause);
								}
								
								if (rowscols == 0)
								{ // add to rows
									resultRows.add(memberName);
								} else
								{ // add to cols
									resultCols.add(memberName);
								}
								
							} else
							
							{
								Level aLevel = (Level) meta.getElement(new UID(
										path.getPath()));
								Dimension currDim = (Dimension) meta
										.getElement(aLevel.getDimension());
								dimTable = currDim.getTable();
								dimTablePrimaryKey = currDim.getPrimary();
								// Dimension table primary key name in fact
								// table
								foreignKeyName = currDim.getForeign();
								dimLevel = aLevel.getField();
								
								ArrayList<UID> members = aLevel.getMembers();
								for (UID member : members)
								{
									Member lvlMember = (Member) meta
											.getElement(member);
									memberName = lvlMember.getItem();
									
									if (rowscols == 0)
									{ // add to rows
										resultRows.add(memberName);
									} else
									{ // add to cols
										resultCols.add(memberName);
									}
								}
							}
						}
						query.addResField(dimTable + "." + dimLevel);
						
						query.addToFromClause(dimTable,
								SQLJoinOperator.INNER_JOIN, dimTable + "."
										+ dimTablePrimaryKey, factTable + "."
										+ foreignKeyName);
						query.addToGroupByClause(dimTable + "." + dimLevel);
						
					}
					// dealing with Where clauses hash map mentioned before...
					if (whereClausesMap.size() != 0)
					{
						Collection<WhereClause> whereCol = whereClausesMap
								.values();
						for (WhereClause whereClauses : whereCol)
						{
							finalWhereClause.addWhereClause(whereClauses,
									SQLLogicOperator.OR);
						}
						
					}
				}
				rowscols = 1;
			}
			
			DimensionElSet aSlice = usrQuery.getSlice();
			analyzeSlice(aSlice, query, finalWhereClause, factTable);
			
			query.addToWhereClause(finalWhereClause);
			try
			{
				return executeQuery(query, resultRows, resultCols);
			} catch (SQLException e)
			{
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Deals with the Slice part of the UserSelection query. Works on reference
	 * of SQLQuery and WhereClause objects so it can be used further
	 * 
	 * @param aSlice
	 * @param query
	 * @param whereClause
	 * @param factTable
	 */
	protected void analyzeSlice(DimensionElSet aSlice, SQLQuery query,
			WhereClause whereClause, String factTable)
	{
		int amount = aSlice.getDimensionElAmount();
		for (int i = 0; i < amount; i++)
		{
			DimensionEl dimEl = aSlice.getDimensionEl(i);
			Path path = dimEl.getPath();
			if ((meta.getElement(new UID(path.getPath())).getClass() == (new Child())
					.getClass()))
			{
				String dimTable;
				String dimTablePrimaryKey;
				String foreignKeyName;
				String dimLevel;
				String memberName;
				
				Child aChild = (Child) meta.getElement(new UID(path.getPath()));
				ArrayList<UID> parentsUIDs = aChild.getPath();
				
				// add parents to result clause in select
				for (UID parentUID : parentsUIDs)
				{
					Member parent = (Member) meta.getElement(parentUID);
					Dimension currDim = (Dimension) meta.getElement(parent
							.getDimension());
					dimTable = currDim.getTable();
					dimTablePrimaryKey = currDim.getPrimary();
					// dimElDDimension table primary key name in
					// fact
					// table
					foreignKeyName = currDim.getForeign();
					
					Level aLevel = (Level) meta.getElement(parent.getLevel());
					dimLevel = aLevel.getField();
					memberName = parent.getItem();
					query.addToFromClause(dimTable, SQLJoinOperator.INNER_JOIN,
							dimTable + "." + dimTablePrimaryKey, factTable
									+ "." + foreignKeyName);
					whereClause.addStr(dimTable + "." + dimLevel + "='"
							+ memberName + "'", SQLLogicOperator.AND);
					
				}
				
				Member child = aChild.getMember();
				
				Dimension currDim = (Dimension) meta.getElement(child
						.getDimension());
				dimTable = currDim.getTable();
				dimTablePrimaryKey = currDim.getPrimary();
				
				foreignKeyName = currDim.getForeign();
				
				Level aLevel = (Level) meta.getElement(child.getLevel());
				dimLevel = aLevel.getField();
				memberName = child.getItem();
				
				query.addToFromClause(dimTable, SQLJoinOperator.INNER_JOIN,
						dimTable + "." + dimTablePrimaryKey, factTable + "."
								+ foreignKeyName);
				whereClause.addStr(dimTable + "." + dimLevel + "='"
						+ memberName + "'", SQLLogicOperator.AND);
				
			} else
			{
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
				whereClause.addStr(dimTable + "." + dimLevel + "='"
						+ memberName + "'", SQLLogicOperator.AND);
			}
		}
	}
	
	/**
	 * Method executes query in database and translates SQLResult type into type
	 * that can be sent to client app (ex. GWT or JSP)
	 * 
	 * @param query -
	 *            query that is to be executed
	 * @param resultRows -
	 *            row names ( row names and col names are needed for dispaying
	 *            it.
	 * @param resultCols -
	 *            col names
	 * @return
	 */
	protected DBResult executeQuery(SQLQuery query,
			ArrayList<String> resultRows, ArrayList<String> resultCols)
			throws SQLException
	{
		try
		{
			
			System.out.println(query.getQuery()); // debug
			
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
					.getQuery(), resultRows, resultCols);
			
			return res;
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw e;
		}
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
