package org.pwr.odwa.server.metadata;

class Meta {
    protected UID m_uid;
    protected EltType m_type;
    protected String m_name;
    protected String m_desc;

    public void setUID(UID uid) {
        m_uid = uid;
    }

    public void setType(EltType type) {
        m_type = type;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setDesc(String desc) {
        m_desc = desc;
    }

    public UID getUID() {
        return m_uid;
    }

    public EltType getType() {
        return m_type;
    }

    public String getName() {
        return m_name;
    }

    public String getDesc() {
        return m_desc;
    }
}

