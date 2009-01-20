package org.pwr.odwa.common.result;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Single row of the db query result
 * (For data presentation module)
 */
public class DBRow implements Serializable
{
	private int fieldsCount;
	private ArrayList<String> values;

	public DBRow(){}

	public DBRow(ArrayList<String> values)
	{
		this.fieldsCount = values.size();
		this.values = values;
	}
	/**
	 * Returns result column count
	 * @return col count
	 */
	int getFieldsCount ( )
	{
		return fieldsCount;
	}


	/**
	 * Returns field value
	 * @param num field number
	 * @return field value
	 */
	public Object getFieldVal ( int num)
	{
		return values.get(num);
	}

}
