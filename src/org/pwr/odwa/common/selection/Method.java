package org.pwr.odwa.common.selection;

import java.io.Serializable;

/**
 * 
 * Class representing description method of dimension members by other members
 * or hierarchy levels (inheritance makes possible to entrance more complex
 * methods and also its combinations
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 * 
 */
public class Method implements Serializable {

	private static final long serialVersionUID = 5987452597403813853L;
	private String methodId;

	public Method() {
		methodId = null;
	}

	/**
	 * Constructor based on methodId
	 * 
	 * @param methodId
	 */
	public Method(String methodId) {
		this.methodId = methodId;
	}

	/**
	 * Get methodId
	 */
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	/**
	* Set methodId
	 */
	public String getMethodId() {
		return methodId;
	}
}
