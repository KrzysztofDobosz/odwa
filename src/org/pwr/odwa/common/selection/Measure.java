package org.pwr.odwa.common.selection;



import java.io.Serializable;


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
public class Measure implements Serializable {

	private static final long serialVersionUID = 4677861308416261725L;
	private String measureUid;

	/**
	 * Konstruktor domyślny
	 */
	public Measure() {
	}
	/**
	 * Konstruktor przyjmujacy indentyfikator miary
	 * @param measureId
	 */
	public Measure(String measureId){
		this.measureUid = measureUid;
	}
	/**
	 * Zwraca identyfikator miary
	 * @return measureId - identyfikator miary
	 */
	public String getMeasureUid() {
		return measureUid;
	}
	/**
	 * Ustawia identyfikator miary na podany w argumencie
	 * @param measureId - identyfikator miary
	 */
	public void setMeasureUid(String measureUid) {
		this.measureUid = measureUid;
	}
}
