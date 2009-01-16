package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

class Hierarchy extends Meta {
    protected UID m_default;
    protected UID m_dimension;
    protected ArrayList<UID> m_levels;

    public void setDefault(UID def) {
        m_default = def;
    }

    public void setDimension(UID dimension) {
        m_dimension = dimension;
    }

    public void setLevels(ArrayList<UID> levels) {
        m_levels = levels;
    }

    public UID getDefault() {
        return m_default;
    }

    public UID getDimension() {
        return m_dimension;
    }

    public ArrayList<UID> getLevels() {
        return m_levels;
    }
}

