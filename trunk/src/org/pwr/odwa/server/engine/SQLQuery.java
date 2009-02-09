package org.pwr.odwa.server.engine;

import java.util.ArrayList;

/**
 * Interface which keeps creating database query abstract 
 *
 */
public interface SQLQuery
{
	public void addResField(String field);
	public void addToFromClause(String fromField);
	public void addToFromClause(String fromField, SQLJoinOperator op
			, String on1, String on2);
	public void addToWhereClause(WhereClause clause);
	public void addMeasureResField(String field);
	public void addToGroupByClause(String clause);
	public String getQuery();
}
