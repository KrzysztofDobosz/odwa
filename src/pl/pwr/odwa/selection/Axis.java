/**
 * 
 */
package pl.pwr.odwa.selection;
/**
 * Klasa reprezentująca zagnieżdżoną oś zapytania {@link UserSelection}
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Axis implements Serializable {
	private static final long serialVersionUID = 1490457996717239587L;
	private ArrayList<AxisElement> axisElements;
	/**
	 * Konstruktor domyślny
	 */
	public Axis(){}
	/**
	 * Zwraca ilość zagnieżdżonych osi {@link AxisElement} (0 dla pojedynczej osi)
	 * @return ilość zagnieżdżonych osi {@link AxisElement}
	 */
	public int getAxisElementAmount() {
		return axisElements.size();
	}
	/**
	 * Zwraca i-ty element zagnieżdżenia {@link AxisElement}
	 * @param i - numer osi w kontenerze
	 * @return axisElement i-ty element osi zagnieżdżonej
	 */
	public AxisElement getAxisElement(int i) {
		return axisElements.get(i);
	}
	/**
	 * Dodaje pojedyczną oś {@link AxisElement} do osi zagnieżdżonej
	 * @param axisElement - pojedyncza oś do dodania {@link AxisElement}
	 */

	public void addAxisElement(AxisElement axisElement) {
		axisElements.add(axisElement);
	}
	/**
	 * Zwraca kontener {@link ArrayList} zagnieżdżeń osi
	 * @return axisElements - {@link ArrayList} zagnieżdżeń osi
	 */
	public ArrayList<AxisElement> getAxisElements() {
		return axisElements;
	}
	/**
	 * Ustawia kontener {@link ArrayList} zagnieżdżeń osi na podany jako argument
	 * @param axisElements kontener {@link ArrayList} zagnieżdżeń osi do ustawienia
	 */
	public void setAxisElements(ArrayList<AxisElement> axisElements) {
		this.axisElements = axisElements;
	}
}
