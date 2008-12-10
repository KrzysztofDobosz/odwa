package pl.pwr.odwa.client.visualization;

import pl.pwr.odwa.server.engine.DBResult;


/**
 * Klasa odpowiadajaca za wyswietlanie danych uzytkownikowi 
 * 
 * @author Wojciech Skórski
 * @author Pawe³ Szo³tysek
 * 
 */
public class Visualization {
    
	/**
	 * Metoda wyswietla dane w trybie ReportMode ze standardowymi stylami.
	 * 
	 * @param reportMode tryb wyœwietlania raportu,
	 * @param result dane, które maj¹ zostaæ wyœwietlone.
	 * 
	 */
	void show(int reportMode, DBResult result)  
	{
		System.out.println("Wyœwietlono raport");
	}
	
	/**
	 * Metoda wyswietla dane w trybie ReportMode z zadanymi stylami.
	 * 
	 * @param reportMode tryb wyœwietlania raportu,
	 * @param result dane, które maj¹ zostaæ wyœwietlone,
	 * @param style style, zgodnie z którymi maj¹ zostaæ wyœwietlone dane.
	 * 
	 */
	void show(int reportMode, DBResult result, ReportStyle style)
	{
		System.out.println("Wyœwietlono raport rozszerzony");
	}
	
	/**
	 * Metoda zwracaj¹ca obecnie aktywny styl.
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
