package org.pwr.odwa.client.visualization;

import org.pwr.odwa.common.dbtypes.*;
import org.pwr.odwa.common.metadata.*;
import org.pwr.odwa.common.result.*;
import org.pwr.odwa.common.selection.*;

import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.client.visualization.ReportStyleCell;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.ColorPalette;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.menu.Menu;

import com.gwtext.client.data.*;
import com.gwtext.client.widgets.grid.RowNumberingColumnConfig;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;

import com.gwtext.client.widgets.chart.yui.PieChart;

import com.gwtext.client.widgets.menu.*;
import com.gwtext.client.widgets.menu.event.ColorMenuListener;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import com.gwtext.client.widgets.grid.event.*;

import com.gwtext.client.core.Ext;

import com.gwtextux.client.widgets.grid.plugins.GridSearchPlugin;

import com.gwtextux.client.widgets.grid.plugins.GridDateFilter;
import com.gwtextux.client.widgets.grid.plugins.GridFilter;
import com.gwtextux.client.widgets.grid.plugins.GridFilterPlugin;
import com.gwtextux.client.widgets.grid.plugins.GridListFilter;
import com.gwtextux.client.widgets.grid.plugins.GridNumericFilter;
import com.gwtextux.client.widgets.grid.plugins.GridStringFilter;

import com.gwtext.client.widgets.chart.yui.LineChart;
import com.gwtext.client.widgets.chart.yui.NumericAxis;
import com.gwtext.client.widgets.chart.yui.SeriesDefY;

import org.pwr.odwa.common.result.DBResult;

/**
 * Visualization class is the output of whole ODWA system.
 * It's main purpose is to generate the grid and charts. Methods show() are 
 * responsible for displaying these information to user.
 * 
 * @author Wojciech Skórski
 * @author Pawe³ Szo³tysek
 * 
 */
