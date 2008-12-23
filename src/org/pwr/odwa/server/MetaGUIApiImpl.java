package org.pwr.odwa.server;

import org.pwr.odwa.common.MetaGUIApi;

import org.pwr.odwa.common.MetaID;
import org.pwr.odwa.common.MetaSlot;
import org.pwr.odwa.common.MetaDataView;
import org.pwr.odwa.common.MetaDimTable;
import org.pwr.odwa.common.MetaDim;
import org.pwr.odwa.common.MetaDimElement;
import org.pwr.odwa.common.MetaHierarchy;
import org.pwr.odwa.common.MetaMeasure;

import java.util.ArrayList;

public class MetaGUIApiImpl implements MetaGUIApi {
    public Object[] getSlots() {
        try {
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

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getSlots: " + ex);
            return null;
        }
    }

    public Object[] getDataViews(MetaID slot) {
        try {
            ArrayList<MetaDataView> list = new ArrayList<MetaDataView>();

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getDataViews: " + ex);
            return null;
        }
    }

    public Object[] getDimTables(MetaID view) {
        try {
            ArrayList<MetaDimTable> list = new ArrayList<MetaDimTable>();

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getDimTables: " + ex);
            return null;
        }
    }

    public Object[] getDimentions(MetaID table) {
        try {
            ArrayList<MetaDim> list = new ArrayList<MetaDim>();

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getDimensions: " + ex);
            return null;
        }
    }

    public Object[] getDimElements(MetaID dim) {
        try {
            ArrayList<MetaDimElement> list = new ArrayList<MetaDimElement>();

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getElements: " + ex);
            return null;
        }
    }

    public Object[] getHierarchies(MetaID view) {
        try {
            ArrayList<MetaHierarchy> list = new ArrayList<MetaHierarchy>();

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getHierarchies: " + ex);
            return null;
        }
    }

    public Object[] getMeasures(MetaID view) {
        try {
            ArrayList<MetaMeasure> list = new ArrayList<MetaMeasure>();

            return list.toArray();
        } catch (Exception ex) {
            System.err.println("ODWAServer: getMeasures: " + ex);
            return null;
        }
    }
}

