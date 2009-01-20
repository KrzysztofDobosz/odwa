package org.pwr.odwa.common.dbtypes;

import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.UserSelection;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBEngineServiceAsync
{
   public void executeQuery(UserSelection userSel, AsyncCallback<DBResult> callback);
   public void connect(String url, String user, String password, AsyncCallback<String> callback);
}
