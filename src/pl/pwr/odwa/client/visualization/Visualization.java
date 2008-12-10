package pl.pwr.odwa.client.visualization;

import pl.pwr.odwa.server.engine.DBResult;


/**
 * Klasa odpowiadajaca za wyswietlanie danych uzytkownikowi 
 * 
 * @author Wojciech Sk�rski
 * @author Pawe� Szo�tysek
 * 
 */
public class Visualization {
	
	/**
	 * Metoda wyswietla raport z zadanymi stylami.
	 * 
	 * @param result dane, kt�re maj� zosta� wy�wietlone,
	 * @param style style, zgodnie z kt�rymi maj� zosta� wy�wietlone dane.
	 * 
	 */
	void show(DBResult result, ReportStyle style)
	{
		System.out.println("Wy�wietlono raport rozszerzony");
	}
	
	/**
	 * Metoda zwracaj�ca obecnie aktywny styl.
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
