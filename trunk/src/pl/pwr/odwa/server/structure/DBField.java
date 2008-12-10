package pl.pwr.odwa.server.structure;

import pl.pwr.odwa.dbtypes.DBFieldDataType;
import pl.pwr.odwa.dbtypes.DBFieldType;


/**
 * Klasa reprezentuje strukture pojedynczego pola w tabeli
 *
 */
public class DBField
{
	private String name;
	
	/**
	 * Podaje nazwę pola
	 * @return nazwa
	 */
	String getName( )
	{
		return null;
	}
	/**
	 * Podaje typ pola
	 * @return typ pola
	 */
	DBFieldType getType( )
	{
		return null;
	}
	/**
	 * Podaje typ danych reprezentowanych w danym polu
	 * @return typ danych
	 */
	DBFieldDataType getDataType( )
	{
		return null;
	}
	/**
	 * Podaje wartosc domyslna pola
	 * @return wartosc domyslna lub null jesli brak
	 */
	Object getDefaultValue ( )
	{
		return null;
	}
	/**
	 * Sprawdza czy mozemy zapisac wartosc NULL w tym polu
	 * @return true jeżeli możliwe jest wstawienie warosci null
	 */
	boolean isNullable( )
	{
		return true;
	}
}
