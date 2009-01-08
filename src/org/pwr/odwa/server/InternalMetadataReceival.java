package org.pwr.odwa.server;

import org.pwr.odwa.server.engine.DBEngine;

public class InternalMetadataReceival
{

   /**
    * @param args
    */
   public static void main(String[] args)
   {
      DBEngine dbEn = new DBEngine();
      dbEn.receiveMetadata("");

   }

}
