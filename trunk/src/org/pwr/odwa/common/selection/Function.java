package org.pwr.odwa.common.selection;



import java.io.Serializable;
import java.util.ArrayList;


/**
 * Klasa reprezentująca funkcję nałożoną na zbiór elementów wymiaru (np. headX,
 * toppercent, notempty)
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class Function implements Serializable {
	private static final long serialVersionUID = 8987240111613491526L;
	private String functionId;

	private ArrayList<Object> parameter;

	/**
	 * Konstruktor domyślny
	 */
	public Function() {
		functionId = null;
		
	}

	/**
	 * Konstruktor na bazie identyfikatora funkcji, bez parametrów
	 *
	 * @param id
	 */
	public Function(String id) {
		functionId = id;
		parameter = null;
	}

	/**
	 * konstruktor na bazie identyfikatora funkcji oraz listy parametrów
	 *
	 * @param id
	 * @param o
	 */
	public Function(String id, ArrayList<Object> o) {
		functionId = id;
		parameter = o;
	}

	/**
	 * Zwraca identyfikator funkcji
	 *
	 * @return
	 */
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * Zwraca listę parametrow funkcji
	 *
	 * @return
	 */
	public ArrayList<Object> getParameter() {
		return parameter;
	}

	/**
	 * Ustawia identyfikator funkcji na podany jako argument
	 *
	 * @param argument
	 *            do ustawienia
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/**
	 * Ustawia listę parametrow funkcji
	 *
	 * @param lista
	 *            parametrów funkcji do ustawienia
	 */
	public void setParameter(ArrayList<Object> parameter) {
		this.parameter = parameter;
	}

}
