package pl.pwr.odwa.client.communication;

import java.util.ArrayList;

import pl.pwr.odwa.server.metadata.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

@RemoteServiceRelativePath("MetaGUIApiService")
public interface MetaGUIApiService extends RemoteService
{
   ArrayList<MetaSlot> getSlots(String userName);
   ArrayList<MetaDataView> getDataViews(MetaSlot slot);
   ArrayList<MetaDimElement> getMembers(MetaDim dimention);
}
