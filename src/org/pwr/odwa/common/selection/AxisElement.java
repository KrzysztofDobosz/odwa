package org.pwr.odwa.common.selection;

import java.io.Serializable;

import org.pwr.odwa.server.metadata.Metadata;

/**
 *
 * Class representing single level of nested {@link Axis} in
 * {@link UserSelection}
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */

public class AxisElement implements Serializable {
	private static final long serialVersionUID = 5157213403910585037L;
	private DimensionElSet dimensionElSet;
	private Function function;

	public AxisElement() {

	}

	/**
	 * Set {@link DimensionElSet}
	 *
	 */
	public void setDimensionElSet(DimensionElSet dimensionElSet) {
		this.dimensionElSet = dimensionElSet;
	}

	/**
	 * Set {@link Function}
	 */
	public void setFunction(Function function) {
		this.function = function;
	}

	/**
	 * Get {@link DimensionElSet}
	 *
	 */
	public DimensionElSet getDimensionElSet() {
		return dimensionElSet;
	}

	/**
	 * Get {@link Function}
	 *
	 */
	public Function getFunction() {
		return function;
	}

    public String toMDX(Metadata meta, boolean keys) {
        if (function != null) {
            return function.toMDX(meta, keys, dimensionElSet.toMDX(meta, keys));
        } else {
            return dimensionElSet.toMDX(meta, keys);
        }
    }
}

