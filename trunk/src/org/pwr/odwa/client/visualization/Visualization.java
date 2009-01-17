package org.pwr.odwa.client.visualization;


import org.pwr.odwa.common.result.DBResult;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.ColorPalette;
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
/*
import com.gwtextux.client.widgets.grid.plugins.GridSearchPlugin;

import com.gwtextux.client.widgets.grid.plugins.GridDateFilter;
import com.gwtextux.client.widgets.grid.plugins.GridFilter;
import com.gwtextux.client.widgets.grid.plugins.GridFilterPlugin;
import com.gwtextux.client.widgets.grid.plugins.GridListFilter;
import com.gwtextux.client.widgets.grid.plugins.GridNumericFilter;
import com.gwtextux.client.widgets.grid.plugins.GridStringFilter;

import net.maincode.main.client.ReportStyle;
*/
import com.gwtext.client.widgets.chart.yui.LineChart;
import com.gwtext.client.widgets.chart.yui.NumericAxis;
import com.gwtext.client.widgets.chart.yui.SeriesDefY;

/*
 * Main task of Visualization class is to provide an simple and useful tool for displaying the data which rest of system is generating.
 * Used technology (GWT-Ext) gives the possibility of easy but powerful and pretty design of the visualization part of the user interface, which can be accessed from the web.
 * An instance of this class is created while system starts. Each data are shown in another window in interface, where each o them is created using function showVisualizationWindow. Each tab in this window handles another type of report - a grid, chart, etc.
 *
 * @author Wojciech Skórski
 * @author Paweł Szołtysek
 *
 */
public class Visualization implements EntryPoint, GridCellListener, ColorMenuListener {
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

	/**
	 * General initialization method. Starts whole system.
	 */
    public void onModuleLoad() {/*
        Panel panel = new Panel();
        panel.setBorder(false);




        Button button = new Button("Show Window");
        button.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	show();
            }
        });
        panel.add(button);

        createGridContextMenu();

        RootPanel.get().add(panel);*/
    }

	/**
	 * @return gridIdMenu variable.
	 */
    public GridPanel getGridIdMenu() {
    	return gridIdMenu;
    }

	/**
	 * Tego docelowo nie bedzie
	 */
	public void onCellClick(GridPanel grid, int rowIndex, int colIndex, EventObject e) {

	}

	/**
	 * Handles the right-click on the grid menu. Automatically called, created basing on GridCellListener interface.
	 * Shows the context menu at the point.
	 *
	 * @author Paweł Szołtysek
	 * @param grid GridPanel in which rightclick occured.
	 * @param rowIndex number of row
	 * @param cellIndex number of field
	 * @param e object on which event occured
	 * @see #show()
	 */
	public void onCellContextMenu(GridPanel grid, int rowIndex, int cellIndex, EventObject e) {
    	System.out.println("GridCellListener.onCellContextMenu::row(" + rowIndex + "), col(" + cellIndex + ");");
    	e.stopEvent();
    	contextCell = new int[] { rowIndex, cellIndex };
    	gridIdMenu = new GridPanel();
    	gridIdMenu = grid;
    	colorPalette = new ColorPalette();
    	colorPalette = gridColorMenu.getColorPalette();

    	gridMenu.showAt(e.getXY());
    	//new Record = tab1.getStore().getAt(rowIndex);
	}

	/**
	 * Cos pod dwuklikiem?
	 */
	public void onCellDblClick(GridPanel grid, int rowIndex, int colIndex, EventObject e) {

	}

	/**
	 * Sets the menu, which is later used to change styles.
	 *
	 * @author Paweł Szołtysek
	 */
	public void createGridContextMenu() {
    	gridMenu.setShadow(true);
    	gridMenu.setMinWidth(10);

        final BaseItemListenerAdapter MenuListener = new BaseItemListenerAdapter() {
            public void onClick(BaseItem item, EventObject e) {

            	if (BoldItem.getItemId().compareTo(item.getItemId()) == 0) {
            		if (Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).getStyle("font-weight").compareTo("bold") == 0){
            			Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("font-weight", "normal");
            		} else {
            			Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("font-weight", "bold");
            		}
            	}
            	else if (ItalicItem.getItemId().compareTo(item.getItemId()) == 0) {
            		if (Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).getStyle("font-style").compareTo("italic") == 0){
            			Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("font-style", "normal");
            		} else {
            			Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("font-style", "italic");
            		}
            	}
            	else if (UnderlineItem.getItemId().compareTo(item.getItemId()) == 0) {
            		if (Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).getStyle("text-decoration").compareTo("underline") == 0){
            			Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("text-decoration", "none");
            		} else {
            			Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("text-decoration", "underline");
            		}
            	}
            	//tab1.getView().getCell(contextCell[0], contextCell[1])

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

