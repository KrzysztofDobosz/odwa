package org.pwr.odwa.common.metadata;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetaGUIApiServiceAsync
{
   public void getSlots(String userName, AsyncCallback<ArrayList<MetaSlot>> callback);
   public void getDataViews(MetaID slotId, AsyncCallback<ArrayList<MetaDataView>> callback);
   public void getDimTables(MetaID viewId, AsyncCallback<ArrayList<MetaDimTable>> callback);
   public void getDimentions(MetaID tableId, AsyncCallback<ArrayList<MetaDim>> callback);
   public void getMeasures(MetaID viewId, AsyncCallback<ArrayList<MetaMeasure>> callback);
   public void getHierarchies(MetaID viewId, AsyncCallback<ArrayList<MetaHierarchy>> callback);
   public void getDimElements(MetaID dimentionId, AsyncCallback<ArrayList<MetaDimElement>> callback);
   public void getDataView(MetaID viewId, AsyncCallback<String> callback);
}