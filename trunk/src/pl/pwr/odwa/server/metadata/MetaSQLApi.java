package pl.pwr.odwa.server.metadata;

import pl.pwr.odwa.server.structure.DBStructure;

/** Klasa definiuje interfejs Metadane <-> SQL.
 *
 * @author Mateusz Paprocki
 * @author Maria Łabaziewicz
 */
public class MetaSQLApi {
    /** Funkcja aktualizuje logiczny obraz struktury fizycznej bazy danych.
     *
     *  Na podstawie metadanych fizycznej bazy danych, funkcja buduje
     *  logiczny obraz tej bazy tj. metadane hurtowni danych. Funkcja
     *  jest wykorzystywana zarówno przy budowie pierwotnego obrazu
     *  bazy jak i jego aktualizacji, w przypadku zaistniałych zmian.
     */
    void refreshMetadata(DBStructure struct) {
        System.out.println("META: Aktualizacja metadanych");
    }

    /** Funkcja tłumaczy elementy bazy metadanych na elementy fizyczne.
     *
     *  Mają dany element bazy metadanych, funkcja przekształca go
     *  w element fizycznej struktury danych, tak aby była możliwa
     *  generacja zapytania SQL względem tego meta-elementu.
     */
    Object translateMetaElement(MetaElement elem) {
        System.out.println("META: tłumaczenie meta-elementu");
        return null;
    }

    /** Funkcja zwraca wartość funkcji skrótu dla danej bazy danych.
     *
     *  Ze względu na możliwość zmiany struktury fizycznej bazy
     *  danych, przechowana jest wartość funkcji skrótu metadanych
     *  fizycznej bazy danych tak aby można było ewentualne zmiany
     *  wychwycić oraz zaktualizować metadane hurtowni (jeśli jest
     *  to możliwe).
     */
    // XXX: int -> DBHash
    int getStructureHash(String db) {
        return 0;
    }
}