        ColorMenuItem.setText("Kolor czcionki");
        ColorMenuItem.setMenu(gridColorMenu);
        gridMenu.addItem(ColorMenuItem);

        gridColorMenu.addListener(this);

        /*Item moreColors = new Item();
        moreColors.setText("More Colors...");
        gridColorMenu.addItem(moreColors);  */

	}

	/**
	 * Creates and displays new window with requested visualization.
	 *
	 * @author Paweł Szołtysek
	 * @param result data which has to be visualized.
	 * @param style CSS styles for the grid.
	 * @param title window title.
	 */
	public void show(DBResult result) {

		// Creating main visualization window
        final Window window = new Window();
        window.setTitle("REPORT");
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
        tab1.setTitle("Grid");

        RecordDef recordDef = new RecordDef(
                new FieldDef[]{
                        new StringFieldDef("company"),
                        new FloatFieldDef("price"),
                        new FloatFieldDef("change"),
                        new FloatFieldDef("pctChange"),
                        new DateFieldDef("lastChanged", "n/j h:ia"),
                        new StringFieldDef("symbol"),
                        new StringFieldDef("industry")
                }
        );

        /*cols = result.getColumnCount();
          GridFilter gf[] = new GridFilter[cols];
          FieldDef[] table;
          for(int i=0; i<numcols; i++) {
          	DBFieldDataType type = result.getColumnType(i);
          	if (type = String) {
          		table[i] = new StringFieldDef(columns.getColumnName(i));
          		gf[i] = new GridStringFilter(columns.getColumnName(i));
          	} else if (type = Double) {
          		table[i] = new DoubleFieldDef(columns.getColumnName(i));
          		gf[i] = new GridNumericFilter(columns.getColumnName(i));
          	} else if (type = Date) {
          		table[i] = new DateFieldDef(columns.getColumnName(i));
          		gf[i] = new GridDateFilter(columns.getColumnName(i));
          	}
          }
          RecordDef recordDef = new RecordDef(table);
        */


        Object[][] data = new Object[][]{
                new Object[]{"3m Co", new Double(71.72), new Double(0.02),
                        new Double(0.03), "9/1 12:00am", "MMM", "Manufacturing"},
                new Object[]{"Alcoa Inc", new Double(29.01), new Double(0.42),
                        new Double(1.47), "9/1 12:00am", "AA", "Manufacturing"},
                new Object[]{"Altria Group Inc", new Double(83.81), new Double(0.28),
                        new Double(0.34), "9/1 12:00am", "MO", "Manufacturing"},
                new Object[]{"American Express Company", new Double(52.55), new Double(0.01),
                        new Double(0.02), "9/1 12:00am", "AXP", "Finance"},
                new Object[]{"American International Group, Inc.", new Double(64.13), new Double(0.31),
                        new Double(0.49), "9/1 12:00am", "AIG", "Services"},
                new Object[]{"AT&T Inc.", new Double(31.61), new Double(-0.48),
                        new Double(-1.54), "9/1 12:00am", "T", "Services"},
                new Object[]{"Boeing Co.", new Double(75.43), new Double(0.53),
                        new Double(0.71), "9/1 12:00am", "BA", "Manufacturing"},
                new Object[]{"Caterpillar Inc.", new Double(67.27), new Double(0.92),
                        new Double(1.39), "9/1 12:00am", "CAT", "Services"},
                new Object[]{"Citigroup, Inc.", new Double(49.37), new Double(0.02),
                        new Double(0.04), "9/1 12:00am", "C", "Finance"},
                new Object[]{"E.I. du Pont de Nemours and Company", new Double(40.48), new Double(0.51),
                        new Double(1.28), "9/1 12:00am", "DD", "Manufacturing"}
        };

        /*
          DBRow row = new DBRow();
          Object[][] obj;
          int i=0;
          while ((row = result.fetchRow()) != NULL)
          {
          	for (int j=0; j<cols; j++)
          	{
          		obj[i,j]=row.getFieldVal(j);
          	}
          	i++;
          }
         */

        MemoryProxy proxy = new MemoryProxy(data);

        ArrayReader reader = new ArrayReader(recordDef);
        Store store = new Store(proxy, reader);
        store.load();
        tab1.setStore(store);

        BaseColumnConfig[] columns = new BaseColumnConfig[]{
        		new RowNumberingColumnConfig(),
                //column ID is company which is later used in setAutoExpandColumn
        		new ColumnConfig("Company", "company", 160, true, null, "company"),
                new ColumnConfig("Price", "price", 35),
                new ColumnConfig("Change", "change", 45),
                new ColumnConfig("% Change", "pctChange", 65),
                new ColumnConfig("Last Updated", "lastChanged", 65),
                new ColumnConfig("Industry", "industry", 60, true)
        };

        /* BaseColumnConfig[] columns = new BaseColumnConfig[] {
           		new RowNumberingColumnConfig()
           }
           for (int i=0; i<cols; i++) {
           		columns[i] = (result.getColumnName(i), result.getColumnName(i), 80, true);
           }
       */

        ColumnModel columnModel = new ColumnModel(columns);
        tab1.setColumnModel(columnModel);

        tab1.setFrame(true);
        tab1.setStripeRows(true);
        //tab1.setAutoExpandColumn("company");

        Toolbar bottomToolbar = new Toolbar();
        bottomToolbar.addFill();
        bottomToolbar.addButton(new ToolbarButton("Clear Sort", new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	tab1.clearSortState(true);
            }
        }));
