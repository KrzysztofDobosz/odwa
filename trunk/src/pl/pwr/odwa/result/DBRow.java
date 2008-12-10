package pl.pwr.odwa.result;

import pl.pwr.odwa.dbtypes.DBFieldDataType;

/**
 * Klasa reprezentuje pojedyńczy rekord wyniku zapytania bazodanowego 
 *
 */
public class DBRow
{
	/**
	 * Podaje ilosc kolumn wyniku
	 * @return Ilosc kolumn
	 */
	int getFieldsCount ( )
	{
		return 0;
	}
	/**
	 * Podaje typ pola o numerze num
	 * @param num numer pola
	 * @return typ pola
	 */
	DBFieldDataType getType (int num)
	{
		return null;
	}
	/**
	 * Podaje typ pola zadanego nazwa name
	 * @param name nazwa pola
	 * @return typ pola
	 */
	DBFieldDataType getType ( String name )
	{
		return null;
	}
	/**
	 * Podaje zawartosc pola o numerze num
	 * @param num numer pola
	 * @return wartosc pola
	 */
	Object getFieldVal ( int num)
	{
		return null;
	}
	/**
	 * Podaje zawartosc pola o nazwie name
	 * @param name nazwa pola
	 * @return wartosc pola
	 */
	Object getFieldVal ( String name ) 
	{
		return null;
	}
}
