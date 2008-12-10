package pl.pwr.odwa.client.communication;

import pl.pwr.odwa.client.reports.UserSelection;
import pl.pwr.odwa.server.engine.DBResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

@RemoteServiceRelativePath("DBEngineService")
public interface DBEngineService extends RemoteService
{
   DBResult executeQuery(UserSelection userSel);
}
