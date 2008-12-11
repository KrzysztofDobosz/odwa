package pl.pwr.odwa.client.visualization;

/**
 * Klasa zawierajaca style poszczególnych komórek w wierszu.
 * 
 * @author Wojciech Skórski
 * @author Paweł Szołtysek
 * 
 */
public class ReportStyleRow {

	/**
	 * Metoda zwraca ilość pól w wierszu.
	 * 
	 * @return ilość pól.
	 * 
	 */
	int getFieldsCount() 
	{
		int fieldCount=20;
		return fieldCount;
	}
	
	/**
	 * Metoda zwraca styl pola o konkretnym numerze.
	 * 
	 * @param num numer pola,
	 * @return styl pola.
	 * 
	 */
	String getFieldStyle(int num) 
	{
		String fieldStyle="";
		return fieldStyle;
	}
	
	/**
	 * Metoda zwraca styl pola o konkretnej nazwie.
	 * 
	 * @param name nazwa pola,
	 * @return styl pola.
	 * 
	 */
	String getFieldStyle(String name) 
	{
		String fieldStyle = "";
		return fieldStyle;
	}
}
