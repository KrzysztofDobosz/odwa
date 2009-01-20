package org.pwr.odwa.server;

import org.pwr.odwa.common.dbtypes.DBEngineService;
import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.server.engine.DBEngine;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DBEngineServiceImpl extends RemoteServiceServlet implements
      DBEngineService
{
   private DBEngine dbEn = new DBEngine();

   public DBResult executeQuery(UserSelection userSel)
   {
      // FIXME: DO KRZYŚKA: nim poprawisz komunikację, musisz tutaj wywołać
      // connect() (tak jak rozmawialiśmy)
      // poniżej example:
      /*
       *
       * DBEngine db = new DBEngine(); db.connect("jdbc:mysql://localhost/odwa",
       * "odwa", "odwa"); DBResult res = db.executeQuery(userSel);
       */

      return dbEn.executeQuery(userSel);
   }

   public String connect(String url, String user, String password)
   {
      dbEn.connect(url, user, password);
      return "";
   }

}