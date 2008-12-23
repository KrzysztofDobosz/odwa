package org.pwr.odwa.common;

import java.util.ArrayList;

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
 *  @author Mateusz Paprocki
 *  @author Maria Łabaziewicz
 */
public class MetaHierarchy extends MetaElement {
    protected ArrayList<MetaDim> m_members;

    protected void setMembers(ArrayList<MetaDim> memb) {
        m_members = memb;
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

