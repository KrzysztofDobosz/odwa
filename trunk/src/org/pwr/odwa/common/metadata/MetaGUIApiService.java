package org.pwr.odwa.common.metadata;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("MetaGUIApiService")
public interface MetaGUIApiService extends RemoteService
{
   public ArrayList<MetaSlot> getSlots(String userName);
   public ArrayList<MetaDataView> getDataViews(MetaID slotId);
   public ArrayList<MetaDimTable> getDimTables(MetaID viewId);
   public ArrayList<MetaDim> getDimentions(MetaID tableId);
   public ArrayList<MetaMeasure> getMeasures(MetaID viewId);
   public ArrayList<MetaHierarchy> getHierarchies(MetaID viewId);
   public ArrayList<MetaDimElement> getDimElements(MetaID dimentionId);
   public String getDataView(MetaID viewId);
}