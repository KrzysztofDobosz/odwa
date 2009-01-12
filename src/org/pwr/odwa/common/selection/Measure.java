package org.pwr.odwa.common.selection;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Klasa reprezentująca miarę definiowaną poprzez identyfikator measureId
 * (dziedziczenie umożliwia wykorzystanie bardziej zaawansowanych, również
 * zagnieżdżonych miar, w tym definiowanych przez użytkownika na bazie
 * istniejących)
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class Measure implements IsSerializable {

	private static final long serialVersionUID = 4677861308416261725L;
	private int measureId;

	/**
	 * Konstruktor domyślny
	 */
	public Measure() {
	}
	/**
	 * Konstruktor przyjmujacy indentyfikator miary
	 * @param measureId
	 */
	public Measure(int measureId){
		this.measureId = measureId;
	}
	/**
	 * Zwraca identyfikator miary
	 * @return measureId - identyfikator miary
	 */
	public int getMeasureId() {
		return measureId;
	}
	/**
	 * Ustawia identyfikator miary na podany w argumencie
	 * @param measureId - identyfikator miary
	 */
	public void setMeasureId(int measureId) {
		this.measureId = measureId;
	}
}
