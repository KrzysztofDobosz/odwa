package pl.pwr.odwa.client.reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import pl.pwr.odwa.client.visualization.ReportStyle;



/**
 * Abstrakcyjna klasa raportu (dziedziczone po niej Static- i DynamicReport),
 * implementuje interfejs {@link Serializable} w celu umo¿liwienia zapisu i
 * odczytu obiektu do/z pliku
 * 
 * @author Micha³ Brzeziñski-Spiczak
 */
public class Report implements Serializable {
	private static final long serialVersionUID = 1726723046281459668L;
	protected ArrayList<String> description;
	ReportStyle format;

	/**
	 * Metoda zapisuj¹ca raport do pliku o nazwie podanej jako argument
	 * 
	 * @param filepath
	 *            nazwa pliku do zapisu raportu
	 */
	public void save(String filepath) throws IOException {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filepath);
			ObjectOutputStream objectStream = new ObjectOutputStream(
					fileOutputStream);
			objectStream.writeObject(this);
			objectStream.close();
			System.out.println("Report saved to file " + filepath);
		} catch (IOException e) {
			System.out.println("Error in saving " + e);
			throw e;
		}
	}

	/**
	 * Zwraca u¿yty przez wizualizacjê styl raportu (opcje wyœwietlania -
	 * {@link ReportStyle})
	 */
	public ReportStyle getReportStyle() {
		return format;
	}

	/**
	 * Ustawia styl raportu na format {@link ReportStyle} podany jako argument
	 * 
	 * @param format
	 *            styl raportu do ustawienia
	 */
	public void setReportStyle(ReportStyle format) {
		this.format = format;
	}

	/**
	 * Zwraca kontener ({@link ArrayList}) opisu raportu zawier¹cy: datê
	 * utworzenia, autora (...)
	 * 
	 */
	public ArrayList<String> getDescription() {
		return description;
	}

	/**
	 * Ustawia kontener ({@link ArrayList}) opisu raportu
	 */
	public void setDescription(ArrayList<String> description) {
		this.description = description;
	}

	/**
	 * Dodaje do opisu raportu element({@link String}) podany jako parametr
	 */
	public void addToDescription(String s) {
		description.add(s);
	}

}
