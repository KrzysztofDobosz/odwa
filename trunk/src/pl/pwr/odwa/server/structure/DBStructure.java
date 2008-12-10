package pl.pwr.odwa.server.structure;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca strukture bazy danych
 *
 */
public class DBStructure
{
	private String name;
		
	/**
	 * Podaje nazwe bazy danych
	 * @return nazwa
	 */
	String getName()
	{
		return name;
	}

	/**
	 * Podaje listę tabel zawartych w bazie danych
	 * @return lista tabel
	 */
	ArrayList<DBTable> getTables()
	{
		
		return null;
	}
}
