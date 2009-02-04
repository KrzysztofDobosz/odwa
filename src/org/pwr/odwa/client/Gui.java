package org.pwr.odwa.client;

import java.util.ArrayList;

import org.pwr.odwa.client.guiUtils.SelectionPanel;
import org.pwr.odwa.client.reports.DynamicReport;
import org.pwr.odwa.client.reports.StaticReport;
import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.client.visualization.Visualization;

import org.pwr.odwa.client.visualization.*;
import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.result.DBRow;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.common.dbtypes.DBEngineService;
import org.pwr.odwa.common.dbtypes.DBEngineServiceAsync;
import org.pwr.odwa.common.metadata.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.NodeTraversalCallback;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.dd.DragData;
import com.gwtext.client.dd.DragDrop;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.DropNodeCallback;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeEditor;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreePanelListenerAdapter;

/**
 * Class responsible for interaction with user. Creates users selection and
 * displays results of a query.
 *
 * @author Lukasz Pintal
 * @author Krzysztof Dobosz
 *
 */
public class Gui implements EntryPoint
{
	private DBEngineServiceAsync dbService;
	/**
	 * Interface for communication via RPC with metadata module.
	 */
	private MetaGUIApiServiceAsync metaService;
	private DBResult currentDBResult = new DBResult();
	/**
	 * Users login.
	 */
	private String userName;

	/**
	 * Displayed data warehouse view (marketplace).
	 */
	private String currentViewXml;

	/**
	 * GWT EXT panel responsible for displaying selection composition tools.
	 */
	private final MainPanel panel = new MainPanel();

	/**
	 * Lists of data slots and views, filled when communicating with metadata
	 * module.
	 */
	private ArrayList<MetaSlot> slotList;
	private ArrayList<MetaDataView> viewList;

	/**
	 *
	 */

	public void saveReport()
	{/*
		 * System.out.println("ODWAClient: Gui: saveReport() executed");
		 * //ReportStyle style = display.getReportStyle(); StaticReport sRep =
		 * new StaticReport(currentDBResult, style); try { sRep.save(""); }
		 * catch (Exception exception) { System.err.println("ODWAClient: " +
		 * exception); } DynamicReport dRep = new
		 * DynamicReport(currentSelection, style); try { dRep.save(""); } catch
		 * (Exception exception) { System.err.println("ODWAClient: " +
		 * exception); }
		 */
	}

	public void staticReportLoad()
	{
		/*
		 * try { StaticReport report = new StaticReport(""); currentDBResult =
		 * report.getResult(); ReportStyle style = report.getReportStyle();
		 * display.show(currentDBResult, style); } catch (Exception exception) {
		 * System.err.println("ODWAClient: " + exception); }
		 */
	}

	public void dynamicReportLoad()
	{/*
		 * try { DynamicReport report = new DynamicReport(""); currentSelection =
		 * report.getUserSelection(); ReportStyle style =
		 * report.getReportStyle(); } catch (Exception exception) {
		 * System.err.println("ODWAClient: " + exception); }
		 */
	}

	/**
	 * Analogical to Main() method, starts the program and displays main panel.
	 */
	public void onModuleLoad()
	{
		slotList = new ArrayList<MetaSlot>();
		viewList = new ArrayList<MetaDataView>();

		dbService = (DBEngineServiceAsync) GWT.create(DBEngineService.class);
		ServiceDefTarget dbEndpoint = (ServiceDefTarget) dbService;
		String dbRelURL = GWT.getModuleBaseURL() + "DBEngineService";
		dbEndpoint.setServiceEntryPoint(dbRelURL);

		metaService = (MetaGUIApiServiceAsync) GWT.create(MetaGUIApiService.class);
		ServiceDefTarget metaEndpoint = (ServiceDefTarget) metaService;
		String metaRelURL = GWT.getModuleBaseURL() + "MetaGUIApiService";
		metaEndpoint.setServiceEntryPoint(metaRelURL);

		panel.login();
		//panel.getSlots();
		// createSelection();

		Viewport viewport = new Viewport(panel);
	}

	/**
	 * Class responsible for displaying all tools for loading, crating selection
	 * and visualisation.
	 *
	 * @author Lukasz Pintal
	 * @author Krzysztof Dobosz
	 *
	 */
	class MainPanel extends Panel
	{
		private Panel northPanel;
		private final Panel westPanel;
		private Panel easthPanel;
		private Panel centerPanel;
		private final FormPanel viewForm;
		private SelectionPanel selectionPanel;

		private final ComboBox slotCB;
		private final ComboBox viewCB;

