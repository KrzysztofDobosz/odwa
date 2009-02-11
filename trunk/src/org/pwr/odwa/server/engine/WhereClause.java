package org.pwr.odwa.server.engine;

/**
 * Interface keeps where clauses in database queries abstract
 * 
 * @author Maciek Kupczak
 * @author Mateusz Lis
 * 
 */
public interface WhereClause
{
	/**
	 * adds query string to WHERE clause.
	 * 
	 * @param clause
	 *            String should have the form of: "Tablename.City = 'wrocław'"
	 * @param op
	 */
	public void addStr(String clause, SQLLogicOperator op);
	
	/**
	 * This function allows you to add WHERE clause to another WHERE clause
	 * 
	 * @param clause
	 *            WHERE clause created earlier (doesn't have to be finnished...)
	 * @param op
	 *            logic operator which will be inserted before the clause (it
	 *            wont be used if clause was empty)
	 */
	public void addWhereClause(WhereClause clause, SQLLogicOperator op);
	
	/**
	 * returns SQL string containing WHERE clause (without the WHERE word itself)
	 * @return
	 */
	public String getClause();
	 
	/**
	 * returns true if where clause is empty
	 * @return
	 */
	public Boolean isEmpty();
	
}
