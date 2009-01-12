package org.pwr.odwa.server.structure;

import org.pwr.odwa.common.dbtypes.DBFieldDataType;

import java.util.ArrayList;

/**
 * Klasa reprezentuje strukture pojedynczego pola w tabeli
 *
 */
public class DBField
{
	private String name;
	private boolean primaryKey;
	private boolean foreignKey;
	private String forTableName;
	private String forColumnName;
	private String dataType;
	private String defVal;
	private boolean nullable;

	/**
	 * Creates new DB field with given parameters
	 *
	 * @param n Field name
	 * @param pK True if field is a primary key
	 * @param fK True if table is a foreign key
	 * @param fTN Name of table to which refers field, should be null if field
	 *        isn't a foreign key, cannot be null if field is foreign key
	 * @param fCN Name of column to which refers field, should be null if field
	 *        isn't a foreign key, cannot be null if field is foreign key
	 * @param dT Type of data in field (see java.sql.Types)
	 * @param dV Default value
	 * @param nA True if field is nullable
	 */
	public DBField(String n, boolean pK, boolean fK, String fTN, String fCN,
			String dT, String dV, boolean nA)
	{
		name = n;
		primaryKey = pK;
		foreignKey = fK;
		forTableName = fTN;
		forColumnName = fCN;
		dataType = dT;
		defVal = dV;
		nullable = nA;
	}

	/**
	 * Returns field name
	 *
	 * @return Field name
	 */
	String getName()
	{
		return name;
	}

	/**
	 * Return true if the field is primary key
	 *
	 * @return true if primary key
	 */
	boolean isPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * Return true if the field is foreign key
	 *
	 * @return true if foreign key
	 */
	boolean isForeignKey()
	{
		return foreignKey;
	}

	/**
	 * Returns name of referd table, or null if field isn`t a foreign key
	 *
	 * @return name of refered table
	 */
	String getRefTable()
	{
		return forTableName;
	}

	/**
	 * Returns name of referd field(column), or null if field isn`t a foreign key
	 *
	 * @return name of refered field
	 */
	String getRefColumn()
	{
		return forColumnName;
	}

	/**
	 * Returns type of data storeid in this field
	 *
	 * @return Data type
	 */
	DBFieldDataType getDataType()
	{
		String[] textTypes = { "BLOB", "CHAR ", "BINARY", "VARBINARY", "VARCHAR",
				"TEXT", "SET", "ENUM", "LONGVARBINARY", "LONGVARCHAR" };
		String[] numTypes = { "INT", "TINYINT ", "SMALLINT", "MEDIUMINT",
				"BIGINT", "FLOAT", "DOUBLE", "REAL", "NUMERIC", "INTEGER",
				"DECIMAL", "BOOLEAN" };
		String[] dateTypes = { "DATETIME", "DATE", "TIMESTAMP", "TIME", "YEAR" };

		for (int i = 0; i<textTypes.length; i++)
		{
			if(dataType.equals(textTypes[i]))
				return DBFieldDataType.StringType;
		}
		for (int i = 0; i<numTypes.length; i++)
		{
			if(dataType.equals(numTypes[i]))
				return DBFieldDataType.IntegerType;
		}
		for (int i = 0; i<dateTypes.length; i++)
		{
			if(dataType.equals(dateTypes[i]))
				return DBFieldDataType.DateType;
		}
		return null;
	}

	/**
	 * Returns default value for field
	 *
	 * @return default value
	 */
	Object getDefaultValue()
	{
		return defVal;
	}

	/**
	 * Checks if field is nullable
	 *
	 * @return true if field is nullable
	 */
	boolean isNullable()
	{
		return nullable;
	}
}
