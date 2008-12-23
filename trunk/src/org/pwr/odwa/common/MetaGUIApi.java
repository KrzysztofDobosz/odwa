package org.pwr.odwa.common;

import java.util.ArrayList;

public interface MetaGUIApi {
    public Object[] getSlots();
    public Object[] getDataViews(MetaID slot);
    public Object[] getDimTables(MetaID view);
    public Object[] getDimentions(MetaID table);
    public Object[] getDimElements(MetaID dim);
    public Object[] getHierarchies(MetaID view);
    public Object[] getMeasures(MetaID view);
}

