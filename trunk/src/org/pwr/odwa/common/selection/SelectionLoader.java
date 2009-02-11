package org.pwr.odwa.common.selection;

import java.util.ArrayList;

/**
 * Class enables transforming interface selection to UserSelection object.
 * @author Lukasz Pintal
 *
 */
public class SelectionLoader
{
	private ArrayList<ArrayList<String>> rows;
	private ArrayList<ArrayList<String>> cols;
	private ArrayList<String> background;
	private String measure;
	private String dataBaseId;


	public ArrayList<ArrayList<String>> getRows() {
		return rows;
	}

	public ArrayList<ArrayList<String>> getCols() {
		return cols;
	}

	public ArrayList<String> getBackground() {
		return background;
	}

	public String getMeasure() {
		return measure;
	}

	public SelectionLoader()
	{
		rows = new ArrayList<ArrayList<String>>();
		cols = new ArrayList<ArrayList<String>>();
		background = new ArrayList<String>();
		measure = "";
	}

	public void setRows(ArrayList<ArrayList<String>> rows)
	{
		this.rows = rows;
	}

	public void setCols(ArrayList<ArrayList<String>> cols)
	{
		this.cols = cols;
	}

	public void setBackground(ArrayList<String> background)
	{
		this.background = background;
	}

	public void setMeasure(String measure)
	{
		this.measure = measure;
	}

	public String toString()
	{
		String retStr="";

		for(ArrayList<String> rowsList : rows)
		{
			retStr += "rows: \n";
			for(String row: rowsList)
			{
				retStr += row + " ";
			}
			retStr += "\n";
		}
		for(ArrayList<String> colsList : cols)
		{
			retStr += "columns: \n";
			for(String col: colsList)
			{
				retStr += col + " ";
			}
			retStr += "\n";
		}
		for(String backList : background)
		{
			retStr += "background: \n";
			retStr += backList;
			retStr += "\n";
		}
		retStr += "measure: " + measure;
		return retStr;
	}

	public void setDataBaseId(String dataBaseId) {
		this.dataBaseId = dataBaseId;
	}

	public String getDataBaseId() {
		return dataBaseId;
	}

}

