package pl.pwr.odwa.client.reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

import pl.pwr.odwa.client.visualization.ReportStyle;
import pl.pwr.odwa.result.DBResult;



/**
 * Klasa raportu statycznego rozszerzaj¹ca abstrakcyjn¹ klasê {@link Report}
 * 
 * @author Micha³ Brzeziñski-Spiczak
 */
public class StaticReport extends Report {
	private static final long serialVersionUID = -4481525925179844309L;
	private DBResult result;

	/**
	 * Konstruktor domyœlny
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
		this.result = result;
		this.format = format;
		description.add("Date = " + new Date().toString());
		description.add("Author = " + "");

	}

	/**
	 * Odczyt raportu statycznego z pliku o nazwie (œcie¿ce) podanej jako
	 * argument
	 * 
	 * @param filepath
	 *            - nazwa/œcie¿ka do pliku raportu do odczytu
	 */
	public void read(String filepath) throws ClassNotFoundException,
			IOException {
		try {
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
		}
	}

	/**
	 * Zwraca wynik zapytania do bazy danych
	 */
	public DBResult getResult() {
		return result;
	}

	/**
	 * Ustawia u¿yte zapytanie do bazy danych {@link DBResult}
	 * 
	 * @param result
	 *            - zapytanie do bazy danych
	 */
	public void setResult(DBResult result) {
		this.result = result;
	}
}