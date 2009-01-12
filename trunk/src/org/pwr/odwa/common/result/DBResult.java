package org.pwr.odwa.common.result;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Reprezentuje wynik zapytania bazodanowego
 *
 */
public class DBResult implements IsSerializable
{
	/**
	 * Zwraca kolejny wiersz wyniku
	 * @return
	 */
	DBRow fetchRow( )
	{
		return null;
	}
	/**
	 * Zwraca tablice wynik�w
	 * @return
	 */
	ArrayList<DBRow> fetchArray ( )
	{
		return null;
	}
	/**
	 * Sortuje wyniki wg. p�l zadanych w fieldNames (w odpowiadajacej kolejnosci)
	 * @param fieldNames lista pol wedlug których należy sortowac
	 */
	void sort (ArrayList<String> fieldNames )
	{

	}

}
