package selection;

import java.io.Serializable;

/**
 * Klasa reprezentuj¹ca metodê opisu cz³onków wymiaru na podstawie innych
 * cz³onków b¹dŸ elementów hierarchii poprzez identyfikator metody (poprzez
 * dziedziczenie umo¿liwione jest wprowadzenie bardziej zaawansowanych metod jak
 * równie¿ ich z³o¿eñ).
 * 
 * @author Micha³ Brzeziñski-Spiczak
 * 
 */
public class Method implements Serializable {

	private static final long serialVersionUID = 5987452597403813853L;
	private int methodId;
	/**
	 * Konstruktor domyœlny
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
	 * Ustawia identyfikator metody na wartoœæ podan¹ jako parametr
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
