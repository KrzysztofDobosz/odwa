package org.pwr.odwa.common.selection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.pwr.odwa.server.metadata.Metadata;

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

    public String toMDX(Metadata meta, boolean keys) {
        return toMDX(meta, keys, null);
    }

    public String toMDX(Metadata meta, boolean keys, String arg) {
        if (functionId == null) {
            return arg;
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append(functionId + "(");

            if (arg != null) {
                builder.append(arg);
            }

            if (parameter != null) {
                Iterator iter = parameter.iterator();

                if (arg != null && iter.hasNext()) {
                    builder.append(", ");
                }

                while (iter.hasNext()) {
                    builder.append(iter.next().toString());

                    if (iter.hasNext()) {
                        builder.append(", ");
                    }
                }
            }

            builder.append(")");

            return builder.toString();
        }
    }
}

