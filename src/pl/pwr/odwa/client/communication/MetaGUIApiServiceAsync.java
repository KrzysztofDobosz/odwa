package pl.pwr.odwa.client.communication;

import java.util.ArrayList;

import pl.pwr.odwa.server.metadata.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetaGUIApiServiceAsync
{
   public void getSlots(String userName, AsyncCallback<ArrayList<MetaSlot>> callback);
   public void getDataViews(MetaSlot slot, AsyncCallback<ArrayList<MetaDataView>> callback);
   public void getDimTables(MetaDataView view, AsyncCallback<ArrayList<MetaDimTable>> callback);
   public void getDimentions(MetaDimTable table, AsyncCallback<ArrayList<MetaDim>> callback);
   public void getMeasures(MetaDataView view, AsyncCallback<ArrayList<MetaMeasure>> callback);
   public void getHierarchies(MetaDataView view, AsyncCallback<ArrayList<MetaHierarchy>> callback);
   public void getDimElements(MetaDim dimention, AsyncCallback<ArrayList<MetaDimElement>> callback);
}
