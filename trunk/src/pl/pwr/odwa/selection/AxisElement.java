package pl.pwr.odwa.selection;

import java.io.Serializable;
/**
 * Klasa reprezentuj¹ca pojedynczy poziom zagnie¿d¿enia osi w zapytaniu {@link UserSelection}
 * 
 * @author Katarzyna Rzerzicha
 * @author Micha³ Brzeziñski-Spiczak
  */

public class AxisElement implements Serializable {
	private static final long serialVersionUID = 5157213403910585037L;
	private DimensionElSet dimensionElSet;
	private Function function;
	/**
	 * Konstruktor domyœlny
	 */
	public AxisElement(){}
	/**
	 * Konstruktor poziomu zagnie¿d¿enia osi na bazie sk³adowych (z³¹czenie metod typu set)
	 * @param dimensionElSet - kontener elementów wymiaru ({@link DimensionElSet})
	 * @param function - funkcja wyboru cz³onków wymiaru z kontenera ({@link Function})
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
	 * Ustawia funkcjê wyboru cz³onków wymiaru z kontenera ({@link Function})
	 * @param function funkcja wyboru cz³onków z kontenera ({@link Function})
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
	 * Zwraca funkcjê wyboru czlonków z kontenera ({@link Function})
	 * @return funkcja wyboru czlonków z kontenera ({@link Function})
	 * 
	 */
	public Function getFunction() {
		return function;
	}
}
