package org.pwr.odwa.server;

import org.pwr.odwa.common.dbtypes.DBEngineService;
import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.server.engine.DBEngine;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DBEngineServiceImpl extends RemoteServiceServlet implements DBEngineService {

   public DBResult executeQuery(UserSelection userSel)
   {
      DBEngine dbEn = new DBEngine();
      return dbEn.executeQuery(userSel);
   }

}
