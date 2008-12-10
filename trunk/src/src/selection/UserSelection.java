package selection;

import java.io.Serializable;

/**
 * Klasa komunikacji mi�dzymodu�owej przechowuj�ca abstrakcyjne zapytanie
 * u�ytkownika analogicznie do j�zyka MDX
 * 
 * @author Micha� Brzezi�ski-Spiczak
 */
public class UserSelection implements Serializable {

	private static final long serialVersionUID = -7023065549287722166L;
	private int dataBaseId;
	private Measure measure;
	private Axis column;
	private Axis row;
	private DimensionElSet slice;

	/**
	 * Konstruktor domy�lny
	 */
	public UserSelection() {
	}

	/**
	 * Konstruktor UserSelection na bazie jego sk�adowych stanowi�ca
	 * z�o�enie metod typu set
	 * 
	 * @param dataBaseid
	 *            - identyfikator bazy danych
	 * @param measure
	 *            - u�yta miara {@link Measure}
	 * @param column
	 *            - definicja (1) osi zagnie�d�onej {@link Axis}
	 * @param row
	 *            - definicja (2) osi zagnie�d�onej {@link Axis}
	 * @param slice
	 *            - u�yty slice (plaster, ci�cie) {@link DimensionElSet}
	 */
	public UserSelection(int dataBaseid, Measure measure, Axis column, Axis row,
			DimensionElSet slice) {
		setDataBaseId(dataBaseid);
		setMeasure(measure);
		setColumn(column);
		setRow(row);
		setSlice(slice);
	}

	/**
	 * Zwraca identyfikator bazy danych
	 * 
	 * @return dataBaseId - identyfikator bazy danych
	 */
	public int getDataBaseId() {
		return dataBaseId;
	}

	/**
	 * Ustawia identyfikator bazy danych dataBaseId
	 * 
	 * @param dataBaseId
	 *            - identyfikator bazy danych
	 */
	public void setDataBaseId(int dataBaseId) {
		this.dataBaseId = dataBaseId;
	}

	/**
	 * Zwraca wykorzystywan� miar� ({@link null} je�li cz�onkowie wymiaru Miary
	 * s� umieszczeni na osiach)
	 * 
	 * @return measure - uzyta miara
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * Ustawia wybran� przez u�ytkownika miar� ({@link null} je�li cz�onkowie
	 * wymiaru Miary s� umieszczeni na osiach)
	 * 
	 * @param measure - wybrana przez u�ytkownika miara
	 */
	public void setMeasure(Measure measure) {
		this.measure = measure;
	}
	/**
	 * Zwraca (1) o� zagnie�d�on� ({@link Axis})
	 * @return column - (1) o� zagnie�d�ona
	 */
	public Axis getColumn() {
		return column;
	}
	/**
	 * Ustawia (1) o� zagnie�d�on� {@link Axis}
	 * @param column
	 */
	public void setColumn(Axis column) {
		this.column = column;
	}
	/**
	 * Zwraca (2) o� zagnie�d�on� ({@link Axis})
	 * @return row - (2) o� zagnie�d�ona
	 */
	public Axis getRow() {
		return row;
	}
	/**
	 * Ustawia (2) o� zagnie�d�on� {@link Axis}
	 * @param row
	 */
	public void setRow(Axis row) {
		this.row = row;
	}
	/**
	 * Zwraca slice (ci�cie, plaster) filtruj�cy zapytanie u�ytkownika
	 * @return slice - ci�cie, plaster filtruj�cy zapytanie u�ytkownika
	 */
	public DimensionElSet getSlice() {
		return slice;
	}
	/**
	 * Ustawia slice (ci�cie, plaster) jako zbi�r ({@link DimensionElSet}) element�w wymiar�w
	 * @param slice - ci�cie, plaster do ustawienia
	 */
	public void setSlice(DimensionElSet slice) {
		this.slice = slice;
	}

}