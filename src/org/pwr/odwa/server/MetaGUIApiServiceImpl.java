package org.pwr.odwa.server;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MetaGUIApiServiceImpl extends RemoteServiceServlet implements
      MetaGUIApiService
{

   public ArrayList<MetaSlot> getSlots(String userName){
      System.out.println("ODWAServer: MetaGUIApi: getSlots executed");
       try {
           ArrayList<MetaSlot> list = new ArrayList<MetaSlot>();

           MetaSlot sl0 = new MetaSlot();
           sl0.setID(new MetaID(0));
           sl0.setName("Slot #0");
           sl0.setNotes("Notes for #0");
           list.add(sl0);

           MetaSlot sl1 = new MetaSlot();
           sl1.setID(new MetaID(1));
           sl1.setName("Slot #1");
           sl1.setNotes("Notes for #1");
           list.add(sl1);

           MetaSlot sl2 = new MetaSlot();
           sl2.setID(new MetaID(2));
           sl2.setName("Slot #2");
           sl2.setNotes("Notes for #2");
           list.add(sl2);

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getSlots: " + ex);
           return null;
       }
   }

   public ArrayList<MetaDataView> getDataViews(MetaID slotId){
      System.out.println("ODWAServer: MetaGUIApi: getDataViews executed");
       try {
           ArrayList<MetaDataView> list = new ArrayList<MetaDataView>();

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getDataViews: " + ex);
           return null;
       }
   }

   public ArrayList<MetaDimTable> getDimTables(MetaID viewId){
      System.out.println("ODWAServer: MetaGUIApi: getDimTables executed");
       try {
           ArrayList<MetaDimTable> list = new ArrayList<MetaDimTable>();

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getDimTables: " + ex);
           return null;
       }
   }

   public ArrayList<MetaDim> getDimentions(MetaID tableId){
      System.out.println("ODWAServer: MetaGUIApi: getDimentions executed");
       try {
           ArrayList<MetaDim> list = new ArrayList<MetaDim>();

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getDimensions: " + ex);
           return null;
       }
   }

   public ArrayList<MetaDimElement> getDimElements(MetaID dimentionId){
      System.out.println("ODWAServer: MetaGUIApi: getDimElements executed");
       try {
           ArrayList<MetaDimElement> list = new ArrayList<MetaDimElement>();

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getElements: " + ex);
           return null;
       }
   }

   public ArrayList<MetaHierarchy> getHierarchies(MetaID viewId){
      System.out.println("ODWAServer: MetaGUIApi: getHierarchies executed");
       try {
           ArrayList<MetaHierarchy> list = new ArrayList<MetaHierarchy>();

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getHierarchies: " + ex);
           return null;
       }
   }

   public ArrayList<MetaMeasure> getMeasures(MetaID viewId){
      System.out.println("ODWAServer: MetaGUIApi: getMeasures executed");
       try {
           ArrayList<MetaMeasure> list = new ArrayList<MetaMeasure>();

           return list;
       } catch (Exception ex) {
           System.err.println("ODWAServer: getMeasures: " + ex);
           return null;
       }
   }

}
