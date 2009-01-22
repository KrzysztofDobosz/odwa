package org.pwr.odwa.common.metadata;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetaGUIApiServiceAsync
{
   public void getSlots(String userName, AsyncCallback<ArrayList<MetaSlot>> callback);
   public void getDataViews(MetaID slotId, AsyncCallback<ArrayList<MetaDataView>> callback);
   public void getDataView(MetaID viewId, AsyncCallback<String> callback);
}