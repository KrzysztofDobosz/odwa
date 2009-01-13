package org.pwr.odwa.client;

import java.util.ArrayList;



import org.pwr.odwa.client.reports.DynamicReport;
import org.pwr.odwa.client.reports.StaticReport;
import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.client.visualization.Visualization;

import org.pwr.odwa.client.visualization.*;
import org.pwr.odwa.common.result.DBResult;
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
 * Klasa odpowiadajÄ…ca za interakcjÄ™ z uĹĽytkownikiem. Klasa nie udostÄ™pnia
 * ĹĽadnych metod innym moduĹ‚om. Do komunikacji z pozostaĹ‚ymi moduĹ‚ami uĹĽywa
 * publicznych funkcji instancji klas.
 *
 * @author Ĺ�ukasz Pintal
 * @author Krzysztof Dobosz
 *
 */
public class Gui implements EntryPoint
{
   /**
    * Interface do komunikacji RPC z silnikiem bazy danych.
    */
   private DBEngineServiceAsync dbService;
   /**
    * Interface do komunikacji RPC z moduĹ‚em metadanych.
    */
   private MetaGUIApiServiceAsync metaService;

   /**
    * Instancja klasy odpowiedzialnej za wyĹ›wietlanie wynikĂłw zapytania do
    * hurtowni.
    */
   private Visualization display;
   /**
    * Aktualny widok metadanych, wybrany przez uĹĽytkownika.
    */
   private MetaDataView currentView;
   /**
    * Aktualnie wybrane opcje do tworzenia zapytania do hurtowni.
    */
   private UserSelection currentSelection = new UserSelection();
   /**
    * Aktualny rezultat zapytania do hurtowni.
    */
   private DBResult currentDBResult = new DBResult();
   /**
    * Nazwa uĹĽytkownika
    */
   private String userName;

	private Menu menu;
	private TreeNode ctxNode;
	private TreeEditor treeEditor;

   private Label l = new Label("staring");
   private Label k = new Label("staring");

   /**
    * Metoda do komunikacji z silnikiem baz danych. WywoĹ‚uje asynchronicznie
    * metodÄ™ DBEngine.executeQuery(UserSelection), a po uzyskaniu wyniku
    * wywoĹ‚uje funkcjÄ™ show(int, DBResult) moduĹ‚u Visualization.
    */

   public void executeQuery()
   {
      dbService.executeQuery(currentSelection, new AsyncCallback<DBResult>()
      {
         public void onFailure(Throwable caught)
         {
            l.setText("ODWAClient: Gui: Query execution failed.");
            //System.out.println("ODWAClient: Gui: Query execution failed.");
         }

         public void onSuccess(DBResult result)
         {
            l.setText("ODWAClient: Gui: Query execution succeed.");
            //System.out.println("ODWAClient: Gui: Query execution succeed.");
            display.show((DBResult) result, new ReportStyle());
         }
      });
   }

   public void saveReport()
   {
      System.out.println("ODWAClient: Gui: saveReport() executed");
      ReportStyle style = display.getReportStyle();
      StaticReport sRep = new StaticReport(currentDBResult, style);
      try
      {
         sRep.save("");
      }
      catch (Exception exception)
      {
         System.err.println("ODWAClient: " + exception);
      }
      DynamicReport dRep = new DynamicReport(currentSelection, style);
      try
      {
         dRep.save("");
      }
      catch (Exception exception)
      {
         System.err.println("ODWAClient: " + exception);
      }
   }

   public void staticReportLoad()
   {
      try
      {
         StaticReport report = new StaticReport("");
         currentDBResult = report.getResult();
         ReportStyle style = report.getReportStyle();
         display.show(currentDBResult, style);
      }
      catch (Exception exception)
      {
         System.err.println("ODWAClient: " + exception);
      }
   }

