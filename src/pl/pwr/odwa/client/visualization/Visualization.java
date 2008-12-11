package pl.pwr.odwa.client.visualization;

import pl.pwr.odwa.result.DBResult;



/**
 * Klasa odpowiadajaca za wyswietlanie danych uzytkownikowi 
 * 
 * @author Wojciech Skórski
 * @author Paweł Szołtysek
 * 
 */
public class Visualization {
	
	/**
	 * Metoda wyswietla raport z zadanymi stylami.
	 * 
	 * @param result dane, które mają zostać wyświetlone,
	 * @param style style, zgodnie z którymi mają zostać wyświetlone dane.
	 * 
	 */
	void show(DBResult result, ReportStyle style)
	{
		System.out.println("Wyświetlono raport rozszerzony");
	}
	
	/**
	 * Metoda zwracająca obecnie aktywny styl.
	 * 
	 * @return obecnie aktywny styl raportu 
	 * 
	 */
	ReportStyle getReportStyle() 
	{
		ReportStyle reportStyle = new ReportStyle();
		return reportStyle;
	}
	

}
