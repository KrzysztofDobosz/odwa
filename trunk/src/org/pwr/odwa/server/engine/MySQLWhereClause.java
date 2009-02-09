/**
 * 
 */
package org.pwr.odwa.server.engine;

import java.util.ArrayList;

/**
 * @author Maciek Kupczak
 * @author Mateusz Lis
 * 
 */
public class MySQLWhereClause implements WhereClause
{
	protected String clause = "";
	protected ArrayList<MySQLWhereClause> whereClauses;
	protected ArrayList<SQLLogicOperator> logicOps;
	
	public MySQLWhereClause()
	{
		whereClauses = new ArrayList<MySQLWhereClause>();
		logicOps = new ArrayList<SQLLogicOperator>();
	}
	
	public MySQLWhereClause(String initialClause)
	{
		clause = initialClause;
		whereClauses = null;
		logicOps = null;
	}
	
	public void addStr(String clause, SQLLogicOperator op)
	{
		whereClauses.add(new MySQLWhereClause(clause));
		logicOps.add(op);
	}
	
	public void addWhereClause(WhereClause clause, SQLLogicOperator op)
			throws ClassCastException
	{
		try
		{
			whereClauses.add((MySQLWhereClause) clause);
			logicOps.add(op);
		} catch (ClassCastException e)
		{
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public String getClause()
	{
		if (whereClauses != null)
		{
			StringBuilder whereClause = new StringBuilder("(");
			if (whereClauses.size() > 0)
			{
				whereClause.append(whereClauses.get(0).getClause());
				
				for (int i = 1; i < whereClauses.size(); i++)
				{
					whereClause.append(" " + logicOperator(logicOps.get(i))
							+ " " + whereClauses.get(i).getClause());
					System.out.println(" asdasdads" + logicOperator(logicOps.get(i))
							+ " " + whereClauses.get(i).getClause());
				}
				whereClause.append(")");
				System.out.println(whereClause.toString());
				
				return whereClause.toString();
			} else
				return "";
			
		} else
			return clause;
	}
	
	public Boolean isEmpty()
	{
		return (clause.length() == 0 && whereClauses.size() == 0);
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
}
