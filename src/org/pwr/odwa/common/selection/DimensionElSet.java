package org.pwr.odwa.common.selection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa-kontener elementów wymiaru
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 * 
 */
public abstract class DimensionElSet implements Serializable {

	private static final long serialVersionUID = 1882221553803217784L;
	private ArrayList<DimensionEl> dimensionEls;

	
	/**
	 * Konstrktor domyślny
	 */
	public DimensionElSet() {
	};
	/**
	 * Zwraca ilość elementów w kontenerze
	 * @return dimensionEl.size();
	 */
	public int getDimensionElAmount() {
		return dimensionEls.size();
	}
	/**
	 * Zwraca i-ty zbiór elementów wymiaru {@link DimensionEl} w kontenerze
	 * @param i - nr elementu kontenera
	 * @return dimensionEl - zbiór elementów wymiaru
	 */
	public DimensionEl getDimensionEl(int i) {
		return dimensionEls.get(i);
	}
	/**
	 * Dodaje do kontenera zbiór elementów wymiaru
	 * @param dimensionEl - zbiór elementów wymiaru
	 */
	public void addDimensionEl(DimensionEl dimensionEl) {
		this.dimensionEls.add(dimensionEl);
	}
	/**
	 * Zwraca kontener {@link ArrayList} zbiorów elementów wymiaru (wymiarów)
	 * @return dimensionEls - {@link ArrayList} zbiorów elementów wymiaru (wymiarów)
	 */
	public ArrayList<DimensionEl> getDimensionEls() {
		return dimensionEls;
	}
	/**
	 * Ustawia kontener {@link ArrayList} zbiorów elementów wymiaru (wymiarów)
	 * @param dimensionEls kontener {@link ArrayList} zbiorów elementów wymiaru (wymiarów)
	 */
	public void setDimensionEls(ArrayList<DimensionEl> dimensionEls) {
		this.dimensionEls = dimensionEls;
	}
}
