package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

/**
 * Stores internal information of a hierarchy.
 *
 * This class is an image of 'hierarchy' tag from metadata XML,
 * giving a complete information set about a single hierarchy
 * in a data warehouse project for the given dimension.
 * <p>
 * The information includes:
 * <ul>
 * <li>default - UID of the default level in this hierarchy</li>
 * <li>dimension - UID of the dimension this hierarchy belongs to</li>
 * <li>levels - an array of levels which belong this hierarchy</li>
 * </ul>
 * <p>
 * This component extends {@link Meta} class inheriting basic meta
 * element properties from it (unique ID, name and description).
 */
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

