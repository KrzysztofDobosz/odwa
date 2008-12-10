package selection;

import java.io.Serializable;

/**
 * Klasa reprezentuj�ca metod� opisu cz�onk�w wymiaru na podstawie innych
 * cz�onk�w b�d� element�w hierarchii poprzez identyfikator metody (poprzez
 * dziedziczenie umo�liwione jest wprowadzenie bardziej zaawansowanych metod jak
 * r�wnie� ich z�o�e�).
 * 
 * @author Micha� Brzezi�ski-Spiczak
 * 
 */
public class Method implements Serializable {

	private static final long serialVersionUID = 5987452597403813853L;
	private int methodId;
	/**
	 * Konstruktor domy�lny
	 */
	public Method(){}
	/**
	 * Konstruktor na bazie identyfikatora metody	
	 * @param methodId
	 */
	public Method(int methodId){
		this.methodId = methodId;
	}
	/**
	 * Ustawia identyfikator metody na warto�� podan� jako parametr
	 * @param methodId - identyfikator metody do ustawienia
	 */
	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}
	/**
	 * Zwraca identyfikator metody
	 * @return methodId - identyfikator metody
	 */
	public int getMethodId() {
		return methodId;
	}
}
