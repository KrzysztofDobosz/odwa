package org.pwr.odwa.server.engine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.server.structure.*;

/**
 *
 *
 */
public class DBEngine
{
	Connection conn;

	DBStructure struct = new DBStructure("");

	public void receiveMetadata(String str)
	{
	   System.out.println("ODWAServer: DBEngine: receiveMetadata executed");
	}

	/**
	 * Connects to Database under given URL, as a given user with given password
	 *
	 * @param url database adress
	 * @param user user login
	 * @param password
	 */
	void connect(String url, String user, String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Ends connection with database
	 */
	void disconnect()
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Executes Query in Database
	 *
	 * @param Query User Query
	 * @return Result of User Query
	 */
	public DBResult executeQuery(UserSelection Query)
	{
	   System.out.println("ODWAServer: DBEngine: executeQuery executed");
		return null;
	}

	/**
	 * Returns all available databases. First use connect for getting
	 * connections. Returns only databases visible for logged user.
	 *
	 * @return list of structures of all databases visible for logged user. See
	 *         {@link DBStructure}
	 */
	ArrayList<DBStructure> getDatabases()
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
					ResultSet fields = meta.getColumns(catName, null, tabName, null);

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
						foreKeysDesc.add(new ForeKeyCont(foreignKeys.getString(8),
								foreignKeys.getString(3), foreignKeys.getString(4)));
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
								.contains(fieldName), foreKeys.contains(fieldName),
								foreTable, foreColumn, fields.getString(6), fields
										.getString(13), fields.getBoolean(11));

						dbFields.add(field);
					}

					DBTable table = new DBTable(tabName, dbFields);
					dbTables.add(table);
				}

				DBStructure struct = new DBStructure(catName, dbTables);
				result.add(struct);
			}
			return result;
		}
		catch (SQLException e)
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
	 * @param n Foreign Key name
	 * @param t Referd table name
	 * @param c Refered column name
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
