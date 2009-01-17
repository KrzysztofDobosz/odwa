package org.pwr.odwa.client.visualization;

import java.util.ArrayList;
import com.gwtext.client.core.Ext;
import com.gwtext.client.widgets.grid.GridPanel;


/**
 * ReportStyle class is responsible for managing stiles while creating report.
 * An instance of this class is created everytime user will decide to save report. Class constructor sets the array of styles, as were chosen by user.
 * Method fetchStyleArray is used to get the style selection.
 *
 * @author Wojciech Skorski
 * @author Pawel Szoltysek
 */
public class ReportStyle
{
	private GridPanel gridIdMenu;
	private int cols;
	private int rows;
	ArrayList<ReportStyleCell> styles;

	/**
	 * Class constructor.
	 *
	 * @param gridIM grid from which styles are taken
	 * @param totalcol number of columns
	 * @param totalrow number of rows
	 */
	public ReportStyle(GridPanel gridIM, int totalcol, int totalrow) {
		gridIdMenu = gridIM;
		cols = totalcol;
		rows = totalrow;
		// iteruj po wszystkich elementach wyciagajac style
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				addReportStyleCell(i,j);
			}
		}
	}

	/**
	 * Method that provides an interface for adding a ReportStyleCell to ArrayList styles.
	 *
	 * @param row number of row
	 * @param col number of column
	 */
	public void addReportStyleCell(int row, int col) {
		styles.add(new ReportStyleCell(Ext.get(gridIdMenu.getView().getCell(row, col)).getStyle("font-weight"),Ext.get(gridIdMenu.getView().getCell(row, col)).getStyle("font-style"),Ext.get(gridIdMenu.getView().getCell(row, col)).getStyle("font-decoration"),Ext.get(gridIdMenu.getView().getCell(row, col)).getStyle("color")));
	}

	/**
	 * This method returns style for specified cell.
	 *
	 * @param row number of row
	 * @param col number of column
	 * @return cell style.
	 *
	 */
	ReportStyleCell fetchStyleForCell(int row, int col) {
		ReportStyleCell styleCell = styles.get((row*cols)+col);
		return styleCell;
	}

	/**
	 * This method returns an array with styles for whole grid.
	 *
	 * @return ArrayList with ReportStyleCell.
	 */
	ArrayList<ReportStyleCell> getReportStyle() {
		return styles;
	}

	/**
	 * This method applies current style sheet to selected grid.
	 *
	 * @param gridIM selected grid
	 */
	public void applyStyle(GridPanel gridIM) {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				Ext.get(gridIdMenu.getView().getCell(i, j)).setStyle("font-weight", styles.get((i-1)*cols+j).getCellStyle("font-weight"));
				Ext.get(gridIdMenu.getView().getCell(i, j)).setStyle("font-style", styles.get((i-1)*cols+j).getCellStyle("font-style"));
				Ext.get(gridIdMenu.getView().getCell(i, j)).setStyle("font-decoration", styles.get((i-1)*cols+j).getCellStyle("font-decoration"));
				Ext.get(gridIdMenu.getView().getCell(i, j)).setStyle("color", styles.get((i-1)*cols+j).getCellStyle("color"));
			}
		}
	}
}
