package org.pwr.odwa.common.metadata;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MetaID implements IsSerializable {
    protected long m_id;

    public MetaID(){}

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