/*
        GridSearchPlugin gridSearch = new GridSearchPlugin(GridSearchPlugin.BOTTOM);
        gridSearch.setMode(GridSearchPlugin.LOCAL);
        tab1.addPlugin(gridSearch);

        GridFilter gf[] = new GridFilter[7];
        gf[0] = new GridStringFilter("company");
        gf[1] = new GridNumericFilter("price");
        gf[2] = new GridNumericFilter("change");
        gf[3] = new GridNumericFilter("pctChange");
        gf[4] = new GridDateFilter("lastChanged");
        gf[5] = new GridStringFilter("symbol");
        gf[6] = new GridListFilter("industry", new String[]{"Manufacturing", "Finance", "Services", "Computer", "Food", "Retail"});

        GridFilterPlugin gridFilterPlugin = new GridFilterPlugin(gf);
        gridFilterPlugin.setLocal(true);
        tab1.addPlugin(gridFilterPlugin);*/

        bottomToolbar.addButton(new ToolbarButton("Create Report", new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	ReportStyle style = new ReportStyle(gridIdMenu,cols,rows);
            	System.out.println(style.getReportStyle().toString());
            }
        }));

        tab1.setBottomToolbar(bottomToolbar);

    	tab1.addGridCellListener(this);
    	//style.applyStyle(tab1);

    	// Creating second tab - CIRCLE CHART
        final PieChart tab2 = new PieChart();
        tab2.setTitle("Circle chart");
        tab2.setWMode("transparent");
        tab2.setStore(store);
        tab2.setDataField("price");
        tab2.setCategoryField("company");
        tab2.setExpressInstall("js/yui/assets/expressinstall.swf");
        //tab2.setWidth(500);
        //tab2.setHeight(400);

    	// Creating third tab - LINE CHART
        final LineChart tab3 = new LineChart();
        tab3.setTitle("Line chart");
        tab3.setWMode("transparent");
        tab3.setStore(store);
        SeriesDefY[] seriesDef = new SeriesDefY[]{
                new SeriesDefY("price", "price")
        };
        tab3.setSeries(seriesDef);
        tab3.setXField("company");
        tab3.setExpressInstall("js/yui/assets/expressinstall.swf");
        //tab2.setWidth(500);
        //tab2.setHeight(400);

        // Creating fourth tab - SIMPLE TEXT

        Panel tab4 = new Panel();
        tab4.setTitle("About");
        tab4.setHtml("SuperbTab ODWA version is the greatest developement of the web.");
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
		System.out.println("#"+color);
		Ext.get(gridIdMenu.getView().getCell(contextCell[0], contextCell[1])).setStyle("color", "#"+color);
	}
}






