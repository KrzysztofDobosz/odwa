/**
 * 
 */
package pl.pwr.odwa.selection;
/**
 * Klasa reprezentuj�ca zagnie�d�on� o� zapytania {@link UserSelection}
 * @author Katarzyna Rzerzicha
 * @author Micha� Brzezi�ski-Spiczak
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Axis implements Serializable {
	private static final long serialVersionUID = 1490457996717239587L;
	private ArrayList<AxisElement> axisElements;
	/**
	 * Konstruktor domy�lny
	 */
	public Axis(){}
	/**
	 * Zwraca ilo�� zagnie�d�onych osi {@link AxisElement} (0 dla pojedynczej osi)
	 * @return ilo�� zagnie�d�onych osi {@link AxisElement}
	 */
	public int getAxisElementAmount() {
		return axisElements.size();
	}
	/**
	 * Zwraca i-ty element zagnie�d�enia {@link AxisElement}
	 * @param i - numer osi w kontenerze
	 * @return axisElement i-ty element osi zagnie�d�onej
	 */
	public AxisElement getAxisElement(int i) {
		return axisElements.get(i);
	}
	/**
	 * Dodaje pojedyczn� o� {@link AxisElement} do osi zagnie�d�onej
	 * @param axisElement - pojedyncza o� do dodania {@link AxisElement}
	 */

	public void addAxisElement(AxisElement axisElement) {
		axisElements.add(axisElement);
	}
	/**
	 * Zwraca kontener {@link ArrayList} zagnie�d�e� osi
	 * @return axisElements - {@link ArrayList} zagnie�d�e� osi
	 */
	public ArrayList<AxisElement> getAxisElements() {
		return axisElements;
	}
	/**
	 * Ustawia kontener {@link ArrayList} zagnie�d�e� osi na podany jako argument
	 * @param axisElements kontener {@link ArrayList} zagnie�d�e� osi do ustawienia
	 */
	public void setAxisElements(ArrayList<AxisElement> axisElements) {
		this.axisElements = axisElements;
	}
}
