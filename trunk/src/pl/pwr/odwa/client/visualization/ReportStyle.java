package pl.pwr.odwa.client.visualization;

import java.util.ArrayList;

/**
 * Klasa zawierajaca selekcje styli uzytkownika do kazdej komórki tabeli.
 * 
 * @author Wojciech Skórski
 * @author Pawe³ Szo³tysek
 * 
 */
public class ReportStyle {

	int reportMode;
	
	/**
	 * Metoda zwraca tryb raportu.
	 * 
	 * @return tryb raportu.
	 * 
	 */
	int getReportMode()
	{
		return reportMode;
	}
	
	/**
	 * Metoda ustala tryb raportu.
	 * 
	 * @param mode tryb raportu.
	 * 
	 */
	void setReportMode(int mode)
	{
		reportMode = mode;
		System.out.println("Ustawiono nowy tryb raportu");
	}
	
	/**
	 * Metoda zwraca kolejny wiersz styli.
	 * 
	 * @return wiersz styli.
	 * 
	 */
	ReportStyleRow fetchStyleForRow() 
	{
		ReportStyleRow styleRow = new ReportStyleRow();
		return styleRow;
	} 
	
	/**
	 * Metoda zwraca tablicê styli.
	 * 
	 * @return tablica styli.
	 * 
	 */
	ArrayList<ReportStyleRow> fetchStyleArray() 
	{
		ArrayList<ReportStyleRow> styleArray = new ArrayList<ReportStyleRow>();
		return styleArray;
	}

}