/*
  	public void showVisualizationWindow(DBResult result) {

        // center panel
        TabPanel tabPanel = new TabPanel();
        tabPanel.setActiveTab(0);

        // TAB 1 - GRID

        final GridPanel tab1 = new GridPanel();
        tab1.setTitle("W tabelke");

        RecordDef recordDef = new RecordDef(
                new FieldDef[]{
                        new StringFieldDef("company"),
                        new FloatFieldDef("price"),
                        new FloatFieldDef("change"),
                        new FloatFieldDef("pctChange"),
                        new DateFieldDef("lastChanged", "n/j h:ia"),
                        new StringFieldDef("symbol"),
                        new StringFieldDef("industry")
                }
        );

        int numcols = result.getColumnCount();
          FieldDef[] table;
          for (int i=1; i<=numcols; i++)
          {
          	DBFieldDataType type = result.getColumnType(i);
          	if (type = String) {table[i] = new StringFieldDef(columns.getColumnName(i));} else
          	if (type = Double) {table[i] = new DoubleFieldDef(columns.getColumnName(i));} else
          	if (type = Date) {table[i] = new DateFieldDef(columns.getColumnName(i));}
          }
          RecordDef recordDef = new RecordDef(table);



        Object[][] data = getCompanyData();
        MemoryProxy proxy = new MemoryProxy(data);

        ArrayReader reader = new ArrayReader(recordDef);
        Store store = new Store(proxy, reader);
        store.load();
        tab1.setStore(store);

        BaseColumnConfig[] columns = new BaseColumnConfig[]{
        		new RowNumberingColumnConfig(),
                //column ID is company which is later used in setAutoExpandColumn
        		new ColumnConfig("Company", "company", 160, true, null, "company"),
                new ColumnConfig("Price", "price", 35),
                new ColumnConfig("Change", "change", 45),
                new ColumnConfig("% Change", "pctChange", 65),
                new ColumnConfig("Last Updated", "lastChanged", 65),
                new ColumnConfig("Industry", "industry", 60, true)
        };

         ColumnConfig[] columns;
           for (int i=1; i<=numcols; i++)
           {
           		columns[i] = (result.getColumnName, result.getColumnName(i), 80, true);
           }


        ColumnModel columnModel = new ColumnModel(columns);
        tab1.setColumnModel(columnModel);

        tab1.setFrame(true);
        tab1.setStripeRows(true);
        //tab1.setAutoExpandColumn("company");

        Toolbar bottomToolbar = new Toolbar();
        bottomToolbar.addFill();
        bottomToolbar.addButton(new ToolbarButton("Clear Sort", new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	tab1.clearSortState(true);
            }
        }));

        GridSearchPlugin gridSearch = new GridSearchPlugin(GridSearchPlugin.BOTTOM);
        gridSearch.setMode(GridSearchPlugin.LOCAL);
        tab1.addPlugin(gridSearch);

        GridFilter gf[] = new GridFilter[7];
        gf[0] = new GridStringFilter("company");
        gf[1] = new GridNumericFilter("price");
        gf[2] = new GridNumericFilter("change");
        gf[3] = new GridNumericFilter("pctChange");
        gf[4] = new GridDateFilter("lastChanged");
        gf[5] = new GridStringFilter("symbol");
        gf[6] = new GridListFilter("industry", new String[]{"Manufacturing", "Finance", "Services", "Computer", "Food", "Retail"});

        GridFilterPlugin gridFilterPlugin = new GridFilterPlugin(gf);
        gridFilterPlugin.setLocal(true);
        tab1.addPlugin(gridFilterPlugin);


        bottomToolbar.addButton(new ToolbarButton("Create Report", new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	ReportStyle style = new ReportStyle(gridIdMenu,cols,rows);
            }
        }));


        tab1.setBottomToolbar(bottomToolbar);


    	tab1.addGridCellListener(this);


        // TAB 2 - CIRCLE CHART

    	MemoryProxy proxy1 = new MemoryProxy(getCompanyData());
        RecordDef recordDef1 = new RecordDef(
                new FieldDef[]{
                        new StringFieldDef("response"),
                        new IntegerFieldDef("count")
                }
        );


        ArrayReader reader1 = new ArrayReader(recordDef1);
        final Store store1 = new Store(proxy1, reader1);
        store1.load();

        final PieChart tab2 = new PieChart();
        tab2.setTitle("W koleczko");
        tab2.setWMode("transparent");
        tab2.setStore(store1);
        tab2.setDataField("count");
        tab2.setCategoryField("response");

        tab2.setExpressInstall("js/yui/assets/expressinstall.swf");
        //tab2.setWidth(500);
        //tab2.setHeight(400);

        // TAB 3 - TEXT

        Panel tab3 = new Panel();
        tab3.setTitle("About");
        tab3.setHtml("SuperbTab ODWA version is the greatest developement of the web.");
        tab3.setAutoScroll(true);
        tab3.setClosable(true);

        tabPanel.add(tab1);
        tabPanel.add(tab2);
        tabPanel.add(tab3);

        BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);
        centerData.setMargins(3, 0, 3, 3);



        final Window window = new Window();
        window.setTitle("Layout Window");
        window.setClosable(true);
        window.setWidth(600);
        window.setHeight(350);
        window.setPlain(true);
        window.setLayout(new BorderLayout());
        window.add(tabPanel, centerData);
        window.setCloseAction(Window.HIDE);
        window.setCollapsible(true);

        window.show();
	}
 */




