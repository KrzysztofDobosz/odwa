package selection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa-kontener element�w wymiaru
 * 
 * @author Micha� Brzezi�ski-Spiczak
 * 
 */
public abstract class DimensionElSet implements Serializable {

	private static final long serialVersionUID = 1882221553803217784L;
	private ArrayList<DimensionEl> dimensionEls;

	
	/**
	 * Konstrktor domy�lny
	 */
	public DimensionElSet() {
	};
	/**
	 * Zwraca ilo�� element�w w kontenerze
	 * @return dimensionEl.size();
	 */
	public int getDimensionElAmount() {
		return dimensionEls.size();
	}
	/**
	 * Zwraca i-ty zbi�r element�w wymiaru {@link DimensionEl} w kontenerze
	 * @param i - nr elementu kontenera
	 * @return dimensionEl - zbi�r element�w wymiaru
	 */
	public DimensionEl getDimensionEl(int i) {
		return dimensionEls.get(i);
	}
	/**
	 * Dodaje do kontenera zbi�r element�w wymiaru
	 * @param dimensionEl - zbi�r element�w wymiaru
	 */
	public void addDimensionEl(DimensionEl dimensionEl) {
		this.dimensionEls.add(dimensionEl);
	}
	/**
	 * Zwraca kontener {@link ArrayList} zbior�w element�w wymiaru (wymiar�w)
	 * @return dimensionEls - {@link ArrayList} zbior�w element�w wymiaru (wymiar�w)
	 */
	public ArrayList<DimensionEl> getDimensionEls() {
		return dimensionEls;
	}
	/**
	 * Ustawia kontener {@link ArrayList} zbior�w element�w wymiaru (wymiar�w)
	 * @param dimensionEls kontener {@link ArrayList} zbior�w element�w wymiaru (wymiar�w)
	 */
	public void setDimensionEls(ArrayList<DimensionEl> dimensionEls) {
		this.dimensionEls = dimensionEls;
	}
}
