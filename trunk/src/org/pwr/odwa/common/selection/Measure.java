package org.pwr.odwa.common.selection;

import java.io.Serializable;

/**
 * 
 * Class representing the measure defined by its ID (measureId) (Inheritance
 * makes possible to use more complex, also nested measures, including
 * user-defined based on exiting 
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 * 
 */
public class Measure implements Serializable {

	private static final long serialVersionUID = 4677861308416261725L;
	private String measureUid;

	public Measure() {
	}

	/**
	 * Constructor based on measureID
	 * 
	 * @param measureId
	 */
	public Measure(String measureId) {
		this.measureUid = measureUid;
	}

	/**
	 * Get measureId
	 * 
	 */
	public String getMeasureUid() {
		return measureUid;
	}

	/**
	 * Set measureId
	 */
	public void setMeasureUid(String measureUid) {
		this.measureUid = measureUid;
	}
}
