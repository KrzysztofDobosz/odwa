package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

/**
 * Stores internal information of a level.
 *
 * This class is an image of 'level' tag from metadata XML,
 * giving a complete information set about a single level
 * in a data warehouse project for a given hierarchy.
 * <p>
 * The information includes:
 * <ul>
 * <li>field - the name of a field in dimension table to which this level maps</li>
 * <li>dtype - database dependent type of field in dimension table (to make mysql happy)</li>
 * <li>default - UID of the default member in this level (usually the first one)</li>
 * <li>nextlevel - UID of the previous level in the given hierarchy</li>
 * <li>nextlevel - UID of the next level in the given hierarchy</li>
 * <li>dimension - UID of a dimension to which this level belongs</li>
 * <li>hierarchy - UID of a hierarchy to which this level belongs</li>
 * <li>members - an array of level's member UIDs</li>
 * </ul>
 * <p>
 * This component extends {@link Meta} class inheriting basic meta
 * element properties from it (unique ID, name and description).
 */
public class Level extends Meta {
    protected String m_field;
    protected String m_dtype;
    protected UID m_default;
    protected UID m_prevlevel;
    protected UID m_nextlevel;
    protected UID m_dimension;
    protected UID m_hierarchy;
    protected ArrayList<UID> m_members;

    public void setField(String field) {
        m_field = field;
    }

    public void setDType(String dtype) {
        m_dtype = dtype;
    }

    public void setDefault(UID def) {
        m_default = def;
    }

    public void setPrevLevel(UID prevlevel) {
        m_prevlevel = prevlevel;
    }

    public void setNextLevel(UID nextlevel) {
        m_nextlevel = nextlevel;
    }

    public void setDimension(UID dimension) {
        m_dimension = dimension;
    }

    public void setHierarchy(UID hierarchy) {
        m_hierarchy = hierarchy;
    }

    public void setMembers(ArrayList<UID> members) {
        m_members = members;
    }

    public String getField() {
        return m_field;
    }

    public String getDType() {
        return m_dtype;
    }

    public UID getDefault() {
        return m_default;
    }

    public UID getPrevLevel() {
        return m_prevlevel;
    }

    public UID getNextLevel() {
        return m_nextlevel;
    }

    public UID getDimension() {
        return m_dimension;
    }

    public UID getHierarchy() {
        return m_hierarchy;
    }

    public ArrayList<UID> getMembers() {
        return m_members;
    }

    public String getUniqueName(Metadata meta) {
        Hierarchy hie = (Hierarchy)meta.getElement(getHierarchy());
        return hie.getUniqueName(meta) + "." + super.getUniqueName(meta);
    }
}