/*
// CHART

import com.google.gwt.core.client.EntryPoint;
import com.gwtext.client.data.*;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.chart.yui.PieChart;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class MyApplication implements EntryPoint {

    public void onModuleLoad() {

        Panel panel = new Panel();
        panel.setPaddings(15);

        panel.setLayout(new VerticalLayout(15));

        MemoryProxy proxy = new MemoryProxy(getData());
        RecordDef recordDef = new RecordDef(
                new FieldDef[]{
                        new StringFieldDef("response"),
                        new IntegerFieldDef("count"),
                        new StringFieldDef("legend")
                }
        );

        ArrayReader reader = new ArrayReader(recordDef);
        final Store store = new Store(proxy, reader);
        store.load();

        final PieChart chart = new PieChart();
        chart.setTitle("Survey Chart");
        chart.setWMode("transparent");
        chart.setStore(store);
        chart.setDataField("count");
        chart.setCategoryField("response");

        chart.setExpressInstall("js/yui/assets/expressinstall.swf");
        chart.setWidth(500);
        chart.setHeight(400);

        panel.add(chart);



        Viewport viewport = new Viewport(panel);
    }

    private static Object[][] getData() {
        return new Object[][]{
                new Object[]{"Summer", new Integer(564815), "#00b8bf"},
                new Object[]{"Fall", new Double(664182), "#8dd5e7"},
                new Object[]{"Spring", new Double(248124), "#c0fff6"},
                new Object[]{"Winter", new Double(271214), "#ffa928"},
                new Object[]{"Undecided", new Double(81845), "#edff9f"}
        };
    }
}


/*
// HERE BEGINS MOVEABLE PANEL WITH GRID
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Button;

import com.gwtext.client.data.*;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.core.EventObject;

import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.core.Function;
import com.gwtext.client.widgets.MessageBox;

//import com.google.gwt.user.client.ui.HorizontalPanel;
import com.gwtext.client.dd.DD;
//import com.gwtext.client.dd.DDProxy;
//import com.gwtext.client.widgets.Viewport;

public class MyApplication implements EntryPoint {
 public void onModuleLoad() {

     // definiujemy, jakie pola bedziemy mieli w tabelce
	 RecordDef recordDef = new RecordDef(
             new FieldDef[]{
                     new StringFieldDef("company"),
                     new FloatFieldDef("price"),
                     new FloatFieldDef("change"),
                     new FloatFieldDef("pctChange"),
                     new DateFieldDef("lastChanged", "n/j h:ia"),
                     new StringFieldDef("symbol"),
                     new StringFieldDef("industry")
             }
     );

	Ext.getBody().insertHtmlBeforeEnd("<div id='mydiv'>GTFO, we're visualizing here!</div>");
	final ExtElement myDiv = Ext.get("mydiv");
    myDiv.setStyle("margin", "10px");
    myDiv.setStyle("border", "1px dotted #F9AA44");
    myDiv.setStyle("width", "300px");
    myDiv.setStyle("padding", "5px");
    myDiv.highlight();


    final Panel mainVisualizationPanel = new Panel(); // panel w ktorym dokonujemy wizualizacji
    mainVisualizationPanel.setBorder(true);
    mainVisualizationPanel.setWidth(800);
    mainVisualizationPanel.setHeight(600);
    mainVisualizationPanel.setPaddings(15);


    // bedziemy tworzyc takie Panele dla kazdej wizualizacji, i na przyklad:
	//final Panel Visualization = new Panel();
	final GridPanel Visualization = new GridPanel();
	// Setting the Panel's properties
	Visualization.setTitle("The Visualization!");
	Visualization.setHeight(300);
	Visualization.setWidth(300);
	Visualization.setCollapsible(true);
	Visualization.setAnimCollapse(true);
	//Visualization.setHtml("<p>This is the visualization. Don't play with it, dude!");


    Object[][] data = getCompanyData();
    MemoryProxy proxy = new MemoryProxy(data);

    ArrayReader reader = new ArrayReader(recordDef);
    Store store = new Store(proxy, reader);
    store.load();
    Visualization.setStore(store);


    ColumnConfig[] columns = new ColumnConfig[]{
            //column ID is company which is later used in setAutoExpandColumn
            new ColumnConfig("Company", "company", 160, true, null, "company"),
            new ColumnConfig("Price", "price", 35),
            new ColumnConfig("Change", "change", 45),
            new ColumnConfig("% Change", "pctChange", 65),
            new ColumnConfig("Last Updated", "lastChanged", 65),
            new ColumnConfig("Industry", "industry", 60, true)
    };

    ColumnModel columnModel = new ColumnModel(columns);
    Visualization.setColumnModel(columnModel);

//    Visualization.setHeight(350);
//    Visualization.setWidth(600);
//    Visualization.setTitle("Array Grid");

    Toolbar bottomToolbar = new Toolbar();
    bottomToolbar.addFill();
    bottomToolbar.addButton(new ToolbarButton("Clear Sort", new ButtonListenerAdapter() {
        public void onClick(Button button, EventObject e) {
        	Visualization.clearSortState(true);
        }
    }));


    Visualization.setBottomToolbar(bottomToolbar);





	Visualization.addTool(new Tool(Tool.GEAR, new Function() {
		public void execute() {
			MessageBox.alert("Gear Tool", "This Panel is "
					+ (Visualization.isCollapsed() ? "" : "not") + " collapsed");
		}
	}, "Gear"));
	Visualization.addTool(new Tool(Tool.PIN, new Function() {
		public void execute() {
			if (Visualization.isFrame())
				Visualization.setFrame(false);
			else
				Visualization.setFrame(true);
		}
	}, "Pin"));
	Visualization.addTool(new Tool(Tool.TOGGLE, new Function() {
		public void execute() {
			if (Visualization.isCollapsed()) {
				Visualization.expand(true);
			} else {
				Visualization.collapse(true);
			}
		}
	}, "Collapse"));

	DD dd = new DD(Visualization);

	mainVisualizationPanel.add(Visualization);

	//Viewport viewport = new Viewport(mainVisualizationPanel);

	RootPanel.get().add(mainVisualizationPanel);

	}

 private Object[][] getCompanyData() {
     return new Object[][]{
             new Object[]{"3m Co", new Double(71.72), new Double(0.02),
                     new Double(0.03), "9/1 12:00am", "MMM", "Manufacturing"},
             new Object[]{"Alcoa Inc", new Double(29.01), new Double(0.42),
                     new Double(1.47), "9/1 12:00am", "AA", "Manufacturing"},
             new Object[]{"Altria Group Inc", new Double(83.81), new Double(0.28),
                     new Double(0.34), "9/1 12:00am", "MO", "Manufacturing"},
             new Object[]{"American Express Company", new Double(52.55), new Double(0.01),
                     new Double(0.02), "9/1 12:00am", "AXP", "Finance"},
             new Object[]{"American International Group, Inc.", new Double(64.13), new Double(0.31),
                     new Double(0.49), "9/1 12:00am", "AIG", "Services"},
             new Object[]{"AT&T Inc.", new Double(31.61), new Double(-0.48),
                     new Double(-1.54), "9/1 12:00am", "T", "Services"},
             new Object[]{"Boeing Co.", new Double(75.43), new Double(0.53),
                     new Double(0.71), "9/1 12:00am", "BA", "Manufacturing"},
             new Object[]{"Caterpillar Inc.", new Double(67.27), new Double(0.92),
                     new Double(1.39), "9/1 12:00am", "CAT", "Services"},
             new Object[]{"Citigroup, Inc.", new Double(49.37), new Double(0.02),
                     new Double(0.04), "9/1 12:00am", "C", "Finance"},
             new Object[]{"E.I. du Pont de Nemours and Company", new Double(40.48), new Double(0.51),
                     new Double(1.28), "9/1 12:00am", "DD", "Manufacturing"}
     };
 }
}
*/