		/**
		 * Creator of MainPanel, sets parameters for displaying gwt-ext panels,
		 * makes instances of all subpanels and buttons and displays them.
		 */
		public MainPanel()
		{
			setBorder(false);
			setLayout(new FitLayout());

			Panel borderPanel = new Panel();
			borderPanel.setLayout(new BorderLayout());
			selectionPanel = new SelectionPanel();

			northPanel = new Panel();
			northPanel.setHeight(50);
			northPanel.setCollapsible(true);
			northPanel.setLayout(new HorizontalLayout(10));
			northPanel.setTitle("Open Data Warehouse Analysis Tool");

			westPanel = new Panel();
			westPanel.setTitle("Settings");
			westPanel.setCollapsible(true);
			westPanel.setLayout(new VerticalLayout(15));
			westPanel.setPaddings(10);
			westPanel.setWidth(350);

			BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);
			westData.setSplit(true);
			westData.setMinSize(175);
			westData.setMaxSize(400);
			westData.setMargins(new Margins(0, 5, 0, 0));

			Panel buttonPanel = new Panel();
			buttonPanel.setPaddings(15);
			buttonPanel.setLayout(new HorizontalLayout(5));

			Button loadButton = new Button("LOAD", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
				}
			});

			Button saveButton = new Button("SAVE", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
				}
			});

			Button execButton = new Button("EXECUTE", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{

					UserSelection selection = new UserSelection();
					selection.load(selectionPanel.loadSelection());
					executeQuery(selection);

				}

			});

			Button logoffButton = new Button("LOG OFF", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
				}
			});

			Button resetButton = new Button("RESET", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
					selectionPanel.reset();
				}
			});
			Button previewButton = new Button("PREVIEW", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
					MessageBox.alert(selectionPanel.loadSelection().toString());
				}
			});

			viewForm = new FormPanel();
			viewForm.setTitle("Select view");
			viewForm.setFrame(true);
			viewForm.setWidth(320);

			slotCB = new ComboBox();
			viewCB = new ComboBox();

			slotCB.setFieldLabel("Select universe");
			slotCB.setDisplayField("universe");
			slotCB.setMode(ComboBox.LOCAL);
			slotCB.setTriggerAction(ComboBox.ALL);
			slotCB.setForceSelection(true);
			slotCB.setValueField("cid");
			slotCB.setReadOnly(true);

			viewCB.setFieldLabel("Select view");
			viewCB.setDisplayField("view");
			viewCB.setValueField("id");
			viewCB.setMode(ComboBox.LOCAL);
			viewCB.setTriggerAction(ComboBox.ALL);
			viewCB.setLinked(false);
			viewCB.setForceSelection(true);
			viewCB.setReadOnly(true);

			viewForm.add(slotCB);
			viewForm.add(viewCB);

			westPanel.add(viewForm);

			Button LoadViewButton = new Button("LOAD VIEW", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
					loadView(new MetaID(new Long(viewCB.getValue())));
				}
			});

			westPanel.add(LoadViewButton);

			westPanel.setButtonAlign(Position.CENTER);
			westPanel.setButtons(new Button[]
			{ loadButton, saveButton, logoffButton });

			centerPanel = new Panel();
			centerPanel.setTitle("Selection");
			centerPanel.setCollapsible(true);
			centerPanel.setButtons(new Button[]
			{ resetButton, previewButton, execButton });
			centerPanel.setButtonAlign(Position.RIGHT);
			centerPanel.add(selectionPanel);
			selectionPanel.setVisible(false);

			borderPanel.add(northPanel, new BorderLayoutData(RegionPosition.NORTH));
			borderPanel.add(westPanel, westData);
			borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));

			add(borderPanel);

		}

		/**
		 * Returns north panel - the one holding informations and titles.
		 *
		 * @return Panel
		 */
		public Panel getNorthPanel()
		{
			return northPanel;
		}

		/**
		 * Returns west panel - with selection of data view.
		 *
		 * @return Panel
		 */
		public Panel getWestPanel()
		{
			return westPanel;
		}

		/**
		 * Returns East panel - with menu, not implemented yet.
		 *
		 * @return Panel
		 */
		public Panel getEasthPanel()
		{
			return easthPanel;
		}

		/**
		 * Returns center panel - containing data and tools for creating
		 * selection.
		 *
		 * @return Panel
		 */
		public Panel getCenterPanel()
		{
			return centerPanel;
		}

		/**
		 * Logs user, in current version a stub, loads only user name. Disables
		 * whole main panel untill a username is typed.
		 */
		private void login()
		{
			final Panel welcomePanel = new Panel();
			welcomePanel.setWidth(200);
			welcomePanel.setFrame(false);
			northPanel.add(welcomePanel);

			final Window window = new Window();
			window.setTitle("Log In");
			window.setClosable(false);
			window.setWidth(350);
			window.setHeight(180);
			window.setPlain(true);
			window.setModal(true);
			window.setLayout(new VerticalLayout());
			window.setPaddings(15);
			window.setCloseAction(Window.HIDE);

			final FormPanel loginPanel = new FormPanel();

			loginPanel.setWidth(300);
			loginPanel.setLabelWidth(70);
			loginPanel.setPaddings(15);

			final TextField login = new TextField("Login", "login", 200);
			login.setAllowBlank(false);
			loginPanel.add(login);

			TextField passwd = new TextField("Password", "passwd", 200);
			passwd.setAllowBlank(false);
			passwd.setPassword(true);
			loginPanel.add(passwd);

			Button okButton = new Button("OK", new ButtonListenerAdapter()
			{
				public void onClick(Button button, EventObject e)
				{
					userName = login.getText();
					if (userName != "")
					{

						welcomePanel.setHtml("welcome, <b>" + userName + "</b>");

						window.close();
						getSlots();

					}

				}
			});
			window.setButtons(new Button[]
			{ okButton });

			window.add(loginPanel);

			window.show();

		}

		/**
		 * Loads data slot list (previously loaded from metadata module) into
		 * combobox and displays it.
		 */
		private void setSlots()
		{

			final Object[][] universes = new Object[slotList.size()][2];

			for (int i = 0; i < slotList.size(); i++)
				universes[i] = new Object[]
				{ slotList.get(i).getID().toString(), slotList.get(i).getName() };

			Store universesStore = new SimpleStore(new String[]
			{ "cid", "universe" }, universes);

			universesStore.load();

			slotCB.setStore(universesStore);
			slotCB.show();
		}

		/**
		 * Loads data view from metadata module, using MetaGuiApiService
		 * function
		 *
		 * @see getDataView Result (String with XML tree) is displayed via
		 *      SelectionPanel
		 *
		 * @param id
		 *            a MetaID of data view to achieve
		 */
		private void loadView(MetaID id)
		{
			metaService.getDataView(new MetaID(10), new AsyncCallback<String>()
			{
				public void onFailure(Throwable caught)
				{
					MessageBox.alert("Could not achieve view XML");
				}

				public void onSuccess(String result)
				{
					showView(result);

				}
			});
		}

		/**
		 * Displays loaded view (XML tree in a string) via SelectionPanel method
		 *
		 * @see loadXml.
		 *
		 * @param viewXml
		 *            String containing XML tree generated by metadata module
		 */
		private void showView(String viewXml)
		{
			selectionPanel.loadXml(viewXml);
			selectionPanel.setVisible(true);
		}

		/**
		 * Loads data view list for possible slots and displays it using
		 * combobox.
		 */
		private void setViews()
		{
			Object[][] views = new Object[viewList.size()][3];

			for (int i = 0; i < viewList.size(); i++)
				views[i] = new Object[]
				{ viewList.get(i).getID().getID(), viewList.get(i).getParentSlot().toString(),
						viewList.get(i).getName() };

			final Store viewsStore = new SimpleStore(new String[]
			{ "id", "cid", "view" }, views);
			viewsStore.load();

			viewCB.setStore(viewsStore);

			slotCB.addListener(new ComboBoxListenerAdapter()
			{

				public void onSelect(ComboBox comboBox, Record record, int index)
				{
					viewCB.setValue("");
					viewsStore.filter("cid", comboBox.getValue());
				}
			});

			viewCB.show();
			slotCB.show();

		}

		/**
		 * Communicates asynchronously with metadata module and loads data slots
		 * list aviable for current user.
		 */
		public void getSlots()
		{

			metaService.getSlots(userName, new AsyncCallback<ArrayList<MetaSlot>>()
			{
				public void onFailure(Throwable caught)
				{
					MessageBox.confirm("Confirm", "Slots are empty!!!!");

				}

				public void onSuccess(ArrayList<MetaSlot> result)
				{

					for (MetaSlot slot : result)
					{
						slotList.add(slot);
					}

					setSlots();

					for (MetaSlot slot : slotList)
					{
						getDataViews(slot.getID());
					}
				}
			});

		}

		/**
		 * Communicates asynchronously with metadata module and loads data view
		 * list aviable for current user and selected slot.
		 *
		 * @param slot
		 *            selected slots MetaID
		 */
		public void getDataViews(final MetaID slotId)
		{
			System.out.println("ODWAClient: Gui: getDataView() executed");

			final Object[][] views = new Object[viewList.size()][3];

			metaService.getDataViews(slotId, new AsyncCallback<ArrayList<MetaDataView>>()
			{
				public void onFailure(Throwable caught)
				{
					MessageBox.alert("Cannot communicate with metadata!");
				}

				public void onSuccess(ArrayList<MetaDataView> result)
				{

					for (MetaDataView view : result)
					{
						view.setParentSlot(slotId);
						viewList.add(view);
					}
					setViews();

				}
			});

		}

		/**
		 * Method responsible for communicating with database engine. Using
		 * asynchronous communication, passes current user selection and gets
		 * result of database query it contained. Result is displayed using
		 * Visualization class instance.
		 *
		 * @param selection
		 *            current user selection
		 */
		public void executeQuery(final UserSelection selection)
		{
			dbService.executeQuery(selection, new AsyncCallback<DBResult>()
			{
				public void onFailure(Throwable caught)
				{
					MessageBox.alert(caught.getMessage());
				}

				public void onSuccess(DBResult result)
				{
					Visualization vis = new Visualization();
					vis.show(result);
				}
			});
		}
	}

}
