package org.pwr.odwa.server.engine;
/**
 * Class responsible for creating MySQL querries.
 */

public class MySQLQuery implements SQLQuery
{
	private String fromClause;

	private String selectClause;

	private String groupByClause;

	private String whereClause;

	public MySQLQuery()
	{
		fromClause = new String();
		selectClause = new String();
		groupByClause = new String();
		whereClause = new String();
	}

	/**
	 * Add result field to the query
	 * @param field name of field, which will be added
	 */
	public void addResField(String field)
	{
		if (selectClause.length() != 0)
		{
			selectClause += ",";
		}
		selectClause += " " + field;

	}

	/**
	 * Adds element to GROUP BY clause in the query
	 * @param clause element
	 */
	public void addToGroupByClause(String clause)
	{
		if (groupByClause.length() != 0)
		{
			groupByClause += ",";
		}
		groupByClause += " " + clause;
	}

	/**
	 * Adds first element to WHERE clause in the query. If it's not the first
	 * element, it is not going to be added, just because then you have to use 
	 * version of this function with operator parameter
	 * @param clause element
	 */
	public void addToWhereClause(String clause)
	{
		if (whereClause.length() == 0)
		{
			whereClause = " " + clause;
		}

	}

	/**
	 * Adds element to WHERE clause (if it is first element, operator will be
	 * ignored)
	 * @param clause element that is going to be added
	 * @param op operator (if it is not the first one
	 */
	public void addToWhereClause(String clause, SQLLogicOperator op)
	{
		if (whereClause.length() == 0)
		{
			whereClause = " " + clause;
		} else
		{
			whereClause += " " + logicOperator(op) + " " + clause;
		}
	}

	/**
	 * Adds FIRST element to the from clause (if it is not the first one, it 
	 * will be ignored, just because you have to use version with join)
	 * @param fromField field that is going to be addded
	 */
	public void addToFromClause(String fromField)
	{
		if (fromClause.length() == 0)
		{
			fromClause = " " + fromField;
		}
	}

	/**
	 * Adds element to from clause. If it is first one, join operator will be 
	 * ignored.
	 * @param fromField field that is going to be added
	 * @param op Join operator (for more than one field
	 * @param on1 first field that is going to be joined on
	 * @param on2 as the above
	 */
	public void addToFromClause(String fromField, SQLJoinOperator op,
			String on1, String on2)
	{
		if (fromClause.length() == 0)
		{
			fromClause = " " + fromField;
		} else
		{
			fromClause += " " + joinMySQL(op) + " " + fromField + " " + " ON "
					+ on1 + "=" + on2;
		}
	}

	private String joinMySQL(SQLJoinOperator op)
	{
		switch (op)
		{
		case JOIN:
			return "JOIN";
		case INNER_JOIN:
			return "INNER JOIN";
		case LEFT_JOIN:
			return "LEFT JOIN";
		case LEFT_RIGHT_JOIN:
			return "LEFT RIGHT JOIN";
		case OUTER_JOIN:
			return "OUTER JOIN";
		}
		return "";
	}

	private String logicOperator(SQLLogicOperator op)
	{
		switch (op)
		{
		case AND:
			return "AND";
		case OR:
			return "OR";
		}
		return "";
	}

	/**
	 * Returns Mysql query string.
	 */
	public String getQuery()
	{
		return "SELECT" + selectClause + " FROM" + fromClause
				+ (whereClause.length() != 0 ? " WHERE" + whereClause : "")
				+ (groupByClause.length() != 0 ? " GROUP BY" + groupByClause : "");
	}
}
