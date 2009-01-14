package org.pwr.odwa.common.result;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Single row of the db query result
 * (For data presentation module)
 */
public class DBRow implements IsSerializable
{
	private int fieldsCount;
	private ArrayList<Object> values;

	public DBRow(){}

	public DBRow(ArrayList<Object> values)
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
	Object getFieldVal ( int num)
	{
		return values.get(num);
	}

}
