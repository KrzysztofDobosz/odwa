package org.pwr.odwa.common.metadata;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("MetaGUIApiService")
public interface MetaGUIApiService extends RemoteService
{
   public ArrayList<MetaSlot> getSlots(String userName);
   public ArrayList<MetaDataView> getDataViews(MetaID slotId);
   public String getDataView(MetaID viewId);
}