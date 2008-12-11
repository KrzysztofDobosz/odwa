package pl.pwr.odwa.selection;

import java.io.Serializable;
/**
 * Klasa reprezentuj�ca pojedynczy poziom zagnie�d�enia osi w zapytaniu {@link UserSelection}
 * 
 * @author Katarzyna Rzerzicha
 * @author Micha� Brzezi�ski-Spiczak
  */

public class AxisElement implements Serializable {
	private static final long serialVersionUID = 5157213403910585037L;
	private DimensionElSet dimensionElSet;
	private Function function;
	/**
	 * Konstruktor domy�lny
	 */
	public AxisElement(){}
	/**
	 * Konstruktor poziomu zagnie�d�enia osi na bazie sk�adowych (z��czenie metod typu set)
	 * @param dimensionElSet - kontener element�w wymiaru ({@link DimensionElSet})
	 * @param function - funkcja wyboru cz�onk�w wymiaru z kontenera ({@link Function})
	 */
	public AxisElement(DimensionElSet dimensionElSet, Function function) {
		setDimensionElSet(dimensionElSet);
		setFunction(function);
	}

	/**
	 * Ustawia kontener element�w wymiaru na podany w argumencie
	 * @param dimensionElSet - zbi�r element�w wymiaru do ustawienia ({@link DimensionElSet})
	 */
	public void setDimensionElSet(DimensionElSet dimensionElSet) {
		this.dimensionElSet = dimensionElSet;
	}
	/**
	 * Ustawia funkcj� wyboru cz�onk�w wymiaru z kontenera ({@link Function})
	 * @param function funkcja wyboru cz�onk�w z kontenera ({@link Function})
	 */
	public void setFunction(Function function) {
		this.function = function;
	}
	/**
	 * Zwraca kontener element�w wymiaru ({@link DimensionElSet})
	 * @return kontener element�w wymiaru ({@link DimensionElSet}) 
	 */
	public DimensionElSet getDimensionElSet() {
		return dimensionElSet;
	}
	/**
	 * Zwraca funkcj� wyboru czlonk�w z kontenera ({@link Function})
	 * @return funkcja wyboru czlonk�w z kontenera ({@link Function})
	 * 
	 */
	public Function getFunction() {
		return function;
	}
}
