package org.pwr.odwa.server.metadata;

/**
 * This is a base class for all metadata elements.
 *
 * This class provides a set of basic information about
 * an metadata element, which includes the unique ID,
 * user friendly name (non-unique), even more user
 * friendly description (non-obligatory) and element
 * type (mostly for internal purpose).
 *
 * @see EltType
 */
public class Meta {
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

    public String getUniqueName(Metadata meta) {
        return "[" + getName() + "]";
    }

    public String getKeyUniqueName(Metadata meta) {
        return getUniqueName(meta);
    }

    public String toString() {
        return "(" + getUID().toString() + ", " +
            getName() + ", " + getDesc() + ")";
    }
}

