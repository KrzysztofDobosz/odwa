package org.pwr.odwa.common.selection;

import java.util.ArrayList;

public class SelectionLoader
{
	private ArrayList<ArrayList<String>> rows;
	private ArrayList<ArrayList<String>> cols;
	private ArrayList<ArrayList<String>> background;
	private String measure;

	public SelectionLoader()
	{
		rows = new ArrayList<ArrayList<String>>();
		cols = new ArrayList<ArrayList<String>>();
		background = new ArrayList<ArrayList<String>>();
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

	public void setBackground(ArrayList<ArrayList<String>> background)
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
		for(ArrayList<String> backList : background)
		{
			retStr += "background: \n";
			for(String bac: backList)
			{
				retStr += bac + " ";
			}
			retStr += "\n";
		}
		retStr += "measure: " + measure;
		return retStr;
	}

}
