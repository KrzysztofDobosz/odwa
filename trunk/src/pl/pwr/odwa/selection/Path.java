package pl.pwr.odwa.selection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa reprezentuj¹ca œcie¿kê hierarchii wewn¹trz wymiaru
 * 
 * @author Micha³ Brzeziñski-Spiczak
 * 
 */
public class Path implements Serializable {

	private static final long serialVersionUID = -2841252524985516662L;
	private ArrayList<String> path;

	/**
	 * Konstruktor domyœlny
	 */
	public Path() {
	}

	/**
	 * Konstruktor œcie¿ki poprzez parsowanie Stringa (hierarchi w zapisie MDX -
	 * NazwaKategorii.NazwaPodKategorii.(...).NazwaCz³onkaWymiaru)
	 * 
	 * @param p reprezentacja MDX œcie¿ki
	 */
	public Path(String p) {
		String[] temp = p.split(".");
		path = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++)
			path.add(temp[i]);

	}

	/**
	 * Dekompozycja œcie¿ki na kontener {@link ArrayList} Stringów
	 * @return
	 */
	public ArrayList<String> decompose() {
		return path;
	}

}
