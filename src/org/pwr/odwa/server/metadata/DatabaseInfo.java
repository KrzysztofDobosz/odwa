package org.pwr.odwa.server.metadata;

/**
 * Stores information about database in use.
 *
 * This class is an image of 'database' tag from metadata
 * XML, providing sufficient information for the engine
 * to connect to a database containing warehouse storage.
 * <p>
 * The information includes:
 * <ul>
 * <li>provider - database provider (currently only mysql is supported)</li>
 * <li>host - full name of warehouse server machine</li>
 * <li>port - provider should listen on this, e.g. 3390</li>
 * <li>name - database name, e.g. odwa</li>
 * </ul>
 */
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

