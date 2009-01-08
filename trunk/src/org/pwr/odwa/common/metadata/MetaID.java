package org.pwr.odwa.common.metadata;

import java.io.Serializable;

public class MetaID implements Serializable {
    protected long m_id;

    public MetaID(long id) {
        m_id = id;
    }

    public void setID(long id) {
        m_id = id;
    }

    public long getID() {
        return m_id;
    }

    public String toString() {
        return "ID(" + m_id + ")";
    }
}