public class Visualization implements /* EntryPoint, */GridCellListener,
		ColorMenuListener {
	private int cols;
	private int rows;
	private int[] contextCell;
	private GridPanel gridIdMenu;
	private ColorPalette colorPalette;
	final Menu gridMenu = new Menu();
	final ColorMenu gridColorMenu = new ColorMenu();

	Item BoldItem = new Item();
	Item ItalicItem = new Item();
	Item UnderlineItem = new Item();
	MenuItem ColorMenuItem = new MenuItem(); // "Pick a Color", gridColorMenu);

	GridPanel currentGridTab = new GridPanel();
	
	public Visualization() {
		createGridContextMenu();
	}

	/**
	 * @return gridIdMenu variable.
	 */
	public GridPanel getGridIdMenu() {
		return gridIdMenu;
	}

	/**
	 * 
	 */
	public void onCellClick(GridPanel grid, int rowIndex, int colIndex,
			EventObject e) {

	}

	/**
	 * Handles the right-click on the grid menu. Automatically called, created
	 * basing on GridCellListener interface. Shows the context menu at the
	 * point.
	 * 
	 * @param grid
	 *            GridPanel in which rightclick occured.
	 * @param rowIndex
	 *            number of row
	 * @param cellIndex
	 *            number of field
	 * @param e
	 *            object on which event occured
	 * @see #show(String title)
	 */
	public void onCellContextMenu(GridPanel grid, int rowIndex, int cellIndex,
			EventObject e) {
		System.out.println("GridCellListener.onCellContextMenu::row("
				+ rowIndex + "), col(" + cellIndex + ");");
		e.stopEvent();
		contextCell = new int[] { rowIndex, cellIndex };
		gridIdMenu = new GridPanel();
		gridIdMenu = grid;
		colorPalette = new ColorPalette();
		colorPalette = gridColorMenu.getColorPalette();

		gridMenu.showAt(e.getXY());
		// new Record = tab1.getStore().getAt(rowIndex);
	}

	/**
	 * 
	 */
	public void onCellDblClick(GridPanel grid, int rowIndex, int colIndex,
			EventObject e) {

	}

	/**
	 * Sets the menu, which is later used to change styles.
	 */
	public void createGridContextMenu() {
		gridMenu.setShadow(true);
		gridMenu.setMinWidth(10);

		final BaseItemListenerAdapter MenuListener = new BaseItemListenerAdapter() {
			public void onClick(BaseItem item, EventObject e) {

				if (BoldItem.getItemId().compareTo(item.getItemId()) == 0) {
					if (Ext.get(
							gridIdMenu.getView().getCell(contextCell[0],
									contextCell[1])).getStyle("font-weight")
							.compareTo("bold") == 0) {
						Ext.get(
								gridIdMenu.getView().getCell(contextCell[0],
										contextCell[1])).setStyle(
								"font-weight", "normal");
					} else {
						Ext.get(
								gridIdMenu.getView().getCell(contextCell[0],
										contextCell[1])).setStyle(
								"font-weight", "bold");
					}
				} else if (ItalicItem.getItemId().compareTo(item.getItemId()) == 0) {
					if (Ext.get(
							gridIdMenu.getView().getCell(contextCell[0],
									contextCell[1])).getStyle("font-style")
							.compareTo("italic") == 0) {
						Ext.get(
								gridIdMenu.getView().getCell(contextCell[0],
										contextCell[1])).setStyle("font-style",
								"normal");
					} else {
						Ext.get(
								gridIdMenu.getView().getCell(contextCell[0],
										contextCell[1])).setStyle("font-style",
								"italic");
					}
				} else if (UnderlineItem.getItemId()
						.compareTo(item.getItemId()) == 0) {
					if (Ext.get(
							gridIdMenu.getView().getCell(contextCell[0],
									contextCell[1]))
							.getStyle("text-decoration").compareTo("underline") == 0) {
						Ext.get(
								gridIdMenu.getView().getCell(contextCell[0],
										contextCell[1])).setStyle(
								"text-decoration", "none");
					} else {
						Ext.get(
								gridIdMenu.getView().getCell(contextCell[0],
										contextCell[1])).setStyle(
								"text-decoration", "underline");
					}
				}
				// tab1.getView().getCell(contextCell[0], contextCell[1])

			}
		};

		BoldItem.setText("<b>Bold</b>");
		gridMenu.addItem(BoldItem);
		BoldItem.addListener(MenuListener);

		ItalicItem.setText("<i>Italic</i>");
		gridMenu.addItem(ItalicItem);
		ItalicItem.addListener(MenuListener);

		UnderlineItem.setText("<u>Underline</u>");
		gridMenu.addItem(UnderlineItem);
		UnderlineItem.addListener(MenuListener);

		gridMenu.addSeparator();

		ColorMenuItem.setText("Font color");
		ColorMenuItem.setMenu(gridColorMenu);
		gridMenu.addItem(ColorMenuItem);

		gridColorMenu.addListener(this);

		/*
		 * Item moreColors = new Item(); moreColors.setText("More Colors...");
		 * gridColorMenu.addItem(moreColors);
		 */

	}

	/**
	 * Creates and displays new window with requested styles.
	 * 
	 * @param result
	 *            data which has to be visualized.
	 * @param style
	 *            CSS styles for the grid.
	 */
	public void show(DBResult result, ReportStyle style) {
		show(result);		
		style.applyStyle(currentGridTab);
	}

	/**
	 * Creates and displays new window with visualization data.
	 * 
	 * @param result
	 *            data which has to be visualized.
	 */
	public void show(DBResult result) {
		// Creating main visualization window
		final Window window = new Window();
		window.setTitle("Report");
		window.setClosable(true);
		window.setWidth(600);
		window.setHeight(350);
		window.setPlain(true);
		window.setLayout(new BorderLayout());
		window.setCloseAction(Window.HIDE);
		window.setCollapsible(true);

		// Creating main tab panel
		TabPanel tabPanel = new TabPanel();
		tabPanel.setActiveTab(0);

		// Creating first tab - GRID
		final GridPanel tab1 = new GridPanel();
		currentGridTab = tab1;
		tab1.setTitle("Grid");

		//result.reset();
		
		DBRow row = new DBRow();
		int rowsInResult = 0;
		int colsInResult = 0;
		if ((row = result.fetchRow()) != null) {
			String firstRow = row.getFieldVal(0).toString();
			String firstCol = row.getFieldVal(1).toString();
			rowsInResult++;
			colsInResult++;
			while ((row = result.fetchRow()) != null) {
				if (firstRow.compareTo(row.getFieldVal(0).toString()) == 0) {
					colsInResult++;
				} 
				if (firstCol.compareTo(row.getFieldVal(1).toString()) == 0) {
					rowsInResult++;
				}
			}
		}
		cols = colsInResult;
		rows = rowsInResult;
		
		result.reset(); // Make fetchRow will start from the beginning.
		
		GridFilter gf[] = new GridFilter[colsInResult];
		FieldDef table[] = new FieldDef[colsInResult];
		
		table[0] = new StringFieldDef("0");
		gf[0] = new GridStringFilter("0");
		for(int i=1;i<=colsInResult; i++) { 
			table[i] = new FloatFieldDef((String)((Integer)i).toString());
			gf[i] = new GridNumericFilter((String)((Integer)i).toString());
		}
		
		RecordDef recordDef = new RecordDef(table);
				
		/*// Hard-coded data
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("company"), new FloatFieldDef("price"),
				new FloatFieldDef("change"), new FloatFieldDef("pctChange"),
				new DateFieldDef("lastChanged", "n/j h:ia"),
				new StringFieldDef("symbol"), new StringFieldDef("industry") });
		Object[][] data = new Object[][] {
				new Object[] { "3m Co", new Double(71.72), new Double(0.02),
						new Double(0.03), "9/1 12:00am", "MMM", "Manufacturing" },
				new Object[] { "Alcoa Inc", new Double(29.01),
						new Double(0.42), new Double(1.47), "9/1 12:00am",
						"AA", "Manufacturing" },
				new Object[] { "Altria Group Inc", new Double(83.81),
						new Double(0.28), new Double(0.34), "9/1 12:00am",
						"MO", "Manufacturing" },
				new Object[] { "American Express Company", new Double(52.55),
						new Double(0.01), new Double(0.02), "9/1 12:00am",
						"AXP", "Finance" },
				new Object[] { "American International Group, Inc.",
						new Double(64.13), new Double(0.31), new Double(0.49),
						"9/1 12:00am", "AIG", "Services" },
				new Object[] { "AT&T Inc.", new Double(31.61),
						new Double(-0.48), new Double(-1.54), "9/1 12:00am",
						"T", "Services" },
				new Object[] { "Boeing Co.", new Double(75.43),
						new Double(0.53), new Double(0.71), "9/1 12:00am",
						"BA", "Manufacturing" },
				new Object[] { "Caterpillar Inc.", new Double(67.27),
						new Double(0.92), new Double(1.39), "9/1 12:00am",
						"CAT", "Services" },
				new Object[] { "Citigroup, Inc.", new Double(49.37),
						new Double(0.02), new Double(0.04), "9/1 12:00am", "C",
						"Finance" },
				new Object[] { "E.I. du Pont de Nemours and Company",
						new Double(40.48), new Double(0.51), new Double(1.28),
						"9/1 12:00am", "DD", "Manufacturing" }
		};*/

		int queryCols = result.getColumnCount();
		
		Object data[][] = new Object[rowsInResult][colsInResult];
		int i=0;
		int j=0;
		if ((row = result.fetchRow()) != null) {
			String CurrentRow = ((String)row.getFieldVal(0));
			data[i][j] = CurrentRow;
			j++;
			data[i][j] = (Integer.parseInt((String)row.getFieldVal(queryCols-1)));
			j++; // j=2
			
			while ((row = result.fetchRow()) != null) {
				if (CurrentRow.compareTo((String)row.getFieldVal(0)) == 0) {
					data[i][j] = (Integer.parseInt((String)row.getFieldVal(queryCols-1)));
					j++;
				} else {
					i++;
					j=0;
					CurrentRow = ((String)row.getFieldVal(0));
					data[i][j] = CurrentRow;
					j++;
					data[i][j] = (Integer.parseInt((String)row.getFieldVal(queryCols-1)));
					j++; // j=2
				}
			}
		}

		MemoryProxy proxy = new MemoryProxy(data);

		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader);
		store.load();
		tab1.setStore(store);

		/*// Hard-coded data.
		BaseColumnConfig[] columns = new BaseColumnConfig[] {
				new RowNumberingColumnConfig(),
				// column ID is company which is later used in
				// setAutoExpandColumn
				new ColumnConfig("Company", "0", 160, true, null, "company"), 
				new ColumnConfig("Price", "1", 35, true, null, "1")
		};*/

		BaseColumnConfig columns[] = new BaseColumnConfig[colsInResult+1];
		columns[0] = new RowNumberingColumnConfig();
		for (int k=1; k<=colsInResult+1; k++) {
			columns[k] = new ColumnConfig("", ((String)((Integer)(k-1)).toString()), 80, true);
		}
		
		ColumnModel columnModel = new ColumnModel(columns);
		tab1.setColumnModel(columnModel);

		tab1.setFrame(true);
		tab1.setStripeRows(true);

		Toolbar bottomToolbar = new Toolbar();
		bottomToolbar.addFill();
		bottomToolbar.addButton(new ToolbarButton("Clear Sort",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						tab1.clearSortState(true);
					}
				}));

		GridSearchPlugin gridSearch = new GridSearchPlugin(GridSearchPlugin.BOTTOM);
		gridSearch.setMode(GridSearchPlugin.LOCAL);
		tab1.addPlugin(gridSearch);

		/*// Hard-coded data
		GridFilter gf[] = new GridFilter[7];
		gf[0] = new GridStringFilter("company");
		gf[1] = new GridNumericFilter("price");
		*/
		
		GridFilterPlugin gridFilterPlugin = new GridFilterPlugin(gf);
		gridFilterPlugin.setLocal(true);
		tab1.addPlugin(gridFilterPlugin);

		bottomToolbar.addButton(new ToolbarButton("Create Report",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						ReportStyle style = new ReportStyle(gridIdMenu, cols, rows);
						System.out.println(style.fetchReportStyle().toString());
					}
				}));

		tab1.setBottomToolbar(bottomToolbar);

		tab1.addGridCellListener(this);
		// style.applyStyle(tab1);

		// Creating second tab - CIRCLE CHART
		final PieChart tab2 = new PieChart();
		tab2.setTitle("Circle chart");
		tab2.setWMode("transparent");
		tab2.setStore(store);
		tab2.setDataField("1");
		tab2.setCategoryField("0");
		tab2.setExpressInstall("js/yui/assets/expressinstall.swf");
		// tab2.setWidth(500);
		// tab2.setHeight(400);

		// Creating third tab - LINE CHART
		final LineChart tab3 = new LineChart();
		tab3.setTitle("Line chart");
		tab3.setWMode("transparent");
		tab3.setStore(store);

		SeriesDefY seriesDef[] = new SeriesDefY[colsInResult];
		for (int k=0; k<colsInResult; k++) {
			seriesDef[k] = new SeriesDefY(((String)((Integer)(k)).toString()),((String)((Integer)(k)).toString()));
		}
		tab3.setSeries(seriesDef);
		tab3.setXField("0");// result.getColumnName(0));
		tab3.setExpressInstall("js/yui/assets/expressinstall.swf");
		// tab2.setWidth(500);
		// tab2.setHeight(400);

		// Creating fourth tab - SIMPLE TEXT

		Panel tab4 = new Panel();
		tab4.setTitle("About");
		tab4.setHtml("ODWA Open Data Warehouse Analysis<br>Politechnika Wroclawska 2008 - 2009<br><br><b>Project tutor</b>: Ph.D. Maciej Kiewra<br><b>Database</b>: Mateusz Lis, Maciej Kupczak<br><b>GUI</b>: Krzysztof Dobosz, Lukasz Pintal<br><b>Metadata</b>:Maria Labaziewicz, Mateusz Paprocki<br><b>Reports</b>:Katarzyna Rzerzicha, Michal Brzezinski-Spiczak<br><b>Visualization</b>:Pawel Szoltysek, Wojciech Skorski");
		tab4.setAutoScroll(true);
		tab4.setClosable(true);

		tabPanel.add(tab1);
		tabPanel.add(tab2);
		tabPanel.add(tab3);
		tabPanel.add(tab4);

		BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);
		centerData.setMargins(3, 0, 3, 3);

		window.add(tabPanel, centerData);
		window.show();
	}

	public void onSelect(ColorPalette colorPalette, String color) {
		System.out.println("#" + color);
		Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("color", "#" + color);
	}
}
