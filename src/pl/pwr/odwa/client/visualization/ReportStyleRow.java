package pl.pwr.odwa.client.visualization;

/**
 * Klasa zawierajaca style poszczególnych komórek w wierszu.
 * 
 * @author Wojciech Skórski
 * @author Pawe³ Szo³tysek
 * 
 */
public class ReportStyleRow {

	/**
	 * Metoda zwraca iloœæ pól w wierszu.
	 * 
	 * @return iloœæ pól.
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
