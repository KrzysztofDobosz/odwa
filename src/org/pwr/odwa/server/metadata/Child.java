package org.pwr.odwa.server.metadata;

import java.util.ArrayList;

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

