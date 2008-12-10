package pl.pwr.odwa.selection;

import java.io.Serializable;

/**
 * Klasa reprezentuj�ca zbi�r cz�onk�w wymiaru poprzez �cie�k� hierarchi (
 * {@link Path}) oraz metod� opisu {@link Method}.
 * 
 * @author Micha� Brzezi�ski-Spiczak
 * 
 */
public class DimensionEl implements Serializable {

	private static final long serialVersionUID = 819048093372776792L;
	private Path path;
	private Method method;

	/**
	 * Konstruktor domy�lny
	 */
	public DimensionEl() {
	}

	/**
	 * Konstruktor zbioru element�w wymiaru poprzez podane sk�adowych, metoda
	 * stanowi�ca po��czenie metod typu set
	 * 
	 * @param path - �cie�ka hierarchiczna ({@link Path})
	 * @param method - metoda opisu ({@link Method})
	 */
	public DimensionEl(Path path, Method method) {
		setPath(path);
		setMethod(method);
	}
	/**
	 * Zwraca �cie�k� hierarchiczn� ({@link Path})
	 * @return path - �cie�ka hierarchiczna ({@link Path})
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Ustawia �cie�k� hierchiczn� na podan� jako argument
	 * @param path - �ciezka hierarchiczna ({@link Path})
	 *           
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * Zwraca metod� opisu( {@link Method})
	 * @return - metoda opisu ({@link Method})
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Ustawia metod� opisu ({@link Method})
	 * @param method - metoda opisu ({@link Method})
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

}
