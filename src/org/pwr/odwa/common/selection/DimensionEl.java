package org.pwr.odwa.common.selection;

import java.io.Serializable;

/**
 * Class representing set of dimension memebers by hierarchical {@link Path} and
 * {@link Method}
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 * 
 */
public class DimensionEl implements Serializable {

	private static final long serialVersionUID = 819048093372776792L;
	private Path path;
	private Method method;

	public DimensionEl() {
	}

	/**
	 * Get {@link Path}
	 * 
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Set {@link Path}
	 * 
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * Get {@link Method}
	 *
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Set {@link Method}
	 * 
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
}
