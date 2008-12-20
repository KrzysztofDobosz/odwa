package pl.pwr.odwa.server.structure;

import java.util.ArrayList;

/**
 * Class representing structure of single database. Contains detailed
 * informations about its tables. Use getTables() to get detailed tables
 * description
 * 
 */
public class DBStructure
{
	private String name;
	private ArrayList<DBTable> tables;

	/**
	 * Creates empty DBStructure for database of given name
	 * 
	 * @param n database name
	 */
	public DBStructure(String n)
	{
		name = n;
		tables = new ArrayList<DBTable>();
	}

	/**
	 * Creates DBStructure for database of given name with given tables
	 * 
	 * @param n database name
	 * @param tab list of tables
	 */
	public DBStructure(String n, ArrayList<DBTable> tab)
	{
		name = n;
		tables = tab;
	}

	/**
	 * Returns table name
	 * 
	 * @return table name
	 */
	String getName()
	{
		return name;
	}

	/**
	 * Returns list of all tables in database, with detailed description (see
	 * {@link DBTable})
	 * 
	 * @return list of tables in database
	 */
	ArrayList<DBTable> getTables()
	{
		return tables;
	}
}
