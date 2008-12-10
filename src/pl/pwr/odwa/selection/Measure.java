package pl.pwr.odwa.selection;

import java.io.Serializable;

/**
 * Klasa reprezentuj�ca miar� definiowan� poprzez identyfikator measureId
 * (dziedziczenie umo�liwia wykorzystanie bardziej zaawansowanych, r�wnie�
 * zagnie�d�onych miar, w tym definiowanych przez u�ytkownika na bazie
 * istniej�cych)
 * 
 * @author Micha� Brzezi�ski-Spiczak
 * 
 */
public class Measure implements Serializable {

	private static final long serialVersionUID = 4677861308416261725L;
	private int measureId;

	/**
	 * Konstruktor domy�lny
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
