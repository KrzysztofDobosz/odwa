package org.pwr.odwa.common.selection;

import java.io.Serializable;

/**
 * Klasa reprezentująca metodę opisu członków wymiaru na podstawie innych
 * członków bądź elementów hierarchii poprzez identyfikator metody (poprzez
 * dziedziczenie umożliwione jest wprowadzenie bardziej zaawansowanych metod jak
 * również ich złożeń).
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 * 
 */
public class Method implements Serializable {

	private static final long serialVersionUID = 5987452597403813853L;
	private int methodId;
	/**
	 * Konstruktor domyślny
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
	 * Ustawia identyfikator metody na wartość podaną jako parametr
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
