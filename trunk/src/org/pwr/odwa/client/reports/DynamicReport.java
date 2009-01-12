package org.pwr.odwa.client.reports;

import java.util.Date;

import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.common.selection.UserSelection;

/**
 * Klasa raportu dynamicnzego rozszerzająca abstrakcyjną klasę {@link Report}
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class DynamicReport extends Report {
	private static final long serialVersionUID = -5321517277324547126L;
	UserSelection selection;
	ReportStyle format;

	/**
	 * Konstruktor domyślny
	 */
	public DynamicReport() {
	};
	/**
	 * Konstruktor raportu dynamicznego na bazie zapytania do bazy danych
	 * {@link UserSelection} oraz stylu formatowania {@link ReportStyle}.
	 *
	 * @param selection
	 *            - wynik zapytania do bazy danych
	 * @param format
	 *            - styl formatowania
	 */
	public DynamicReport(UserSelection selection, ReportStyle format) {
		System.out.println("ODWAClient: DynamicReport: constructor executed");
	   this.selection = selection;
		this.format = format;
		//description.add("Date = " + new Date().toString());
		//description.add("Author = " + "");
	}
	/**
	 * Odczyt raportu dynamicznego z pliku o nazwie (ścieżce) podanej jako
	 * argument
	 *
	 * @param filepath
	 *            - nazwa/ścieżka do pliku raportu do odczytu
	 */
	public DynamicReport(String filepath) {
		System.out.println("ODWAClient: DynamicReport: filepath constructor executed");
	   /*try {
			FileInputStream file = new FileInputStream(filepath);
			ObjectInputStream objectstream = new ObjectInputStream(file);
			Object report = objectstream.readObject();
			if (report instanceof DynamicReport) {
				DynamicReport rep = (DynamicReport) report;
				this.description = rep.description;
				this.selection = rep.selection;
				this.format = rep.format;
			}

		} catch (IOException e) {
			System.out.println("Error in opening report " + e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * Zwraca zapisane w raporcie zapytanie użytkownika {@link UserSelection}
	 */

	public UserSelection getUserSelection() {
	   System.out.println("ODWAClient: DynamicReport: getUserSelection executed");
		return selection;
	}
	/**
	 * Ustawia zapytanie użytkownika {@link UserSelection}
	 * @param selection - zapytanie użytkowinka
	 */
	public void setUserSelection(UserSelection selection){
		this.selection = selection;
	}

}
