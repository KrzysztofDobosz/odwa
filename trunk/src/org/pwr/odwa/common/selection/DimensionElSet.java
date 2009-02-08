package org.pwr.odwa.common.selection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class - container of dimension member elements
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class DimensionElSet implements Serializable {

	private static final long serialVersionUID = 1882221553803217784L;

	private ArrayList<DimensionEl> dimensionEls;

	public DimensionElSet() {
		dimensionEls = new ArrayList<DimensionEl>();
	};
	/**
	 * Get dimension member elements amount
	 *
	 */
	public int getDimensionElAmount() {
		return dimensionEls.size();
	}
	/**
	 * Get i {@link DimensionEl} in container
	 */
	public DimensionEl getDimensionEl(int i) {
		return dimensionEls.get(i);
	}
	/**
	 * Add {@link DimensionEl} to container
	 */
	public void addDimensionEl(DimensionEl dimensionEl) {
		this.dimensionEls.add(dimensionEl);
	}
	/**
	 * Get container {@link ArrayList} of {@link DimensionEl}
	 */
	public ArrayList<DimensionEl> getDimensionEls() {
		return dimensionEls;
	}
	/**
	 * Set container {@link ArrayList} of {@link DimensionEl}
	 */
	public void setDimensionEls(ArrayList<DimensionEl> dimensionEls) {
		this.dimensionEls = dimensionEls;
	}

    public String toMDX() {
        if (dimensionEls.isEmpty()) {
            return "{}";
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append("{ ");

            Iterator iter = dimensionEls.iterator();

            while (iter.hasNext()) {
                builder.append(((DimensionEl)iter.next()).toMDX());

                if (iter.hasNext()) {
                    builder.append(", ");
                }
            }

            builder.append(" }");

            return builder.toString();
        }
    }
}

