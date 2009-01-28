package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

/**
 * Stores internal information of a child.
 *
 * This class is an image of 'child' tag from metadata XML,
 * giving a complete information set about a single child
 * in a data warehouse project for a given member.
 * <p>
 * This component extends @see Meta class inheriting basic meta
 * element properties from it (unique ID, name and description).
 */
class Child extends Meta {
    protected ArrayList<UID> m_path;
    protected Member m_member;

    public void setName(String name) {
        // void
    }

    public void setDesc(String desc) {
        // void
    }

    public void setMember(Member member) {
        m_member = member;
    }

    public void setPath(ArrayList<UID> path) {
        m_path = path;
    }

    public String getName() {
        return m_member.getName();
    }

    public String getDesc() {
        return m_member.getDesc();
    }

    public Member getMember() {
        return m_member;
    }

    public ArrayList<UID> getPath() {
        return m_path;
    }
}

