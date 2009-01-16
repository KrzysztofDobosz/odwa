package org.pwr.odwa.server.metadata;

import java.io.Serializable;

public final class UID implements Serializable {
    private String m_uid;

    public UID(String uid) {
        m_uid = uid;
    }

    public String get_uid() {
        return m_uid;
    }

    public int hashCode() {
        return m_uid.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        UID uid = (UID)obj;

        return (get_uid().compareTo(uid.get_uid()) == 0);
    }
}

