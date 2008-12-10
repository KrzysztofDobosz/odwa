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
   public ArrayList<MetaSlot> getSlots(String userName);
   public ArrayList<MetaDataView> getDataViews(MetaSlot slot);
   public ArrayList<MetaDimTable> getDimTables(MetaDataView view);
   public ArrayList<MetaDim> getDimentions(MetaDimTable table);
   public ArrayList<MetaMeasure> getMeasures(MetaDataView view);
   public ArrayList<MetaHierarchy> getHierarchies(MetaDataView view);
   public ArrayList<MetaDimElement> getDimElements(MetaDim dimention);
}