/*
// HERE BEGINS BETTER MOVEABLE PANEL

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class LayoutWindowSample implements EntryPoint {

    public void onModuleLoad() {
        Panel panel = new Panel();
        panel.setBorder(false);
        panel.setPaddings(15);

        //center panel
        TabPanel tabPanel = new TabPanel();
        tabPanel.setActiveTab(0);

        Panel tab1 = new Panel();
        tab1.setTitle("Bogus Tab");
        tab1.setHtml(getBogusMarkup());
        tab1.setAutoScroll(true);

        Panel tab2 = new Panel();
        tab2.setTitle("Another Tab");
        tab2.setHtml(getBogusMarkup());
        tab2.setAutoScroll(true);

        Panel tab3 = new Panel();
        tab3.setTitle("Closable Tab");
        tab3.setHtml(getBogusMarkup());
        tab3.setAutoScroll(true);
        tab3.setClosable(true);

        tabPanel.add(tab1);
        tabPanel.add(tab2);
        tabPanel.add(tab3);

        //west panel
        Panel navPanel = new Panel();
        navPanel.setTitle("Navigation");
        navPanel.setWidth(200);
        navPanel.setCollapsible(true);

        BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);
        centerData.setMargins(3, 0, 3, 3);

        BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);
        westData.setSplit(true);
        westData.setMargins(3, 3, 0, 3);
        westData.setCMargins(3, 3, 3, 3);

        final Window window = new Window();
        window.setTitle("Layout Window");
        window.setClosable(true);
        window.setWidth(600);
        window.setHeight(350);
        window.setPlain(true);
        window.setLayout(new BorderLayout());
        window.add(tabPanel, centerData);
        window.add(navPanel, westData);
        window.setCloseAction(Window.HIDE);

        Button button = new Button("Show Window");
        button.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                window.show(button.getId());
            }
        });
        panel.add(button);

        RootPanel.get().add(panel);
    }

    private static String getBogusMarkup() {
        return "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                "Sed metus nibh, sodales a, porta at, vulputate eget, dui.  " +
                "In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, " +
                "cursus a, fringilla vel, urna.";
    }
}
*/







