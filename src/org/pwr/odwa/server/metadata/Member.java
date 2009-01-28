package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

/**
 * Stores internal information of a member.
 *
 * This class is an image of 'member' tag from metadata XML,
 * giving a complete information set about a single member
 * in a data warehouse project for a given level.
 * <p>
 * The information includes:
 * <ul>
 * <li>item - value in data base table to which this member maps</li>
 * <li>level - UID of level to which this member belongs</li>
 * <li>prevmember - UID of the previous member in member's level</li>
 * <li>nextmember - UID of the next member in member's level</li>
 * <li>dimension - UID of dimension to which this member belongs</li>
 * <li>hierarchy - UID of hierarchy to which this member belongs</li>
 * <li>children - an array of member's children UIDs</li>
 * </ul>
 * <p>
 * This component extends {@link Meta} class inheriting basic meta
 * element properties from it (unique ID, name and description).
 */
public class Member extends Meta {
    protected String m_item;
    protected UID m_level;
    protected UID m_prevmember;
    protected UID m_nextmember;
    protected UID m_dimension;
    protected UID m_hierarchy;
    protected ArrayList<UID> m_children;

    public void setItem(String item) {
        m_item = item;
    }

    public void setLevel(UID level) {
        m_level = level;
    }

    public void setPrevMember(UID prevmember) {
        m_prevmember = prevmember;
    }

    public void setNextMember(UID nextmember) {
        m_nextmember = nextmember;
    }

    public void setDimension(UID dimension) {
        m_dimension = dimension;
    }

    public void setHierarchy(UID hierarchy) {
        m_hierarchy = hierarchy;
    }

    public void setChildren(ArrayList<UID> children) {
        m_children = children;
    }

    public String getItem() {
        return m_item;
    }

    public UID getLevel() {
        return m_level;
    }

    public UID getPrevMember() {
        return m_prevmember;
    }

    public UID getNextMember() {
        return m_nextmember;
    }

    public UID getDimension() {
        return m_dimension;
    }

    public UID getHierarchy() {
        return m_hierarchy;
    }

    public ArrayList<UID> getChildren() {
        return m_children;
    }
}

