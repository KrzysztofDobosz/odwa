package pl.pwr.odwa.server.metadata;

import java.util.ArrayList;

public class MetaGUIApi
{
   public ArrayList<MetaSlot> getSlots(String userName)
   {
      System.out.println("MetaGUIApi: getSlots() executed");
      return null;
   }

   public ArrayList<MetaDataView> getDataViews(MetaSlot slot)
   {
      System.out.println("MetaGUIApi: getDataViews() executed");
      return slot.getDataViews();
   }

   public ArrayList<MetaDimElement> getDimElements(MetaDim dimention)
   {
      System.out.println("MetaGUIApi: getDimElements() executed");
      return dimention.getDimElements();
   }

   public ArrayList<MetaDimTable> getDimTables(MetaDataView view)
   {
      System.out.println("MetaGUIApi: getDimTables() executed");
      return view.getDimTables();
   }

   public ArrayList<MetaDim> getDimentions(MetaDimTable table)
   {
      System.out.println("MetaGUIApi: getDimentions() executed");
      return table.getDimensions();
   }

   public ArrayList<MetaHierarchy> getHierarchies(MetaDataView view)
   {
      System.out.println("MetaGUIApi: getHierarchies() executed");
      return view.getHierarchies();
   }

   public ArrayList<MetaMeasure> getMeasures(MetaDataView view)
   {
      System.out.println("MetaGUIApi: getMeasures() executed");
      return view.getMeasures();
   }

}
