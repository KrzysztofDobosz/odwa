package org.pwr.odwa.server;

import org.pwr.odwa.common.metadata.DBHash;
import org.pwr.odwa.common.metadata.MetaElement;
import org.pwr.odwa.server.structure.DBStructure;

public class MetaSQLApi
{
   public DBHash getStructureHash(String str)
   {
      System.out.println("ODWAServer: MetaSQLApi: getStructureHash executed");
      return new DBHash();
   }

   public void refreshMetaData(DBStructure dbStruct)
   {
      System.out.println("ODWAServer: MetaSQLApi: refreshMetaData executed");
   }

   public Object translateMetaElement(MetaElement el)
   {
      System.out.println("ODWAServer: MetaSQLApi: translateMetaElement executed");
      return null;
   }
}
