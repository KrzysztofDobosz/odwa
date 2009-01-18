package org.pwr.odwa.common.selection;



import java.io.Serializable;


/**
 * Klasa reprezentująca pojedynczy poziom zagnieżdżenia osi w zapytaniu {@link UserSelection}
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
  */

public class AxisElement implements Serializable {
	private static final long serialVersionUID = 5157213403910585037L;
	private DimensionElSet dimensionElSet;
	private Function function;
	/**
	 * Konstruktor domyślny
	 */
	public AxisElement(){
		
	}
	/**
	 * Konstruktor poziomu zagnieżdżenia osi na bazie składowych (złączenie metod typu set)
	 * @param dimensionElSet - kontener elementów wymiaru ({@link DimensionElSet})
	 * @param function - funkcja wyboru członków wymiaru z kontenera ({@link Function})
	 */
	public AxisElement(DimensionElSet dimensionElSet, Function function) {
		setDimensionElSet(dimensionElSet);
		setFunction(function);
	}

	/**
	 * Ustawia kontener elementów wymiaru na podany w argumencie
	 * @param dimensionElSet - zbiór elementów wymiaru do ustawienia ({@link DimensionElSet})
	 */
	public void setDimensionElSet(DimensionElSet dimensionElSet) {
		this.dimensionElSet = dimensionElSet;
	}
	/**
	 * Ustawia funkcję wyboru członków wymiaru z kontenera ({@link Function})
	 * @param function funkcja wyboru członków z kontenera ({@link Function})
	 */
	public void setFunction(Function function) {
		this.function = function;
	}
	/**
	 * Zwraca kontener elementów wymiaru ({@link DimensionElSet})
	 * @return kontener elementów wymiaru ({@link DimensionElSet})
	 */
	public DimensionElSet getDimensionElSet() {
		return dimensionElSet;
	}
	/**
	 * Zwraca funkcję wyboru czlonków z kontenera ({@link Function})
	 * @return funkcja wyboru czlonków z kontenera ({@link Function})
	 *
	 */
	public Function getFunction() {
		return function;
	}
}
