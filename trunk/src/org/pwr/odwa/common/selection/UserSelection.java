package org.pwr.odwa.common.selection;

import java.io.Serializable;

/**
 * Klasa komunikacji międzymodułowej przechowująca abstrakcyjne zapytanie
 * użytkownika analogicznie do języka MDX
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class UserSelection implements Serializable {

	private static final long serialVersionUID = -7023065549287722166L;
	private int dataBaseId;
	private Measure measure;
	private Axis column;
	private Axis row;
	private DimensionElSet slice;

	/**
	 * Konstruktor domyślny
	 */
	public UserSelection() {
	}

	/**
	 * Konstruktor UserSelection na bazie jego składowych stanowiąca
	 * złożenie metod typu set
	 * 
	 * @param dataBaseid
	 *            - identyfikator bazy danych
	 * @param measure
	 *            - użyta miara {@link Measure}
	 * @param column
	 *            - definicja (1) osi zagnieżdżonej {@link Axis}
	 * @param row
	 *            - definicja (2) osi zagnieżdżonej {@link Axis}
	 * @param slice
	 *            - użyty slice (plaster, cięcie) {@link DimensionElSet}
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
	 * Zwraca wykorzystywaną miarę ({@link null} jeśli członkowie wymiaru Miary
	 * są umieszczeni na osiach)
	 * 
	 * @return measure - uzyta miara
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * Ustawia wybraną przez użytkownika miarę ({@link null} jeśli członkowie
	 * wymiaru Miary są umieszczeni na osiach)
	 * 
	 * @param measure - wybrana przez użytkownika miara
	 */
	public void setMeasure(Measure measure) {
		this.measure = measure;
	}
	/**
	 * Zwraca (1) oś zagnieżdżoną ({@link Axis})
	 * @return column - (1) oś zagnieżdżona
	 */
	public Axis getColumn() {
		return column;
	}
	/**
	 * Ustawia (1) oś zagnieżdżoną {@link Axis}
	 * @param column
	 */
	public void setColumn(Axis column) {
		this.column = column;
	}
	/**
	 * Zwraca (2) oś zagnieżdżoną ({@link Axis})
	 * @return row - (2) oś zagnieżdżona
	 */
	public Axis getRow() {
		return row;
	}
	/**
	 * Ustawia (2) oś zagnieżdżoną {@link Axis}
	 * @param row
	 */
	public void setRow(Axis row) {
		this.row = row;
	}
	/**
	 * Zwraca slice (cięcie, plaster) filtrujący zapytanie użytkownika
	 * @return slice - cięcie, plaster filtrujący zapytanie użytkownika
	 */
	public DimensionElSet getSlice() {
		return slice;
	}
	/**
	 * Ustawia slice (cięcie, plaster) jako zbiór ({@link DimensionElSet}) elementów wymiarów
	 * @param slice - cięcie, plaster do ustawienia
	 */
	public void setSlice(DimensionElSet slice) {
		this.slice = slice;
	}

}