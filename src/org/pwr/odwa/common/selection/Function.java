package org.pwr.odwa.common.selection;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Klasa reprezentująca funkcję nałożoną na zbiór elementów wymiaru (np. headX,
 * toppercen, notempty)
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class Function implements IsSerializable {
	private static final long serialVersionUID = 8987240111613491526L;
	private int functionId;

	private ArrayList<Object> parameter;

	/**
	 * Konstruktor domyślny
	 */
	public Function() {
	}

	/**
	 * Konstruktor na bazie identyfikatora funkcji, bez parametrów
	 *
	 * @param id
	 */
	public Function(int id) {
		functionId = id;
		parameter = null;
	}

	/**
	 * konstruktor na bazie identyfikatora funkcji oraz listy parametrów
	 *
	 * @param id
	 * @param o
	 */
	public Function(int id, ArrayList<Object> o) {
		functionId = id;
		parameter = o;
	}

	/**
	 * Zwraca identyfikator funkcji
	 *
	 * @return
	 */
	public int getFunctionId() {
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
	public void setFunctionId(int functionId) {
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