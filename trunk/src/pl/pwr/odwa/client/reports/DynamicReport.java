package pl.pwr.odwa.client.reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

import pl.pwr.odwa.client.visualization.ReportStyle;
import pl.pwr.odwa.selection.UserSelection;

/**
 * Klasa raportu dynamicnzego rozszerzaj¹ca abstrakcyjn¹ klasê {@link Report}
 * 
 * @author Katarzyna Rzerzicha
 * @author Micha³ Brzeziñski-Spiczak
 */
public class DynamicReport extends Report {
	private static final long serialVersionUID = -5321517277324547126L;
	UserSelection selection;

	/**
	 * Konstruktor domyœlny
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
		this.selection = selection;
		this.format = format;
		description.add("Date = " + new Date().toString());
		description.add("Author = " + "");
	}
	/**
	 * Odczyt raportu dynamicznego z pliku o nazwie (œcie¿ce) podanej jako
	 * argument
	 * 
	 * @param filepath
	 *            - nazwa/œcie¿ka do pliku raportu do odczytu
	 */
	public DynamicReport(String filepath) {
		try {
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
		}
	}
	/**
	 * Zwraca zapisane w raporcie zapytanie u¿ytkownika {@link UserSelection}
	 */
	
	public UserSelection getUserSelection() {
		return selection;
	}
	/**
	 * Ustawia zapytanie u¿ytkownika {@link UserSelection}
	 * @param selection - zapytanie u¿ytkowinka
	 */
	public void setUserSelection(UserSelection selection){
		this.selection = selection;
	}

}
