/**
 * 
 */
package org.pwr.odwa.server.engine;

import java.util.ArrayList;

/**
 * Class contains MySQL Where clauses. It is capable of joining any where
 * clauses in any logic form because it is possible to add whereClause to
 * another where clause and so on (finally creating algebraic tree)
 * 
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

	/**
	 * Returns text of the clause (without WHERE)
	 */
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
				}
				whereClause.append(")");
				
				return whereClause.toString();
			} else
				return "";
			
		} else
			return clause;
	}
	
	/**
	 * Nothing to comment.
	 */
	public Boolean isEmpty()
	{
		return (clause.length() == 0 && whereClauses.size() == 0);
	}
	
	/**
	 * translates enum logic operator to string
	 * @param op
	 * @return
	 */
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
