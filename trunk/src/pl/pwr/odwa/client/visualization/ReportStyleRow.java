package pl.pwr.odwa.client.visualization;

/**
 * Klasa zawierajaca style poszczeg�lnych kom�rek w wierszu.
 * 
 * @author Wojciech Sk�rski
 * @author Pawe� Szo�tysek
 * 
 */
public class ReportStyleRow {

	/**
	 * Metoda zwraca ilo�� p�l w wierszu.
	 * 
	 * @return ilo�� p�l.
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
