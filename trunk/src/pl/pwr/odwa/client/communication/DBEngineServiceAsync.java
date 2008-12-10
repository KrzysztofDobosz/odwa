package pl.pwr.odwa.client.communication;

import pl.pwr.odwa.client.reports.UserSelection;
import pl.pwr.odwa.server.engine.DBResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBEngineServiceAsync
{
   void executeQuery(UserSelection userSel, AsyncCallback<DBResult> callback);
}
