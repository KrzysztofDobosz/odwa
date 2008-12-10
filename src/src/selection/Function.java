package selection;

import java.io.Serializable;
import java.util.ArrayList;

public class Function implements Serializable {
	private static final long serialVersionUID = 8987240111613491526L;
	private int functionId;
	private ArrayList<Object> parameter;
	/**
	 * Konstruktor na bazie identyfikatora funkcji, bez parametr�w
	 * @param id
	 */
	public Function(int id) {
		functionId = id;
		parameter = null;
	}
	/**
	 * konstruktor na bazie identyfikatora funkcji oraz listy parametr�w
	 * @param id
	 * @param o
	 */
	public Function(int id, ArrayList<Object> o) {
		functionId = id;
		parameter = o;
	}
	/**
	 * Zwraca identyfikator funkcji
	 * @return
	 */
	public int getFunctionId() {
		return functionId;
	}

	/**
	 * Zwraca list� parametrow funkcji
	 * @return
	 */
	public ArrayList<Object> getParameter() {
		return parameter;
	}
	/**
	 * Ustawia identyfikator funkcji na podany jako argument
	 * @param argument do ustawienia
	 */
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	/**
	 * Ustawia list� parametrow funkcji
	 * @param lista parametr�w funkcji do ustawienia
	 */
	public void setParameter(ArrayList<Object> parameter) {
		this.parameter = parameter;
	}
	

}
