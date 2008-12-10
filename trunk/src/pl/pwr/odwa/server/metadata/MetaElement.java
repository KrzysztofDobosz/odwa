package pl.pwr.odwa.server.metadata;

/** Klasa bazowa dla klas będących elementami bazy metadanych.
 *
 *  Każdy element bazy metadanych cechuje się trzema podstawowymi
 *  atrybutami:
 *
 *   [1] Unikalne ID.
 *   [2] Przyjazna dla urzytkownika nazwa.
 *   [3] Opis przeznaczenia danego elementu bazy metadanych.
 *
 *  Zawartość poszczególnych atrybutów może być ustalana jedynie w
 *  module metadaych, natomias moduły korzystające z elementów bazy
 *  metadanych (głównie moduł GUI) może jedynie czytać zawartość
 *  atrybutów.
 *
 * @author Mateusz Paprocki
 * @author Maria Łabaziewicz
 */
public class MetaElement {
    protected int m_id;        // XXX: int -> MetaID
    protected String m_name;
    protected String m_notes;

    protected void setID(int id) {
        m_id = id;
    }

    protected void setName(String name) {
        m_name = name;
    }

    protected void setNotes(String notes) {
        m_notes = notes;
    }

    public int getID() {
        return m_id;
    }

    public String getName() {
        return m_name;
    }

    public String getNotes() {
        return m_notes;
    }
}

