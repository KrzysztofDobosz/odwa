package pl.pwr.odwa.client.visualization;

import java.util.ArrayList;

/**
 * Klasa zawierajaca selekcje styli uzytkownika do kazdej kom�rki tabeli.
 * 
 * @author Wojciech Sk�rski
 * @author Pawe� Szo�tysek
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
	 * Metoda zwraca tablic� styli.
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
