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
	 * Metoda wyswietla dane w trybie ReportMode ze standardowymi stylami.
	 * 
	 * @param reportMode tryb wy�wietlania raportu,
	 * @param result dane, kt�re maj� zosta� wy�wietlone.
	 * 
	 */
	void show(int reportMode, DBResult result)  
	{
		System.out.println("Wy�wietlono raport");
	}
	
	/**
	 * Metoda wyswietla dane w trybie ReportMode z zadanymi stylami.
	 * 
	 * @param reportMode tryb wy�wietlania raportu,
	 * @param result dane, kt�re maj� zosta� wy�wietlone,
	 * @param style style, zgodnie z kt�rymi maj� zosta� wy�wietlone dane.
	 * 
	 */
	void show(int reportMode, DBResult result, ReportStyle style)
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
