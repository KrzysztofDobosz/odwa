package org.pwr.odwa.common.dbtypes;

import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.UserSelection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("DBEngineService")
public interface DBEngineService extends RemoteService
{
   public DBResult executeQuery(UserSelection userSel);
   public String connect(String url, String user, String password);
}
