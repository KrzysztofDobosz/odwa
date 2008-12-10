package pl.pwr.odwa.client.communication;

import pl.pwr.odwa.result.DBResult;
import pl.pwr.odwa.selection.UserSelection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

@RemoteServiceRelativePath("DBEngineService")
public interface DBEngineService extends RemoteService
{
   public DBResult executeQuery(UserSelection userSel);
}
