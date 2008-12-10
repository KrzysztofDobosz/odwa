package selection;

import java.io.Serializable;

/**
 * Klasa komunikacji miêdzymodu³owej przechowuj¹ca abstrakcyjne zapytanie
 * u¿ytkownika analogicznie do jêzyka MDX
 * 
 * @author Micha³ Brzeziñski-Spiczak
 */
public class UserSelection implements Serializable {

	private static final long serialVersionUID = -7023065549287722166L;
	private int dataBaseId;
	private Measure measure;
	private Axis column;
	private Axis row;
	private DimensionElSet slice;

	/**
	 * Konstruktor domyœlny
	 */
	public UserSelection() {
	}

	/**
	 * Konstruktor UserSelection na bazie jego sk³adowych stanowi¹ca
	 * z³o¿enie metod typu set
	 * 
	 * @param dataBaseid
	 *            - identyfikator bazy danych
	 * @param measure
	 *            - u¿yta miara {@link Measure}
	 * @param column
	 *            - definicja (1) osi zagnie¿d¿onej {@link Axis}
	 * @param row
	 *            - definicja (2) osi zagnie¿d¿onej {@link Axis}
	 * @param slice
	 *            - u¿yty slice (plaster, ciêcie) {@link DimensionElSet}
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
	 * Zwraca wykorzystywan¹ miarê ({@link null} jeœli cz³onkowie wymiaru Miary
	 * s¹ umieszczeni na osiach)
	 * 
	 * @return measure - uzyta miara
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * Ustawia wybran¹ przez u¿ytkownika miarê ({@link null} jeœli cz³onkowie
	 * wymiaru Miary s¹ umieszczeni na osiach)
	 * 
	 * @param measure - wybrana przez u¿ytkownika miara
	 */
	public void setMeasure(Measure measure) {
		this.measure = measure;
	}
	/**
	 * Zwraca (1) oœ zagnie¿d¿on¹ ({@link Axis})
	 * @return column - (1) oœ zagnie¿d¿ona
	 */
	public Axis getColumn() {
		return column;
	}
	/**
	 * Ustawia (1) oœ zagnie¿d¿on¹ {@link Axis}
	 * @param column
	 */
	public void setColumn(Axis column) {
		this.column = column;
	}
	/**
	 * Zwraca (2) oœ zagnie¿d¿on¹ ({@link Axis})
	 * @return row - (2) oœ zagnie¿d¿ona
	 */
	public Axis getRow() {
		return row;
	}
	/**
	 * Ustawia (2) oœ zagnie¿d¿on¹ {@link Axis}
	 * @param row
	 */
	public void setRow(Axis row) {
		this.row = row;
	}
	/**
	 * Zwraca slice (ciêcie, plaster) filtruj¹cy zapytanie u¿ytkownika
	 * @return slice - ciêcie, plaster filtruj¹cy zapytanie u¿ytkownika
	 */
	public DimensionElSet getSlice() {
		return slice;
	}
	/**
	 * Ustawia slice (ciêcie, plaster) jako zbiór ({@link DimensionElSet}) elementów wymiarów
	 * @param slice - ciêcie, plaster do ustawienia
	 */
	public void setSlice(DimensionElSet slice) {
		this.slice = slice;
	}

}