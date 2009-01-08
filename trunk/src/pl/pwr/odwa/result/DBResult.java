package pl.pwr.odwa.result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.pwr.odwa.dbtypes.DBFieldDataType;

/**
 * Represents database query result (mostly syntactic sugar)
 * 
 */
public class DBResult
{
	private int next;
	
	private String query;

	private ResultSet result;

	private ArrayList<String> columnNames;

	private ArrayList<String> fieldTypes;

	private int columnCount;

	/**
	 * Creates DB result from native java ResultSet
	 * 
	 * @param DBRes
	 */
	public DBResult(ResultSet DBRes, String query)
	{
		this.query = query;
		this.result = DBRes;
		columnNames = new ArrayList<String>();
		fieldTypes = new ArrayList<String>();
		try
		{
			columnCount = (this.result.getMetaData().getColumnCount());
			for (int i = 1; i <= getColumnCount(); i++)
			{
				columnNames.add(this.result.getMetaData().getColumnName(i));
				fieldTypes.add(this.result.getMetaData().getColumnTypeName(i));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		next = 0;
	}

	/**
	 * Returns next row of the result
	 * 
	 * @return
	 */
	DBRow fetchRow()
	{
		ArrayList<Object> rowObjects = new ArrayList<Object>();
		try
		{
			if (result.next())

			{
				for (int i = 1; i <= getColumnCount(); i++)
				{
					rowObjects.add(result.getObject(i));
				}
				return new DBRow(rowObjects);
			} else
			{
				return null;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns dataType for the column
	 * 
	 * @param num Column index
	 *            
	 * @return
	 */
	DBFieldDataType getType(int num)
	{
		String[] textTypes =
		{ "BLOB", "CHAR ", "BINARY", "VARBINARY", "VARCHAR", "TEXT", "SET",
				"ENUM", "LONGVARBINARY", "LONGVARCHAR" };
		String[] numTypes =
		{ "INT", "TINYINT ", "SMALLINT", "MEDIUMINT", "BIGINT", "FLOAT",
				"DOUBLE", "REAL", "NUMERIC", "INTEGER", "DECIMAL", "BOOLEAN" };
		String[] dateTypes =
		{ "DATETIME", "DATE", "TIMESTAMP", "TIME", "YEAR" };

		for (int i = 0; i < textTypes.length; i++)
		{
			if (fieldTypes.get(num).equals(textTypes[i]))
				return DBFieldDataType.StringType;
		}
		for (int i = 0; i < numTypes.length; i++)
		{
			if (fieldTypes.get(num).equals(numTypes[i]))
				return DBFieldDataType.IntegerType;
		}
		for (int i = 0; i < dateTypes.length; i++)
		{
			if (fieldTypes.get(num).equals(dateTypes[i]))
				return DBFieldDataType.DateType;
		}
		return null;
	}

	/**
	 * Returns Type of the field identified by name
	 * 
	 * @param name field name   
	 * @return 
	 */
	DBFieldDataType getType(String name)
	{
		int num = columnNames.indexOf(name);
		return getType(num);
	}

	
	/**
	 * Returns Result ArrayList
	 * 
	 * @return
	 */
	ArrayList<DBRow> fetchArray()
	{
		return null;
	}


	/**
	 * @return the columnCount
	 */
	public int getColumnCount()
	{
		return columnCount;
	}
	
	/**
	 * Returns name of the column
	 * @param num number of the column
	 * @return
	 */
	public String getColumnName(int num)
	{
		return columnNames.get(num);
	}


	/**
	 * @return the query
	 */
	public String getQuery()
	{
		return query;
	}
}
