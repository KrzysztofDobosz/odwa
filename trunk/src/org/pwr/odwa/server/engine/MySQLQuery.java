package org.pwr.odwa.server.engine;

import java.util.ArrayList;

/**
 * Class responsible for creating MySQL queries. It handles all basic options,
 * however there is another class to handle WhereClauses, just because it has to
 * be capable of executing logic in queries
 * 
 * @author Maciek Kupczak macku30@gmail.com
 * @author Mateusz Lis mateusz.lis@gmail.com
 */

public class MySQLQuery implements SQLQuery
{
	private StringBuilder fromClause;
	private ArrayList<String> fromClauses;
	
	private StringBuilder selectClause;
	private ArrayList<String> selectClauses;
	
	private StringBuilder measureSelectClause;
	private ArrayList<String> measureSelectClauses;
	
	private StringBuilder groupByClause;
	private ArrayList<String> groupByClauses;
	
	private MySQLWhereClause whereClause;
	
	public MySQLQuery()
	{
		fromClause = new StringBuilder();
		selectClause = new StringBuilder();
		groupByClause = new StringBuilder();
		whereClause = null;
		fromClauses = new ArrayList<String>();
		selectClauses = new ArrayList<String>();
		measureSelectClause = new StringBuilder();
		measureSelectClauses = new ArrayList<String>();
		
		groupByClauses = new ArrayList<String>();
	}
	
	/**
	 * Add result field to the query
	 * 
	 * @param field
	 *            name of field, which will be added
	 */
	public void addResField(String field)
	{
		if (!selectClauses.contains(field))
		{
			if (selectClause.length() != 0)
			{
				selectClause.append(",");
			}
			selectClause.append(" " + field);
			selectClauses.add(field);
		}
		
	}
	
	/**
	 * Add measure result field to the query
	 * 
	 * @param field
	 *            name of measure, which will be added
	 */
	public void addMeasureResField(String field)
	{
		if (!measureSelectClauses.contains(field))
		{
			if (measureSelectClause.length() != 0)
			{
				measureSelectClause.append(",");
			}
			measureSelectClause.append(" " + field);
			measureSelectClauses.add(field);
		}
		
	}
	
	/**
	 * Adds element to GROUP BY clause in the query
	 * 
	 * @param clause
	 *            element
	 */
	public void addToGroupByClause(String clause)
	{
		if (!groupByClauses.contains(clause))
		{
			if (groupByClause.length() != 0)
			{
				groupByClause.append(",");
			}
			groupByClause.append(" " + clause);
			groupByClauses.add(clause);
		}
		
	}
	
	/**
	 * Adds WHERE clause to the query. If clause already exists, it will replace
	 * it.
	 * 
	 * @param clause
	 *            (MySQLWhereClause, otherwise will throw ClassCastException)
	 * 
	 */
	public void addToWhereClause(WhereClause aWhereClause)
			throws ClassCastException
	{
		try
		{
			whereClause = (MySQLWhereClause) aWhereClause;
		} catch (ClassCastException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Adds FIRST element to the from clause (if it is not the first one, it
	 * will be ignored, just because you have to use version with join)
	 * 
	 * @param fromField
	 *            field that is going to be addded
	 */
	public void addToFromClause(String fromField)
	{
		if (!fromClauses.contains(fromField))
		{
			if (fromClause.length() == 0)
			{
				fromClause.append(" " + fromField);
			}
			fromClauses.add(fromField);
		}
	}
	
	/**
	 * Adds element to from clause. If it is first one, join operator will be
	 * ignored.
	 * 
	 * @param fromField
	 *            field that is going to be added
	 * @param op
	 *            Join operator (for more than one field
	 * @param on1
	 *            first field that is going to be joined on
	 * @param on2
	 *            as the above
	 */
	public void addToFromClause(String fromField, SQLJoinOperator op,
			String on1, String on2)
	{
		// TODO: what if we need to add the same from field twice with
		// different operators? Is it correct? Is it needed?
		if (!fromClauses.contains(fromField))
		{
			if (fromClause.length() == 0)
			{
				fromClause.append(" " + fromField);
			} else
			{
				fromClause.append(" " + joinMySQL(op) + " " + fromField + " "
						+ " ON " + on1 + "=" + on2);
			}
			fromClauses.add(fromField);
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
	
	/**
	 * Returns Mysql query string.
	 */
	public String getQuery()
	{
		return "SELECT"
				+ selectClause.toString()
				+ (selectClause.length() != 0 ? ", " : "")
				+ measureSelectClause.toString()
				+ " FROM"
				+ fromClause.toString()
				+ ((whereClause != null && (!whereClause.isEmpty())) ? " WHERE"
						+ whereClause.getClause() : "")
				+ (groupByClause.length() != 0 ? " GROUP BY"
						+ groupByClause.toString() : "");
	}
}
