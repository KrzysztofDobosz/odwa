package org.pwr.odwa.common.selection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing function defined on dimension member elements set (ie
 * headX, TopPercent, NotEmpty)
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 * 
 */
public class Function implements Serializable {
	private static final long serialVersionUID = 8987240111613491526L;
	private String functionId;

	private ArrayList<Object> parameter;

	public Function() {
		functionId = null;

	}

	/**
	 * Constructor on the base of function id
	 * 
	 */
	public Function(String id) {
		functionId = id;
		parameter = null;
	}

	/**
	 * Constructor on the base of function id and {@link ArrayList} of
	 * parameters
	 */
	public Function(String id, ArrayList<Object> o) {
		functionId = id;
		parameter = o;
	}

	/**
	 * Get functionId
	 * 
	 * @return
	 */
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * Get parameter container ({@link ArrayList})
	 * 
	 */
	public ArrayList<Object> getParameter() {
		return parameter;
	}

	/**
	 * Set functionId
	 * 
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/**
	 * Set parameter container ({@link ArrayList})
	 */
	public void setParameter(ArrayList<Object> parameter) {
		this.parameter = parameter;
	}

}