   public void dynamicReportLoad()
   {
      try
      {
         DynamicReport report = new DynamicReport("");
         currentSelection = report.getUserSelection();
         ReportStyle style = report.getReportStyle();
      }
      catch (Exception exception)
      {
         System.err.println("ODWAClient: " + exception);
      }
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera sloty dostÄ™pne dla danego uĹĽytkownika.
    *
    * @param userName
    *           nazwa uĹĽytkownika
    */
   public void getSlots()
   {
      System.out.println("ODWAClient: Gui: getSlots() executed");

      metaService.getSlots(userName, new AsyncCallback<ArrayList<MetaSlot>>()
      {
         public void onFailure(Throwable caught)
         {
            k.setText("Gui: Getting slots failed.");
            //System.out.println("Gui: Getting slots failed.");
         }

         public void onSuccess(ArrayList<MetaSlot> result)
         {
            k.setText("Gui: Getting slots succeed.");
            //System.out.println("Gui: Getting slots succeed.");
            getDataViews(new MetaID(0));
         }
      });

   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera widoki danych dostÄ™pne dla danego slotu.
    *
    * @param slot
    *           wybrany przez uĹĽytkownika slot
    */
   public void getDataViews(MetaID slotId)
   {
      System.out.println("ODWAClient: Gui: getDataView() executed");
      metaService.getDataViews(slotId,
            new AsyncCallback<ArrayList<MetaDataView>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting data views failed.");
               }

               public void onSuccess(ArrayList<MetaDataView> result)
               {
                  System.out.println("Gui: Getting data views succeed.");
                  getDimTables(new MetaID(0));
                  getMeasures(new MetaID(0));
                  getHierarchies(new MetaID(0));
               }
            });

   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera tabelki wymiarĂłw dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   public void getDimTables(MetaID viewId)
   {
      System.out.println("ODWAClient: Gui: getDimTables() executed");
      metaService.getDimTables(viewId,
            new AsyncCallback<ArrayList<MetaDimTable>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting dimention tables failed.");
               }

               public void onSuccess(ArrayList<MetaDimTable> result)
               {
                  System.out.println("Gui: Getting dimention tables succeed.");
                  getDimentions(new MetaID(0));
               }
            });

   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera wymiary dostÄ™pne dla danej tabelki wymiarĂłw.
    *
    * @param table
    *           tabelka wymiarĂłw
    */
   public void getDimentions(MetaID tableId)
   {
      System.out.println("ODWAClient: Gui: getDimentions() executed");
      metaService.getDimentions(tableId,
            new AsyncCallback<ArrayList<MetaDim>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting dimentions failed.");
               }

               public void onSuccess(ArrayList<MetaDim> result)
               {
                  System.out.println("Gui: Getting dimentions succeed.");
                  getDimElements(new MetaID(0));
               }
            });

   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera miary dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   public void getMeasures(MetaID viewId)
   {
      System.out.println("ODWAClient: Gui: getMeasures() executed");
      metaService.getMeasures(viewId,
            new AsyncCallback<ArrayList<MetaMeasure>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting measures failed.");
               }

               public void onSuccess(ArrayList<MetaMeasure> result)
               {
                  System.out.println("Gui: Getting measures succeed.");
               }
            });

   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera hierarchie dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   public void getHierarchies(MetaID viewId)
   {
      System.out.println("ODWAClient: Gui: getHierarchies() executed");
      metaService.getHierarchies(viewId,
            new AsyncCallback<ArrayList<MetaHierarchy>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting hierarchies failed.");
               }

               public void onSuccess(ArrayList<MetaHierarchy> result)
               {
                  System.out.println("Gui: Getting hierarchies succeed.");
               }
            });

   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera elementy wymiarĂłw dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   public void getDimElements(MetaID dimentionId)
   {
      System.out.println("ODWAClient: Gui: getDimElements() executed");

      metaService.getDimElements(dimentionId,
            new AsyncCallback<ArrayList<MetaDimElement>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting dimention elements failed.");
               }

               public void onSuccess(ArrayList<MetaDimElement> result)
               {
                  System.out
                        .println("Gui: Getting dimention elements succeed.");
               }
            });

   }

   /**
    * Metoda analogiczna do main(). Jest uruchamiana przy starcie programu.
    * WyĹ›wietla caĹ‚y interface uĹĽytkownika.
    */

   public void onModuleLoad()
   {
      dbService = (DBEngineServiceAsync) GWT.create(DBEngineService.class);
      ServiceDefTarget dbEndpoint = (ServiceDefTarget) dbService;
      String dbRelURL = GWT.getModuleBaseURL() + "DBEngineService";
      dbEndpoint.setServiceEntryPoint(dbRelURL);

      metaService = (MetaGUIApiServiceAsync) GWT
            .create(MetaGUIApiService.class);
      ServiceDefTarget metaEndpoint = (ServiceDefTarget) metaService;
      String metaRelURL = GWT.getModuleBaseURL() + "MetaGUIApiService";
      metaEndpoint.setServiceEntryPoint(metaRelURL);

      RootPanel.get().add(l);
      RootPanel.get().add(k);

      System.out.println("Gui: ODWA system started.");
      executeQuery();
      getSlots();

      Panel panel = new Panel();
		panel.setBorder(false);
		// panel.setPaddings(15);
		panel.setLayout(new FitLayout());

		Panel borderPanel = new Panel();
		borderPanel.setLayout(new BorderLayout());

		Panel buttonPanel = new Panel();
		buttonPanel.setPaddings(15);
		buttonPanel.setLayout(new HorizontalLayout(5));

		// add north panel
		final Panel northPanel = new Panel();
		northPanel.setHeight(50);
		northPanel.setCollapsible(true);
		northPanel.setLayout(new HorizontalLayout(10));
		northPanel.setTitle("Open Data Warehouse Analysis Tool");

		Panel westPanel = new Panel();
		// westPanel.setHtml("<p>SETTINGS</p>");
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

		Button loadButton = new Button("LOAD", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
			}
		});

		Button saveButton = new Button("SAVE", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
			}
		});

		Button execButton = new Button("EXECUTE", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				MessageBox.confirm("Confirm",
						"This is where yhe report will appear",
						new MessageBox.ConfirmCallback() {
							public void execute(String btnID) {
							}
						});
			}

		});

		Button logoffButton = new Button("LOG OFF",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
					}
				});

		Button resetButton = new Button("RESET", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
			}
		});

		Button loadViewButton = new Button("LOAD VIEW",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
					}
				});


		westPanel.setButtonAlign(Position.CENTER);
		westPanel.setButtons(new Button[] { loadButton, saveButton,
				logoffButton });

		Panel centerPanel = new Panel();
		// centerPanel.setHtml("<p>REPORT PANEL</p>");
		centerPanel.setTitle("Selection");
		centerPanel.setCollapsible(true);
		// centerPanel.setLayout(new BorderLayout());
		centerPanel.setButtons(new Button[] { resetButton, execButton });
		centerPanel.setButtonAlign(Position.RIGHT);

		borderPanel.add(northPanel, new BorderLayoutData(RegionPosition.NORTH));
		borderPanel.add(westPanel, westData);
		borderPanel.add(centerPanel,
				new BorderLayoutData(RegionPosition.CENTER));

		panel.add(borderPanel);

		/**
		 * LOGIN WINDOW
		 */

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

		Button okButton = new Button("OK", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				userName = login.getText();
				if (userName != "") {

					welcomePanel.setHtml("welcome, <b>" + userName + "</b>");

					window.close();
				}

			}
		});
		window.setButtons(new Button[] { okButton });

		window.add(loginPanel);

		window.show();

		/**
		 * SELECT VIEW
		 */

		// westPanel.removeFromParent();
		FormPanel viewForm = new FormPanel();

		viewForm.setTitle("Select view");
		viewForm.setFrame(true);
		viewForm.setWidth(320);

		// slots store
		Object[][] universes = new Object[][] {
				new Object[] { "U1", "Universe 1" },
				new Object[] { "U2", "Universe 2" },
				new Object[] { "U3", "Universe 3" } };

		final Store universesStore = new SimpleStore(new String[] { "cid",
				"universe" }, universes);
		universesStore.load();

		// views store
		Object[][] views = new Object[][] {
				new Object[] { new Integer(1), "U1", "Finances" },
				new Object[] { new Integer(2), "U1", "Human resources" },
				new Object[] { new Integer(3), "U1", "Trade" },
				new Object[] { new Integer(4), "U1", "Transportation" },
				new Object[] { new Integer(5), "U2", "Human resources" },
				new Object[] { new Integer(6), "U2", "Research" },
				new Object[] { new Integer(7), "U3", "Menagement" },
				new Object[] { new Integer(8), "U3", "Finances" },
				new Object[] { new Integer(9), "U3", "Research" } };

		final Store viewsStore = new SimpleStore(new String[] { "id", "cid",
				"view" }, views);
		viewsStore.load();

		final ComboBox slotCB = new ComboBox();
		slotCB.setFieldLabel("Select universe");
		slotCB.setStore(universesStore);
		slotCB.setDisplayField("universe");
		slotCB.setMode(ComboBox.LOCAL);
		slotCB.setTriggerAction(ComboBox.ALL);
		slotCB.setForceSelection(true);
		slotCB.setValueField("cid");
		slotCB.setReadOnly(true);

		final ComboBox viewCB = new ComboBox();
		viewCB.setFieldLabel("Select view");
		viewCB.setStore(viewsStore);
		viewCB.setDisplayField("view");
		viewCB.setValueField("id");
		viewCB.setMode(ComboBox.LOCAL);
		viewCB.setTriggerAction(ComboBox.ALL);
		viewCB.setLinked(true);
		viewCB.setForceSelection(true);
		viewCB.setReadOnly(true);

		slotCB.addListener(new ComboBoxListenerAdapter() {

			public void onSelect(ComboBox comboBox, Record record, int index) {
				viewCB.setValue("");
				viewsStore.filter("cid", comboBox.getValue());
			}
		});

		viewForm.add(slotCB);
		viewForm.add(viewCB);
		westPanel.add(viewForm);
		westPanel.add(loadViewButton);

		/*
		 * TREE TO TREE DRAG AND DROP
		 */

		// create source countries tree
		final TreePanel treePanel = new TreePanel();
		treePanel.setTitle("Structure");
		//treePanel.setAnimate(true);
		treePanel.setFrame(false);
		treePanel.setEnableDD(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(true);
		treePanel.setWidth(300);
		treePanel.setHeight(400);
		treePanel.setSelectionModel(new MultiSelectionModel());

		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl("data/metadata.xml");
		loader.setMethod(Connection.GET);
		loader.setRootTag("countries");
		loader.setFolderTitleMapping("@title");
		loader.setFolderTag("continent");
		loader.setLeafTitleMapping("@title");
		loader.setLeafTag("country");
		loader.setQtipMapping("@qtip");

		AsyncTreeNode root = new AsyncTreeNode("Countries", loader);
		treePanel.setRootNode(root);
		root.expand();
		treePanel.expandAll();

		// create target selection tree
		final TreePanel rowsTreePanel = new SelectionTreePanel();
		rowsTreePanel.setAnimate(true);
		rowsTreePanel.setContainerScroll(true);
		rowsTreePanel.setRootVisible(true);
		rowsTreePanel.setWidth(300);
		rowsTreePanel.setHeight(400);

		TextField field = new TextField();
		field.setSelectOnFocus(true);
		treeEditor = new TreeEditor(rowsTreePanel, field);

		/*
		 * final XMLTreeLoader tripLoader = new XMLTreeLoader();
		 * tripLoader.setDataUrl("data/rows.xml");
		 * tripLoader.setMethod(Connection.GET);
		 * tripLoader.setRootTag("vacations");
		 * tripLoader.setFolderIdMapping("@title");
		 * tripLoader.setFolderTag("trip"); tripLoader.setQtipMapping("@qtip");
		 * tripLoader.setAttributeMappings(new String[] { "@trip" });
		 *
		 * final AsyncTreeNode tripRoot = new AsyncTreeNode("User selection",
		 * tripLoader);
		 *
		 * rowsTreePanel.setRootNode(tripRoot);
		 */
		// add trip tree listener that handles move / copy logic
		rowsTreePanel.addListener(new TreePanelListenerAdapter() {
			public void onRender(Component component) {
				// rowsTreePanel.getRootNode().expand();
				// rowsTreePanel.expandAll();
			}

			public boolean doBeforeNodeDrop(TreePanel treePanel,
					TreeNode target, DragData dragData, String point,
					DragDrop source, TreeNode dropNode,
					DropNodeCallback dropDropNodeCallback) {
				if ("true".equals(target.getAttribute("trip"))) {
					TreeNode copyNode = dropNode.cloneNode();
					Node[] children = copyNode.getChildNodes();
					for (int i = 0; i < children.length; i++) {
						Node child = children[i];
						copyNode.removeChild(child);
					}
					dropDropNodeCallback.setDropNode(copyNode);

				}
				return true;
			}

			public void onContextMenu(TreeNode node, EventObject e) {
				int[] xy = e.getXY();
				showContextMenu(node, e);
			}
		});

		Panel horizontalPanel = new Panel();
		horizontalPanel.setLayout(new HorizontalLayout(20));
		horizontalPanel.setPaddings(15);
		horizontalPanel.add(treePanel);
		horizontalPanel.add(rowsTreePanel);

		centerPanel.add(horizontalPanel);

		Viewport viewport = new Viewport(panel);
	}

	class SelectionTreePanel extends TreePanel {

		public SelectionTreePanel() {

			TreeNode root = new TreeNode("User selection:");
			root.setExpanded(true);

			TreeNode columns = new TreeNode("COLUMNS");
			root.appendChild(columns);

			TreeNode rows = new TreeNode("ROWS");
			root.appendChild(rows);

			TreeNode backg = new TreeNode("BACKGROUND");
			root.appendChild(backg);

			setTitle("Selection");
			setWidth(200);
			setHeight(400);
			setEnableDD(true);
			setRootNode(root);
		}
	}

	private void showContextMenu(final TreeNode node, EventObject e) {
		if (menu == null) {
			menu = new Menu();
			Item editItem = new Item("Edit", new BaseItemListenerAdapter() {
				public void onClick(BaseItem item, EventObject e) {
					treeEditor.startEdit(ctxNode);
				}
			});
			editItem.setId("edit-item");
			menu.addItem(editItem);

			Item disableItem = new Item("Disable",
					new BaseItemListenerAdapter() {
						public void onClick(BaseItem item, EventObject e) {
							ctxNode.disable();
							ctxNode.cascade(new NodeTraversalCallback() {
								public boolean execute(Node node) {
									((TreeNode) node).disable();
									return true;
								}
							});
						}
					});
			disableItem.setId("disable-item");
			menu.addItem(disableItem);

			Item enableItem = new Item("Enable", new BaseItemListenerAdapter() {
				public void onClick(BaseItem item, EventObject e) {
					ctxNode.enable();
					ctxNode.cascade(new NodeTraversalCallback() {
						public boolean execute(Node node) {
							((TreeNode) node).enable();
							return true;
						}
					});
				}
			});
			enableItem.setId("enable-item");
			menu.addItem(enableItem);

			Item removeItem = new Item("Remove", new BaseItemListenerAdapter() {
				public void onClick(BaseItem item, EventObject e) {

					ctxNode.cascade(new NodeTraversalCallback() {
						public boolean execute(Node node) {
							((TreeNode) node).remove();
							return true;
						}
					});
					ctxNode.remove();
				}
			});
			removeItem.setId("remove-item");
			menu.addItem(removeItem);

			Item cloneItem = new Item("Clone", new BaseItemListenerAdapter() {
				public void onClick(BaseItem item, EventObject e) {
					TreeNode clone = ctxNode.cloneNode();
					clone.setText("Copy of " + clone.getText());
					ctxNode.getParentNode().appendChild(clone);
					treeEditor.startEdit(clone);
				}
			});
			cloneItem.setId("clone-item");
			menu.addItem(cloneItem);

			Item newFolderItem = new Item("New Folder",
					new BaseItemListenerAdapter() {
						public void onClick(BaseItem item, EventObject e) {
							TreeNode newFolder = new TreeNode("New Folder");
							ctxNode.getParentNode().appendChild(newFolder);
							treeEditor.startEdit(newFolder);
						}
					});
			newFolderItem.setId("newfolder-item");
			menu.addItem(newFolderItem);
		}

		if (ctxNode != null) {
			ctxNode = null;
		}
		ctxNode = node;

		if (ctxNode.isDisabled()) {
			menu.getItem("disable-item").disable();
			menu.getItem("enable-item").enable();
		} else {
			menu.getItem("disable-item").enable();
			menu.getItem("enable-item").disable();
		}
		menu.showAt(e.getXY());

   }

}
