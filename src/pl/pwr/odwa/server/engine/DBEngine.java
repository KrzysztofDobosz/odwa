package pl.pwr.odwa.server.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.pwr.odwa.result.DBResult;
import pl.pwr.odwa.selection.UserSelection;
import pl.pwr.odwa.server.structure.DBStructure;

/**
 * 
 * 
 */
public class DBEngine
{
	Connection conn;

	/**
	 * Laczy ze wskazana baza danych, dla zadanego uzytkownika z podanym haslem
	 * 
	 * @param url adres bazy
	 * @param user nazwa uzytkownika
	 * @param password haslo
	 */
	void connect(String url, String user, String password)
	{
		try
		{
			conn = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Ko�czy polaczenie z baza danych
	 */
	void disconnect()
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Wykonuje zapytanie do bay danych
	 * 
	 * @param Query Zapytanie u�ytkownika
	 * @return Wynik zapytania u�ytkownika
	 */
	DBResult executeQuery(UserSelection Query)
	{
		return null;
	}

	/**
	 * Zwraca wszystkie dost�pne bazy danych
	 * 
	 * @return lista dostepnych baz danych pod danym adresem
	 */
	ArrayList<DBStructure> getDatabases(String url)
	{
		return null;
	}
}
