package org.pwr.odwa.common.selection;



import java.io.Serializable;
import java.util.ArrayList;


/**
 * Klasa reprezentująca ścieżkę hierarchii wewnątrz wymiaru
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class Path implements Serializable {

	private static final long serialVersionUID = -2841252524985516662L;

	private String path;

	/**
	 * Konstruktor domyślny
	 */
	public Path() {
		path = null;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	


}
