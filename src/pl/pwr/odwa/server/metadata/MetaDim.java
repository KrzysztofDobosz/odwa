package pl.pwr.odwa.server.metadata;

/** Klasa definiuje wymiar widoku danych.
 *
 * @author Mateusz Paprocki
 * @author Maria Łabaziewicz
 */
public class MetaDim extends MetaElement {
    private MetaDimTable m_table;

    protected void setTable(MetaDimTable table) {
        m_table = table;
    }

    /** Zwraca tabelę wymiarów do której wymiar należy.
     *
     *  Nazwa wymiaru nie jest unikalna w widoku, dlatego
     *  do reprezentacji wymiaru, poza nazwą, potrzebna
     *  jest tabela źródłowa, w której wymiar został
     *  zdefiniowany.
     *
     *  Oczywiści do unikalnej, ale znacznie mniej przejrzystej
     *  identyfikacji wymiaru można użyć jego identyfikator.
     */
    public MetaDimTable getTable() {
        return m_table;
    }
}

