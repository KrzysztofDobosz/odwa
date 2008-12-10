package pl.pwr.odwa.selection;

import java.io.Serializable;

/**
 * Klasa reprezentuj¹ca miarê definiowan¹ poprzez identyfikator measureId
 * (dziedziczenie umo¿liwia wykorzystanie bardziej zaawansowanych, równie¿
 * zagnie¿d¿onych miar, w tym definiowanych przez u¿ytkownika na bazie
 * istniej¹cych)
 * 
 * @author Micha³ Brzeziñski-Spiczak
 * 
 */
public class Measure implements Serializable {

	private static final long serialVersionUID = 4677861308416261725L;
	private int measureId;

	/**
	 * Konstruktor domyœlny
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
