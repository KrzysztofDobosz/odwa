package org.pwr.odwa.server.metadata;

class DatabaseInfo {
    protected String m_provider;
    protected String m_host;
    protected String m_port;
    protected String m_name;

    public void setProvider(String provider) {
        m_provider = provider;
    }

    public void setHost(String host) {
        m_host = host;
    }

    public void setPort(String port) {
        m_port = port;
    }

    public void setName(String name) {
        m_name = name;
    }

    public String getProvider() {
        return m_provider;
    }

    public String getHost() {
        return m_host;
    }

    public String getPort() {
        return m_port;
    }

    public String getName() {
        return m_name;
    }
}

