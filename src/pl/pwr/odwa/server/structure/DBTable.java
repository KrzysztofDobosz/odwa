package pl.pwr.odwa.server.structure;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca struktore pojedynczej tabeli w bazie danych
 *
 */
public class DBTable
{
	private String name;
	/**
	 * Podaje nazwe tabeli
	 * @return nazwa
	 */
	String getName( )
	{
		return name;
	}
	/**
	 * Podaje wszystkie pola zawarte w tabeli
	 * @return lista kolumn zawarych w tabeli
	 */
	ArrayList<DBField> getFields( )
	{
		return null;
	}
}
