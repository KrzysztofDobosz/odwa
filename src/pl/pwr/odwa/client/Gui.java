package pl.pwr.odwa.client;

import pl.pwr.odwa.client.communication.*;
import pl.pwr.odwa.client.reports.UserSelection;
import pl.pwr.odwa.client.visualization.*;
import pl.pwr.odwa.server.engine.DBResult;
import pl.pwr.odwa.server.metadata.MetaDataView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class Gui implements EntryPoint
{
   private DBEngineServiceAsync dbService;
   private MetaGUIApiServiceAsync metaService;

   private Visualization display;
   private MetaDataView currentView;
   private UserSelection currentSelection;
   private DBResult currentDBResult;
   private String userName;

   void executeQuery()
   {
      dbService.executeQuery(currentSelection, new AsyncCallback<DBResult>()
      {
         public void onFailure(Throwable caught)
         {
            // TODO Auto-generated method stub

         }

         public void onSuccess(DBResult result)
         {
            // TODO Auto-generated method stub

         }
      });
   }

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

   }

}
