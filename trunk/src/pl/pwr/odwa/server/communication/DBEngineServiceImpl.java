package pl.pwr.odwa.server.communication;

import pl.pwr.odwa.client.communication.DBEngineService;
import pl.pwr.odwa.selection.UserSelection;
import pl.pwr.odwa.server.engine.DBResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DBEngineServiceImpl extends RemoteServiceServlet implements DBEngineService {

   public DBResult executeQuery(UserSelection userSel)
   {
      System.out.println("");
      return null;
   }

}
