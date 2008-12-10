package pl.pwr.odwa.server.communication;

import java.util.ArrayList;

import pl.pwr.odwa.client.communication.MetaGUIApiService;
import pl.pwr.odwa.server.metadata.MetaDataView;
import pl.pwr.odwa.server.metadata.MetaDim;
import pl.pwr.odwa.server.metadata.MetaDimElement;
import pl.pwr.odwa.server.metadata.MetaSlot;

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

}
