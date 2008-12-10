package pl.pwr.odwa.server.metadata;

/** Klasa definiuje hierarchię wymiarów.
 *
 *  Hierarchią wymiarów nazywamy uporządkowany zbiór wymiarów
 *  reprezentujący poziomy agregacji lub metodę uporządkowania
 *  wymiarów, w celu ułatwienia tworzenia zapytań.
 *
 *  Przykład:
 *
 *      +-----------+
 *      | Kontynent |
 *      +-----------+
 *            |
 *            |
 *            V
 *      +-----------+
 *      |  Państwo  |
 *      +-----------+
 *            |
 *            |
 *            V
 *      +-----------+
 *      |  Region   |
 *      +-----------+
 *
 *  gdzie wymiar Kontynent jest najwyższym, natomias
 *  wymiar Region jest najniższym poziomem agregacji.
 *
 * @author Mateusz Paprocki
 * @author Maria Łabaziewicz
 */
public class MetaHierarchy extends MetaElement {
    protected ArrayList<MetaDim> m_members;

    protected ArrayList<MetaDim> getMembers() {
        return m_members;
    }

    /** Zwraca listę wymiarów hierarchii.
     *
     *  Porządek argumentów jest od najwyższego
     *  do najniższego w hierarchii.
     */
    public ArrayList<MetaDim> getMembers() {
        return m_members;
    }
}

