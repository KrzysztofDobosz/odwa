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

   private String currentViewXml;

   private final MainPanel panel = new MainPanel();

   private ArrayList<MetaSlot> slotList;
   private ArrayList<MetaDataView> viewList;

   // private FormPanel viewForm;

   private Label l = new Label("starting");
   private Label k = new Label("starting");

   /**
    * Metoda do komunikacji z silnikiem baz danych. WywoĹ‚uje asynchronicznie
    * metodÄ™ DBEngine.executeQuery(UserSelection), a po uzyskaniu wyniku
    * wywoĹ‚uje funkcjÄ™ show(int, DBResult) moduĹ‚u Visualization.
    */

   public void saveReport()
   {/*
       * System.out.println("ODWAClient: Gui: saveReport() executed");
       * //ReportStyle style = display.getReportStyle(); StaticReport sRep = new
       * StaticReport(currentDBResult, style); try { sRep.save(""); } catch
       * (Exception exception) { System.err.println("ODWAClient: " + exception); }
       * DynamicReport dRep = new DynamicReport(currentSelection, style); try {
       * dRep.save(""); } catch (Exception exception) {
       * System.err.println("ODWAClient: " + exception); }
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
       * report.getUserSelection(); ReportStyle style = report.getReportStyle(); }
       * catch (Exception exception) { System.err.println("ODWAClient: " +
       * exception); }
       */
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
    * serwera sloty dostÄ™pne dla danego uĹĽytkownika.
    *
    * @param userName
    *           nazwa uĹĽytkownika
    */

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
      slotList = new ArrayList<MetaSlot>();
      viewList = new ArrayList<MetaDataView>();

      dbService = (DBEngineServiceAsync) GWT.create(DBEngineService.class);
      ServiceDefTarget dbEndpoint = (ServiceDefTarget) dbService;
      String dbRelURL = GWT.getModuleBaseURL() + "DBEngineService";
      dbEndpoint.setServiceEntryPoint(dbRelURL);

      metaService = (MetaGUIApiServiceAsync) GWT
            .create(MetaGUIApiService.class);
      ServiceDefTarget metaEndpoint = (ServiceDefTarget) metaService;
      String metaRelURL = GWT.getModuleBaseURL() + "MetaGUIApiService";
      metaEndpoint.setServiceEntryPoint(metaRelURL);

      // MainPanel panel = new MainPanel();

      display = new Visualization();

      // panel.login();
      panel.getSlots();
      // createSelection();

      Viewport viewport = new Viewport(panel);
   }

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
               /*
                * MessageBox.confirm("Confirm", "This is where yhe report will
                * appear", new MessageBox.ConfirmCallback() { public void
                * execute(String btnID) { } });
                */
               executeQuery();
               // display.show(new DBResult());

            }

         });

         Button logoffButton = new Button("LOG OFF",
               new ButtonListenerAdapter()
               {
                  public void onClick(Button button, EventObject e)
                  {
                  }
               });

         Button resetButton = new Button("RESET", new ButtonListenerAdapter()
         {
            public void onClick(Button button, EventObject e)
            {
               MessageBox.alert(selectionPanel.loadSelection().toString());
            }
         });
         Button previewButton = new Button("PREVIEW",
               new ButtonListenerAdapter()
               {
                  public void onClick(Button button, EventObject e)
                  {
                     MessageBox
                           .alert(selectionPanel.loadSelection().toString());
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

         Button LoadViewButton = new Button("LOAD VIEW",
               new ButtonListenerAdapter()
               {
                  public void onClick(Button button, EventObject e)
                  {
                     loadView(new MetaID(new Long(viewCB.getValue())));
                     // selectionPanel.setVisible(true);
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

         borderPanel
               .add(northPanel, new BorderLayoutData(RegionPosition.NORTH));
         borderPanel.add(westPanel, westData);
         borderPanel.add(centerPanel, new BorderLayoutData(
               RegionPosition.CENTER));

         add(borderPanel);

      }

      public Panel getNorthPanel()
      {
         return northPanel;
      }

      public Panel getWestPanel()
      {
         return westPanel;
      }

      public Panel getEasthPanel()
      {
         return easthPanel;
      }

      public Panel getCenterPanel()
      {
         return centerPanel;
      }

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

      private void showView(String viewXml)
      {

         selectionPanel.loadXml(viewXml);
         selectionPanel.setVisible(true);
         // selectionPanel.show();
      }

      private void setViews()
      {
         Object[][] views = new Object[viewList.size()][3];

         for (int i = 0; i < viewList.size(); i++)
            views[i] = new Object[]
            { viewList.get(i).getID().getID(),
                  viewList.get(i).getParentSlot().toString(),
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

      public void getSlots()
      {

         metaService.getSlots(userName,
               new AsyncCallback<ArrayList<MetaSlot>>()
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

      private void showSelectionPanel(MetaID id)
      {

      }

      /**
       * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od
       * serwera widoki danych dostÄ™pne dla danego slotu.
       *
       * @param slot
       *           wybrany przez uĹĽytkownika slot
       */
      public void getDataViews(final MetaID slotId)
      {
         System.out.println("ODWAClient: Gui: getDataView() executed");

         final Object[][] views = new Object[viewList.size()][3];

         metaService.getDataViews(slotId,
               new AsyncCallback<ArrayList<MetaDataView>>()
               {
                  public void onFailure(Throwable caught)
                  {
                     k.setText("Gui: Getting data views failed.");
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

      public void executeQuery()
      {
         dbService.executeQuery(currentSelection, new AsyncCallback<DBResult>()
         {
            public void onFailure(Throwable caught)
            {
               MessageBox.alert(caught.getMessage());
            }

            public void onSuccess(DBResult result)
            {
               DBRow row;
               String out = "";
               int count = result.getColumnCount();
               for (int i = 0; i < count; i++)
               {
                  out = out.concat(result.getColumnName(i) + " ");
                  System.out.print(result.getColumnName(i) + " ");
               }
               out = out.concat("\n");
               System.out.println();

               while ((row = result.fetchRow()) != null)
               {
                  for (int i = 0; i < count; i++)
                  {
                     out = out.concat(row.getFieldVal(i).toString() + " ");
                     System.out.print(row.getFieldVal(i).toString() + " ");
                  }
                  out = out.concat("\n");
                  System.out.println();
               }
               MessageBox.alert(out);
               display.show((DBResult) result);
            }
         });
      }
   }

}
