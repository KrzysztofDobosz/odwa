package org.pwr.odwa.common.metadata;

/** Najwyższa jednostaka allokacji w bazie metadanych.
 *
 *  Slotem bazy metadanych nazwymy najwyższą jednostkę allokacji,
 *  tzn. zbiór zawierający definicję źródła danych oraz (niepusty)
 *  zbiór widoków danych.
 *
 *  Przykład:
 *
 *    SL0    SL1    ...
 *  +-------------------+
 *  | DS0  | DS1  | ... |
 *  +-------------------+
 *  | DV00 | DV10 |     |
 *  | DV01 | DV11 |     |
 *  |  .   |  .   | ... |
 *  |  .   |  .   |     |
 *  |  .   |  .   |     |
 *  +-------------------+
 *
 *  gdzie SLi jest i-tym slotem, DSi jest i-tym źródłem danych,
 *  natomiast DVij jest jednym z elementów i-tego slotu.
 *
 *  @author Mateusz Paprocki
 *  @author Maria Łabaziewicz
 */
public class MetaSlot extends MetaElement {

}

