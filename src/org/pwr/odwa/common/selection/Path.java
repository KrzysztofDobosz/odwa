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
	private ArrayList<String> path;

	/**
	 * Konstruktor domyślny
	 */
	public Path() {
	}

	/**
	 * Konstruktor ścieżki poprzez parsowanie Stringa (hierarchi w zapisie MDX -
	 * NazwaKategorii.NazwaPodKategorii.(...).NazwaCzłonkaWymiaru)
	 * 
	 * @param p reprezentacja MDX ścieżki
	 */
	public Path(String p) {
		String[] temp = p.split(".");
		path = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++)
			path.add(temp[i]);

	}

	/**
	 * Dekompozycja ścieżki na kontener {@link ArrayList} Stringów
	 * @return
	 */
	public ArrayList<String> decompose() {
		return path;
	}

}
