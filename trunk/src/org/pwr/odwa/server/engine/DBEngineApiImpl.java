package org.pwr.odwa.server.engine;

import java.util.ArrayList;

import org.pwr.odwa.common.dbtypes.DBEngineApi;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.common.result.DBResult;

public class DBEngineApiImpl implements DBEngineApi
{
   DBEngine dbEn = new DBEngine();

   public Object[] executeQuery(UserSelection userSel)
   {
      System.out.println("ODWAServer: DBEngineApi: executeQuery executed");
      try {
         ArrayList<DBResult> list = new ArrayList<DBResult>();
         list.add(dbEn.executeQuery(userSel));
         return list.toArray();
     } catch (Exception ex) {
         System.err.println("ODWAServer: executeQuery: " + ex);
         return null;
     }
   }

}
