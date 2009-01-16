package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

class Level extends Meta {
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
}

