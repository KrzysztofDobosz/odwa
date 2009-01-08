package org.pwr.odwa.server.structure;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

/**
 * Class representing a single table in database, contains description of each
 * column as {@link DBField}. Use getFields() to get fields description.
 * 
 */
public class DBTable
{
	private String name;
	private ArrayList<DBField> fields;

	/**
	 * Creates empty DBTable for table of given name
	 * 
	 * @param n
	 */
	public DBTable(String n)
	{
		name = n;
		fields = new ArrayList<DBField>();
	}

	/**
	 * Creates DBTable for table of given name with given fields
	 * 
	 * @param n table name
	 * @param field fields list
	 */
	public DBTable(String n, ArrayList<DBField> field)
	{
		name = n;
		fields = new ArrayList<DBField>();
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
	 * Returns list of all columns in table, with detailed description (see
	 * {@link DBField})
	 * 
	 * @return list of columns in table
	 */
	ArrayList<DBField> getFields()
	{
		return null;
	}
}
