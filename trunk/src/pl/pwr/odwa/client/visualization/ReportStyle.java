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
