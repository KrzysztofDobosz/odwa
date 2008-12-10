package pl.pwr.odwa.selection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa reprezentuj�ca �cie�k� hierarchii wewn�trz wymiaru
 * 
 * @author Micha� Brzezi�ski-Spiczak
 * 
 */
public class Path implements Serializable {

	private static final long serialVersionUID = -2841252524985516662L;
	private ArrayList<String> path;

	/**
	 * Konstruktor domy�lny
	 */
	public Path() {
	}

	/**
	 * Konstruktor �cie�ki poprzez parsowanie Stringa (hierarchi w zapisie MDX -
	 * NazwaKategorii.NazwaPodKategorii.(...).NazwaCz�onkaWymiaru)
	 * 
	 * @param p reprezentacja MDX �cie�ki
	 */
	public Path(String p) {
		String[] temp = p.split(".");
		path = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++)
			path.add(temp[i]);

	}

	/**
	 * Dekompozycja �cie�ki na kontener {@link ArrayList} String�w
	 * @return
	 */
	public ArrayList<String> decompose() {
		return path;
	}

}
