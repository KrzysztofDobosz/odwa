package pl.pwr.odwa.selection;

import java.io.Serializable;

/**
 * Klasa reprezentująca zbiór członków wymiaru poprzez ścieżkę hierarchi (
 * {@link Path}) oraz metodę opisu {@link Method}.
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class DimensionEl implements Serializable {

	private static final long serialVersionUID = 819048093372776792L;
	private Path path;
	private Method method;

	/**
	 * Konstruktor domyślny
	 */
	public DimensionEl() {
	}

	/**
	 * Konstruktor zbioru elementów wymiaru poprzez podane składowych, metoda
	 * stanowiąca połączenie metod typu set
	 *
	 * @param path - ścieżka hierarchiczna ({@link Path})
	 * @param method - metoda opisu ({@link Method})
	 */
	public DimensionEl(Path path, Method method) {
		setPath(path);
		setMethod(method);
	}

	/**
	 * Zwraca ścieżkę hierarchiczną ({@link Path})
	 * @return path - ścieżka hierarchiczna ({@link Path})
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Ustawia ścieżkę hierchiczną na podaną jako argument
	 * @param path - ściezka hierarchiczna ({@link Path})
	 *
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * Zwraca metodę opisu( {@link Method})
	 * @return - metoda opisu ({@link Method})
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Ustawia metodę opisu ({@link Method})
	 * @param method - metoda opisu ({@link Method})
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
}