/*
// HERE BEGINS SIMPLE MOVEABLE PANEL
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;

import com.gwtext.client.widgets.Tool;
import com.gwtext.client.core.Function;
import com.gwtext.client.widgets.MessageBox;

//import com.google.gwt.user.client.ui.HorizontalPanel;
import com.gwtext.client.dd.DD;
//import com.gwtext.client.dd.DDProxy;
//import com.gwtext.client.widgets.Viewport;

public class MyApplication implements EntryPoint {
 public void onModuleLoad() {

	Ext.getBody().insertHtmlBeforeEnd("<div id='mydiv'>GTFO, we're visualizing here!</div>");
	final ExtElement myDiv = Ext.get("mydiv");
    myDiv.setStyle("margin", "10px");
    myDiv.setStyle("border", "1px dotted #F9AA44");
    myDiv.setStyle("width", "300px");
    myDiv.setStyle("padding", "5px");
    myDiv.highlight();


    final Panel mainVisualizationPanel = new Panel(); // panel w ktorym dokonujemy wizualizacji
    mainVisualizationPanel.setBorder(true);
    mainVisualizationPanel.setWidth(800);
    mainVisualizationPanel.setHeight(600);
    mainVisualizationPanel.setPaddings(15);


    // bedziemy tworzyc takie Panele dla kazdej wizualizacji, i na przyklad:
	final Panel Visualization = new Panel();
	// Setting the Panel's properties
	Visualization.setTitle("The Visualization!");
	Visualization.setHeight(300);
	Visualization.setWidth(300);
	Visualization.setCollapsible(true);
	Visualization.setAnimCollapse(true);
	Visualization.setHtml("<p>This is the visualization. Don't play with it, dude!");

	Visualization.addTool(new Tool(Tool.GEAR, new Function() {
		public void execute() {
			MessageBox.alert("Gear Tool", "This Panel is "
					+ (Visualization.isCollapsed() ? "" : "not") + " collapsed");
		}
	}, "Gear"));
	Visualization.addTool(new Tool(Tool.PIN, new Function() {
		public void execute() {
			if (Visualization.isFrame())
				Visualization.setFrame(false);
			else
				Visualization.setFrame(true);
		}
	}, "Pin"));
	Visualization.addTool(new Tool(Tool.TOGGLE, new Function() {
		public void execute() {
			if (Visualization.isCollapsed()) {
				Visualization.expand(true);
			} else {
				Visualization.collapse(true);
			}
		}
	}, "Collapse"));

	DD dd = new DD(Visualization);

	mainVisualizationPanel.add(Visualization);

	//Viewport viewport = new Viewport(mainVisualizationPanel);

	RootPanel.get().add(mainVisualizationPanel);

	}
}

*/

