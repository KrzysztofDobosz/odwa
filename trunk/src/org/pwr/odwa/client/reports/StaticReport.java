package org.pwr.odwa.client.reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.common.result.DBResult;

/**
 * Klasa raportu statycznego rozszerzająca abstrakcyjną klasę {@link Report}
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class StaticReport extends Report {
	private static final long serialVersionUID = -4481525925179844309L;
	private DBResult result;

	/**
	 * Konstruktor domyślny
	 */
	public StaticReport() {
	}

	/**
	 * Konstruktor raportu statycznego na bazie rezultatu zapytania do bazy danych
	 * {@link DBResult} oraz stylu formatowania {@link ReportStyle}.
	 *
	 * @param result
	 *            - wynik zapytania do bazy danych
	 * @param format
	 *            - styl formatowania
	 */
	public StaticReport(DBResult result, ReportStyle format) {
		System.out.println("ODWAClient: StaticReport: constructor executed");
	   this.result = result;
		this.format = format;
		//description.add("Date = " + new Date().toString());
		//description.add("Author = " + "");
	}

	/**
	 * Odczyt raportu statycznego z pliku o nazwie (ścieżce) podanej jako
	 * argument
	 *
	 * @param filepath
	 *            - nazwa/ścieżka do pliku raportu do odczytu
	 */
	public StaticReport(String filepath) throws ClassNotFoundException,
			IOException {
	   System.out.println("ODWAClient: StaticReport: filepath constructor executed");
		/*try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			ObjectInputStream objectstream = new ObjectInputStream(
					fileInputStream);
			Object readObject = objectstream.readObject();
			if (readObject instanceof StaticReport) {
				StaticReport rep = (StaticReport) readObject;
				this.description = rep.description;
				this.result = rep.result;
				this.format = rep.format;
			}

		} catch (IOException e) {
			System.out.println("Error in opening report " + e);
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}*/
	}

	/**
	 * Zwraca wynik zapytania do bazy danych
	 */
	public DBResult getResult() {
	   System.out.println("ODWAClient: StaticReport: getResult executed");
		//return result;
	   return new DBResult();
	}

	/**
	 * Ustawia użyte zapytanie do bazy danych {@link DBResult}
	 *
	 * @param result
	 *            - zapytanie do bazy danych
	 */
	public void setResult(DBResult result) {
		this.result = result;
	}
}