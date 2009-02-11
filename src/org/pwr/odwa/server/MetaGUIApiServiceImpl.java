package org.pwr.odwa.server;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;
import org.pwr.odwa.server.metadata.Metadata;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MetaGUIApiServiceImpl extends RemoteServiceServlet implements
		MetaGUIApiService
{

	public ArrayList<MetaSlot> getSlots(String userName)
	{
		System.out.println("ODWAServer: MetaGUIApi: getSlots executed");
		ArrayList<MetaSlot> list = new ArrayList<MetaSlot>();

		MetaSlot sl0 = new MetaSlot();
		sl0.setID(new MetaID(0));
		sl0.setName("Slot #0");
		sl0.setNotes("Notes for #0");
		list.add(sl0);

		MetaSlot sl1 = new MetaSlot();
		sl1.setID(new MetaID(1));
		sl1.setName("Slot #1");
		sl1.setNotes("Notes for #1");
		list.add(sl1);

		MetaSlot sl2 = new MetaSlot();
		sl2.setID(new MetaID(2));
		sl2.setName("Slot #2");
		sl2.setNotes("Notes for #2");
		list.add(sl2);

		return list;

	}

	public ArrayList<MetaDataView> getDataViews(MetaID slotId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getDataViews executed");
		try
		{
			ArrayList<MetaDataView> list = new ArrayList<MetaDataView>();

			MetaDataView view = new MetaDataView();
			view.setID(new MetaID(slotId.getID() * 3 + 5));
			view.setName("View " + view.getID().getID());

			list.add(view);

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getDataViews: " + ex);
			return null;
		}
	}

	public String getDataView(MetaID viewId)
	{
		Metadata meta = new Metadata();
		meta.loadMetadata(null);
	    return meta.getMetadataTree();
	}

}

