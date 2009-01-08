package pl.pwr.odwa.result;
import java.util.ArrayList;

/**
 * Single row of the db query result
 * (For data presentation module)
 */
public class DBRow
{
	private int fieldsCount;
	private ArrayList<Object> values;
	
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
