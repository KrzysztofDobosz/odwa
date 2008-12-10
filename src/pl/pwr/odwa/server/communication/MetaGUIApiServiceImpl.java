package pl.pwr.odwa.server.communication;

import java.util.ArrayList;

import pl.pwr.odwa.client.communication.MetaGUIApiService;
import pl.pwr.odwa.server.metadata.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MetaGUIApiServiceImpl extends RemoteServiceServlet implements
      MetaGUIApiService
{

   public ArrayList<MetaSlot> getSlots(String userName)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaDataView> getDataViews(MetaSlot slot)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaDimElement> getMembers(MetaDim dimention)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaDimElement> getDimElements(MetaDim dimention)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaDimTable> getDimTables(MetaDataView view)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaDim> getDimentions(MetaDimTable table)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaHierarchy> getHierarchies(MetaDataView view)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<MetaMeasure> getMeasures(MetaDataView view)
   {
      // TODO Auto-generated method stub
      return null;
   }

}
