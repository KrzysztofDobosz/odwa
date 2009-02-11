package org.pwr.odwa.server.metadata;

import java.util.ArrayList;
import java.util.Iterator;

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
public class Child extends Meta {
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

    public String getUniqueName(Metadata meta) {
        StringBuilder builder = new StringBuilder();

        Iterator iter = getPath().iterator();

        while (iter.hasNext()) {
            builder.append("[" + meta.getElement((UID)iter.next()).getName() + "]");

            if (iter.hasNext()) {
                builder.append(".");
            }
        }

        Hierarchy hie = (Hierarchy)meta.getElement(getMember().getHierarchy());
        return hie.getUniqueName(meta) + "." + builder.toString();
    }

    public String getKeyUniqueName(Metadata meta) {
        StringBuilder builder = new StringBuilder();

        Iterator iter = getPath().iterator();

        while (iter.hasNext()) {
            builder.append("&[" + meta.getElement((UID)iter.next()).getName() + "]");

            if (iter.hasNext()) {
                builder.append(".");
            }
        }

        Hierarchy hie = (Hierarchy)meta.getElement(getMember().getHierarchy());
        return hie.getUniqueName(meta) + "." + builder.toString();
    }
}

