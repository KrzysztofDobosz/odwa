package org.pwr.odwa.client.reports;

import java.util.ArrayList;

import org.pwr.odwa.client.visualization.ReportStyle;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Abstrakcyjna klasa raportu (dziedziczone po niej Static- i DynamicReport),
 * implementuje interfejs {@link Serializable} w celu umożliwienia zapisu i
 * odczytu obiektu do/z pliku
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class Report implements IsSerializable {
	private static final long serialVersionUID = 1726723046281459668L;
	protected ArrayList<String> description;
	ReportStyle format;

	/**
	 * Metoda zapisująca raport do pliku o nazwie podanej jako argument
	 *
	 * @param filepath
	 *            nazwa pliku do zapisu raportu
	 */
	public void save(String filepath) {
	   System.out.println("ODWAClient: Report: save executed");
	   /*try {
		   FileOutputStream fileOutputStream = new FileOutputStream(filepath);
			ObjectOutputStream objectStream = new ObjectOutputStream(
					fileOutputStream);
			objectStream.writeObject(this);
			objectStream.close();
			System.out.println("Report saved to file " + filepath);
		} catch (IOException e) {
			System.out.println("Error in saving " + e);
			throw e;
		}*/
	}

	/**
	 * Zwraca użyty przez wizualizację styl raportu (opcje wyświetlania -
	 * {@link ReportStyle})
	 */
	public ReportStyle getReportStyle() {
	   System.out.println("ODWAClient: Report: getReportStyle executed");
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
	 * Zwraca kontener ({@link ArrayList}) opisu raportu zawierący: datę
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
