package org.pwr.odwa.common.selection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.pwr.odwa.server.metadata.Metadata;

/**
 * Class representing nested-axis of {@link UserSelection}
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class Axis implements Serializable {
	private static final long serialVersionUID = 1490457996717239587L;
	private ArrayList<AxisElement> axisElements;

	public Axis(){
		axisElements = new ArrayList<AxisElement>();
	}
	/**
	 * Returns the amount of AxisElements {@link AxisElement} in nested Axis(0 for not-nested axis)
	 * @return the amount of AxisElements {@link AxisElement}
	 */
	public int getAxisElementAmount() {
		return axisElements.size();
	}
	/**
	 *
	 * @param i - axis number in container
	 * @return axisElement i AxisElement of nested Axis
	 */
	public AxisElement getAxisElement(int i) {
		return axisElements.get(i);
	}
	/**
	 * Add {@link AxisElement} to nested Axis
	 * @param axisElement - single {@link AxisElement} to be added
	 */

	public void addAxisElement(AxisElement axisElement) {
		axisElements.add(axisElement);
	}
	/**
	 *
	 * @return axisElements - {@link ArrayList} of AxisElements (container of Axiselements)
	 */
	public ArrayList<AxisElement> getAxisElements() {
		return axisElements;
	}
	/**
	 * Set container {@link ArrayList} of AxisElements as current Axis
	 * @param axisElements {@link ArrayList} of {@link AxisElement}s
	 */
	public void setAxisElements(ArrayList<AxisElement> axisElements) {
		this.axisElements = axisElements;
	}

    public String toMDX(Metadata meta, boolean keys) {
        switch (axisElements.size()) {
        case 0:
            return "{}";
        case 1:
            return axisElements.get(0).toMDX(meta, keys);
        default:
            StringBuilder builder = new StringBuilder();

            builder.append("Crossjoin(");

            Iterator iter = axisElements.iterator();

            while (iter.hasNext()) {
                builder.append(((DimensionEl)iter.next()).toMDX(meta, keys));

                if (iter.hasNext()) {
                    builder.append(", ");
                }
            }

            builder.append(")");

            return builder.toString();
        }
    }
}

