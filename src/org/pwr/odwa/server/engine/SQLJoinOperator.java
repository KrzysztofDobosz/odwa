package org.pwr.odwa.server.engine;

/**
 * Represents join operators in SQL language
 *
 * @author Maciek Kupczak macku30@gmail.com
 * @author Mateusz Lis mateusz.lis@gmail.com
 */
public enum SQLJoinOperator
{
	JOIN,
	INNER_JOIN,
	OUTER_JOIN,
	LEFT_JOIN,
	RIGHT_JOIN,
	LEFT_RIGHT_JOIN;
}
