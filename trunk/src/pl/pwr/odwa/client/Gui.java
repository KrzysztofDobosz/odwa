package pl.pwr.odwa.client;

import java.util.ArrayList;

import pl.pwr.odwa.client.communication.*;
import pl.pwr.odwa.client.visualization.*;
import pl.pwr.odwa.selection.UserSelection;
import pl.pwr.odwa.server.engine.DBResult;
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
 * Klasa odpowiadająca za interakcję z użytkownikiem. Klasa nie udostępnia
 * żadnych metod innym modułom. Do komunikacji z pozostałymi modułami używa
 * publicznych funkcji instancji klas.
 *
 * @author Łukasz Pintal
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
    * Interface do komunikacji RPC z modułem metadanych.
    */
   private MetaGUIApiServiceAsync metaService;

   /**
    * Instancja klasy odpowiedzialnej za wyświetlanie wyników zapytania do
    * hurtowni.
    */
   private Visualization display;
   /**
    * Aktualny widok metadanych, wybrany przez użytkownika.
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
    * Nazwa użytkownika
    */
   private String userName;

   /**
    * Metoda do komunikacji z silnikiem baz danych. Wywołuje asynchronicznie
    * metodę DBEngine.executeQuery(UserSelection), a po uzyskaniu wyniku
    * wywołuje funkcję show(int, DBResult) modułu Visualization.
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * sloty dostępne dla danego użytkownika.
    *
    * @param userName
    *           nazwa użytkownika
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * widoki danych dostępne dla danego slotu.
    *
    * @param slot
    *           wybrany przez użytkownika slot
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * tabelki wymiarów dostępne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez użytkownika widok danych
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * wymiary dostępne dla danej tabelki wymiarów.
    *
    * @param table
    *           tabelka wymiarów
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * miary dostępne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez użytkownika widok danych
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * hierarchie dostępne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez użytkownika widok danych
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
    * Metoda do komunikacji z modułem metadanych. Wyświetla otrzymane od serwera
    * elementy wymiarów dostępne dla danego widoku danych.
    *
    * @param view
    *           wybrany przez użytkownika widok danych
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
    * Wyświetla cały interface użytkownika.
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
