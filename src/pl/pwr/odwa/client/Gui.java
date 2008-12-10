package pl.pwr.odwa.client;

import java.util.ArrayList;

import pl.pwr.odwa.client.communication.*;
import pl.pwr.odwa.client.visualization.*;
import pl.pwr.odwa.result.DBResult;
import pl.pwr.odwa.selection.UserSelection;
import pl.pwr.odwa.server.metadata.MetaDataView;
import pl.pwr.odwa.server.metadata.MetaDim;
import pl.pwr.odwa.server.metadata.MetaDimElement;
import pl.pwr.odwa.server.metadata.MetaDimTable;
import pl.pwr.odwa.server.metadata.MetaHierarchy;
import pl.pwr.odwa.server.metadata.MetaMeasure;
import pl.pwr.odwa.server.metadata.MetaSlot;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

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
   private UserSelection currentSelection;
   /**
    * Aktualny rezultat zapytania do hurtowni.
    */
   private DBResult currentDBResult;
   /**
    * Nazwa uĹĽytkownika
    */
   private String userName;

   /**
    * Metoda do komunikacji z silnikiem baz danych. WywoĹ‚uje asynchronicznie
    * metodÄ™ DBEngine.executeQuery(UserSelection), a po uzyskaniu wyniku
    * wywoĹ‚uje funkcjÄ™ show(int, DBResult) moduĹ‚u Visualization.
    */
   void executeQuery()
   {
      dbService.executeQuery(currentSelection, new AsyncCallback<DBResult>()
      {
         public void onFailure(Throwable caught)
         {
            System.out.println("Gui: Query execution failed.");
         }

         public void onSuccess(DBResult result)
         {
            System.out.println("Gui: Query execution succeed.");
         }
      });
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * sloty dostÄ™pne dla danego uĹĽytkownika.
    *
    * @param userName
    *           nazwa uĹĽytkownika
    */
   void getSlots()
   {
      metaService.getSlots(userName, new AsyncCallback<ArrayList<MetaSlot>>()
      {
         public void onFailure(Throwable caught)
         {
            System.out.println("Gui: Getting slots failed.");
         }

         public void onSuccess(ArrayList<MetaSlot> result)
         {
            System.out.println("Gui: Getting slots succeed.");
         }
      });
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * widoki danych dostÄ™pne dla danego slotu.
    *
    * @param slot
    *           wybrany przez uĹĽytkownika slot
    */
   void getDataViews(MetaSlot slot)
   {
      metaService.getDataViews(slot,
            new AsyncCallback<ArrayList<MetaDataView>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting data views failed.");
               }

               public void onSuccess(ArrayList<MetaDataView> result)
               {
                  System.out.println("Gui: Getting data views succeed.");
               }
            });
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * tabelki wymiarĂłw dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   void getDimTables(MetaDataView view)
   {
      metaService.getDimTables(view,
            new AsyncCallback<ArrayList<MetaDimTable>>()
            {
               public void onFailure(Throwable caught)
               {
                  System.out.println("Gui: Getting dimention tables failed.");
               }

               public void onSuccess(ArrayList<MetaDimTable> result)
               {
                  System.out.println("Gui: Getting dimention tables succeed.");
               }
            });
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * wymiary dostÄ™pne dla danej tabelki wymiarĂłw.
    *
    * @param table
    *           tabelka wymiarĂłw
    */
   void getDimentions(MetaDimTable table)
   {
      metaService.getDimentions(table, new AsyncCallback<ArrayList<MetaDim>>()
      {
         public void onFailure(Throwable caught)
         {
            System.out.println("Gui: Getting dimentions failed.");
         }

         public void onSuccess(ArrayList<MetaDim> result)
         {
            System.out.println("Gui: Getting dimentions succeed.");
         }
      });
   }

   /**
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * miary dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   void getMeasures(MetaDataView view)
   {
      metaService.getMeasures(view, new AsyncCallback<ArrayList<MetaMeasure>>()
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
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * hierarchie dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   void getHierarchies(MetaDataView view)
   {
      metaService.getHierarchies(view,
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
    * Metoda do komunikacji z moduĹ‚em metadanych. WyĹ›wietla otrzymane od serwera
    * elementy wymiarĂłw dostÄ™pne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez uĹĽytkownika widok danych
    */
   void getDimElements(MetaDim dimention)
   {
      metaService.getDimElements(dimention,
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
      String metaRelURL = GWT.getModuleBaseURL() + "DBEngineService";
      metaEndpoint.setServiceEntryPoint(metaRelURL);

      System.out.println("Gui: ODWA system started.");

   }

}