/*
// HERE BEGINS SIMPLE GRID

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.data.*;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.google.gwt.user.client.ui.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.core.EventObject;


public class MyApplication implements EntryPoint {

    public void onModuleLoad() {
        Panel panel = new Panel();
        panel.setBorder(false);
        panel.setPaddings(15);

        RecordDef recordDef = new RecordDef(
                new FieldDef[]{
                        new StringFieldDef("company"),
                        new FloatFieldDef("price"),
                        new FloatFieldDef("change"),
                        new FloatFieldDef("pctChange"),
                        new DateFieldDef("lastChanged", "n/j h:ia"),
                        new StringFieldDef("symbol"),
                        new StringFieldDef("industry")
                }
        );

        final GridPanel grid = new GridPanel();

        Object[][] data = getCompanyData();
        MemoryProxy proxy = new MemoryProxy(data);

        ArrayReader reader = new ArrayReader(recordDef);
        Store store = new Store(proxy, reader);
        store.load();
        grid.setStore(store);


        ColumnConfig[] columns = new ColumnConfig[]{
                //column ID is company which is later used in setAutoExpandColumn
                new ColumnConfig("Company", "company", 160, true, null, "company"),
                new ColumnConfig("Price", "price", 35),
                new ColumnConfig("Change", "change", 45),
                new ColumnConfig("% Change", "pctChange", 65),
                new ColumnConfig("Last Updated", "lastChanged", 65),
                new ColumnConfig("Industry", "industry", 60, true)
        };

        ColumnModel columnModel = new ColumnModel(columns);
        grid.setColumnModel(columnModel);

        grid.setFrame(true);
        grid.setStripeRows(true);
        grid.setAutoExpandColumn("company");

        grid.setHeight(350);
        grid.setWidth(600);
        grid.setTitle("Array Grid");

        Toolbar bottomToolbar = new Toolbar();
        bottomToolbar.addFill();
        bottomToolbar.addButton(new ToolbarButton("Clear Sort", new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                grid.clearSortState(true);
            }
        }));


        grid.setBottomToolbar(bottomToolbar);

        panel.add(grid);

        RootPanel.get().add(panel);
    }

    private Object[][] getCompanyData() {
        return new Object[][]{
                new Object[]{"3m Co", new Double(71.72), new Double(0.02),
                        new Double(0.03), "9/1 12:00am", "MMM", "Manufacturing"},
                new Object[]{"Alcoa Inc", new Double(29.01), new Double(0.42),
                        new Double(1.47), "9/1 12:00am", "AA", "Manufacturing"},
                new Object[]{"Altria Group Inc", new Double(83.81), new Double(0.28),
                        new Double(0.34), "9/1 12:00am", "MO", "Manufacturing"},
                new Object[]{"American Express Company", new Double(52.55), new Double(0.01),
                        new Double(0.02), "9/1 12:00am", "AXP", "Finance"},
                new Object[]{"American International Group, Inc.", new Double(64.13), new Double(0.31),
                        new Double(0.49), "9/1 12:00am", "AIG", "Services"},
                new Object[]{"AT&T Inc.", new Double(31.61), new Double(-0.48),
                        new Double(-1.54), "9/1 12:00am", "T", "Services"},
                new Object[]{"Boeing Co.", new Double(75.43), new Double(0.53),
                        new Double(0.71), "9/1 12:00am", "BA", "Manufacturing"},
                new Object[]{"Caterpillar Inc.", new Double(67.27), new Double(0.92),
                        new Double(1.39), "9/1 12:00am", "CAT", "Services"},
                new Object[]{"Citigroup, Inc.", new Double(49.37), new Double(0.02),
                        new Double(0.04), "9/1 12:00am", "C", "Finance"},
                new Object[]{"E.I. du Pont de Nemours and Company", new Double(40.48), new Double(0.51),
                        new Double(1.28), "9/1 12:00am", "DD", "Manufacturing"}
        };
    }
}*/