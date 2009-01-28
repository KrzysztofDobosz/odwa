package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

/**
 * Stores internal information of a dimension.
 *
 * This class is an image of 'dimension' tag from metadata XML,
 * giving a complete information set about a single dimension
 * in a data warehouse project.
 * <p>
 * The information includes:
 * <ul>
 * <li>table - the name of table containing data for this dimension</li>
 * <li>primary - the name of the primary key of dimension's table</li>
 * <li>foreign - the foreign key in facts table to which links this dimension</li>
 * <li>default - UID of the default hierarchy in this dimension</li>
 * <li>hierarchies - an array of hierarchy UIDs that belongs to this dimension</li>
 * </ul>
 * <p>
 * This component extends {@link Meta} class inheriting basic meta
 * element properties from it (unique ID, name and description).
 */
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

