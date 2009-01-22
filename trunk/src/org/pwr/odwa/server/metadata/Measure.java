package org.pwr.odwa.server.metadata;

public class Measure extends Meta {
    protected String m_table;
    protected String m_field;
    protected String m_format;
    protected String m_function;

    public void setTable(String table) {
        m_table = table;
    }

    public void setField(String field) {
        m_field = field;
    }

    public void setFormat(String format) {
        m_format = format;
    }

    public void setFunction(String function) {
        m_function = function;
    }

    public String getTable() {
        return m_table;
    }

    public String getField() {
        return m_field;
    }

    public String getFormat() {
        return m_format;
    }

    public String getFunction() {
        return m_function;
    }
}

