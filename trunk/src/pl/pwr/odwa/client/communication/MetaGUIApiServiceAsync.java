package pl.pwr.odwa.client.communication;

import java.util.ArrayList;

import pl.pwr.odwa.server.metadata.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetaGUIApiServiceAsync
{
   void getSlots(String userName, AsyncCallback<ArrayList<MetaSlot>> callback);
   void getDataViews(MetaSlot slot, AsyncCallback<ArrayList<MetaDataView>> callback);
   void getMembers(MetaDim dimention, AsyncCallback<ArrayList<MetaDimElement>> callback);
}
