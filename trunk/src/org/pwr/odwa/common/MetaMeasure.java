package org.pwr.odwa.common;

/** Klasa reprezentuje miarę widoku danych.
 *
 *  Miarą definiujemy funkcję zdefiniowaną dla zbioru faktów,
 *  reprezentującą zachowanie lub wynik działalności względem
 *  wymiaru.
 *
 *  @author Mateusz Paprocki
 *  @author Maria Łabaziewicz
 */
public class MetaMeasure extends MetaElement {
    /** Klasa definiuje rodzaj aggregacji miary. */
    public enum MetaMeasureAggregation {
        MeasureSum,           /// Suma elementów zbioru.
        MeasureMin,           /// Minimalna wartość ze zbioru.
        MeasureMax,           /// Maksymalna wartość ze zbioru.
        MeasureCount,         /// Liczba elementów zbioru.
        MeasureDistincCount   /// Liczba unikalnych elementów zbioru.
    }

    private MetaMeasureAggregation m_type;

    public void setType(MetaMeasureAggregation type) {
        m_type = type;
    }

    /** Zwraca rodzaj agregacji aktulanej miary.
     *
     *  Przykładowe typy agregacji:
     *
     *   [1] sumacyjna
     *   [2] licząca
     *   [3] min/max
     *
     *  @see MetaMeasureAggregation
     */
    public MetaMeasureAggregation getType() {
        return m_type;
    }
}

