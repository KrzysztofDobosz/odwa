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
import com.gwtext.client.widgets.form.Label;

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
   }

}
