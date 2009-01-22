package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

public class Dimension extends Meta {
    protected String m_table;
    protected String m_primary;
    protected String m_foreign;
    protected UID m_default;
    protected ArrayList<UID> m_hierarchies;

    public void setTable(String table) {
        m_table = table;
    }

    public void setPrimary(String primary) {
        m_primary = primary;
    }

    public void setForeign(String foreign) {
        m_foreign = foreign;
    }

    public void setDefault(UID def) {
        m_default = def;
    }

    public void setHierarchies(ArrayList<UID> hierarchies) {
        m_hierarchies = hierarchies;
    }

    public String getTable() {
        return m_table;
    }

    public String getPrimary() {
        return m_primary;
    }

    public String getForeign() {
        return m_foreign;
    }

    public UID getDefault() {
        return m_default;
    }

    public ArrayList<UID> getHierarchies() {
        return m_hierarchies;
    }
}

