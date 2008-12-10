package pl.pwr.odwa.dbtypes;

/**
 * Reprezentuje typ pola w bazie danych
 *
 */
public enum DBFieldType
{
	/**
	 * Zwykly element
	 */
	DBElement,
	/**
	 * Klucz glówny
	 */
	DBPrimaryKey,
	/**
	 * Klucz obcy
	 */
	DBForeignKey;
}
