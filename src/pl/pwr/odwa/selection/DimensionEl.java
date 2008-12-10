package pl.pwr.odwa.selection;

import java.io.Serializable;

/**
 * Klasa reprezentuj¹ca zbiór cz³onków wymiaru poprzez œcie¿kê hierarchi (
 * {@link Path}) oraz metodê opisu {@link Method}.
 * 
 * @author Micha³ Brzeziñski-Spiczak
 * 
 */
public class DimensionEl implements Serializable {

	private static final long serialVersionUID = 819048093372776792L;
	private Path path;
	private Method method;

	/**
	 * Konstruktor domyœlny
	 */
	public DimensionEl() {
	}

	/**
	 * Konstruktor zbioru elementów wymiaru poprzez podane sk³adowych, metoda
	 * stanowi¹ca po³¹czenie metod typu set
	 * 
	 * @param path - œcie¿ka hierarchiczna ({@link Path})
	 * @param method - metoda opisu ({@link Method})
	 */
	public DimensionEl(Path path, Method method) {
		setPath(path);
		setMethod(method);
	}
	/**
	 * Zwraca œcie¿kê hierarchiczn¹ ({@link Path})
	 * @return path - œcie¿ka hierarchiczna ({@link Path})
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Ustawia œcie¿kê hierchiczn¹ na podan¹ jako argument
	 * @param path - œciezka hierarchiczna ({@link Path})
	 *           
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * Zwraca metodê opisu( {@link Method})
	 * @return - metoda opisu ({@link Method})
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Ustawia metodê opisu ({@link Method})
	 * @param method - metoda opisu ({@link Method})
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

}
