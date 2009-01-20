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
	private String dataBaseId;
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

	/**
	 * Zwraca identyfikator bazy danych
	 * 
	 * @return dataBaseId - identyfikator bazy danych
	 */
	public String getDataBaseId() {
		return dataBaseId;
	}

	/**
	 * Ustawia identyfikator bazy danych dataBaseId
	 * 
	 * @param dataBaseId
	 *            - identyfikator bazy danych
	 */
	public void setDataBaseId(String dataBaseId) {
		this.dataBaseId = dataBaseId;
	}

	/**
	 * Zwraca wykorzystywaną miarę ({@link null} jeśli członkowie wymiaru
	 * Miary są umieszczeni na osiach)
	 * 
	 * @return measure - uzyta miara
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * Ustawia wybraną przez użytkownika miarę ({@link null} jeśli
	 * członkowie wymiaru Miary są umieszczeni na osiach)
	 * 
	 * @param measure
	 *            - wybrana przez użytkownika miara
	 */
	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	/**
	 * Zwraca (1) oś zagnieżdżoną ({@link Axis})
	 * 
	 * @return column - (1) oś zagnieżdżona
	 */
	public Axis getColumn() {
		return column;
	}

	/**
	 * Ustawia (1) oś zagnieżdżoną {@link Axis}
	 * 
	 * @param column
	 */
	public void setColumn(Axis column) {
		this.column = column;
	}

	/**
	 * Zwraca (2) oś zagnieżdżoną ({@link Axis})
	 * 
	 * @return row - (2) oś zagnieżdżona
	 */
	public Axis getRow() {
		return row;
	}

	/**
	 * Ustawia (2) oś zagnieżdżoną {@link Axis}
	 * 
	 * @param row
	 */
	public void setRow(Axis row) {
		this.row = row;
	}

	/**
	 * Zwraca slice (cięcie, plaster) filtrujący zapytanie użytkownika
	 * 
	 * @return slice - cięcie, plaster filtrujący zapytanie użytkownika
	 */
	public DimensionElSet getSlice() {
		return slice;
	}

	/**
	 * Ustawia slice (cięcie, plaster) jako zbiór ({@link DimensionElSet})
	 * elementów wymiarów
	 * 
	 * @param slice
	 *            - cięcie, plaster do ustawienia
	 */
	public void setSlice(DimensionElSet slice) {
		this.slice = slice;
	}

	public String toString() {
		String selection = new String();

		if (getDataBaseId() != null)
			selection += "Database: " + getDataBaseId() + "<br>";
		else
			selection += "Database: null <br>";
		if (measure.getMeasureUid() != null)
			selection += "Measure: " + measure.getMeasureUid() + "<br>";
		else
			selection += "Measure: null <br>";
		AxisElement el;
		selection += "<br><b>Column:</b><br>";

		for (int i = 0; i < getColumn().getAxisElementAmount(); i++) {
			selection += " <b>  Axis_</b>" + (i + 1) + " - function: ";
			el = getColumn().getAxisElement(i);
			DimensionElSet dimelset = el.getDimensionElSet();
			Function func = el.getFunction();

			selection += func.getFunctionId();
			selection += "<br>{";
			DimensionEl dimel;
			for (int k = 0; k < dimelset.getDimensionElAmount(); k++) {
				dimel = dimelset.getDimensionEl(k);
				// if (dimel.getMethod() != null)
				selection += "(" + dimel.getPath().getPath() + ", "
						+ dimel.getMethod().getMethodId() + ")";
				// else
				// selection += "(" + dimel.getPath().getPath() + ", null)";

			}
			selection += "}<br><br>";
		}
		selection += "<br><b>Row:</b><br>";

		for (int i = 0; i < getRow().getAxisElementAmount(); i++) {
			selection += " <b>  Axis_</b>" + (i + 1) + " - function: ";
			el = getRow().getAxisElement(i);
			DimensionElSet dimelset = el.getDimensionElSet();
			Function func = el.getFunction();
			selection += func.getFunctionId() + "<br>{";
			DimensionEl dimel;
			for (int k = 0; k < dimelset.getDimensionElAmount(); k++) {
				dimel = dimelset.getDimensionEl(k);
				// if (dimel.getMethod() != null)
				selection += "(" + dimel.getPath().getPath() + ", "
						+ dimel.getMethod().getMethodId() + ")";
				// else
				// selection += "(" + dimel.getPath().getPath() + ", null)";

			}
			selection += "}<br><br>";
		}

		selection += "Slice:<br>";
		DimensionElSet dimelset = getSlice();
		DimensionEl dimel;
		for (int k = 0; k < dimelset.getDimensionElAmount(); k++) {
			dimel = dimelset.getDimensionEl(k);
			if (dimel.getMethod() != null)
				selection += "(" + dimel.getPath().getPath() + ", "
						+ dimel.getMethod().getMethodId() + ")";
			else
				selection += "(" + dimel.getPath().getPath() + ", null)";

		}

		return selection;

	}

	public void load(SelectionLoader sloader) {
		Measure m = new Measure();
		m.setMeasureUid(sloader.getMeasure());
		setMeasure(m);

		//setDataBaseId(sloader.getDatabaseId());

		Axis col_ = new Axis();

		Function func = new Function();

		for (int i = 0; i < sloader.getCols().size(); i++) {
			AxisElement col_el = new AxisElement();
			DimensionElSet dimelset = new DimensionElSet();
			for (int k = 0; k < sloader.getCols().get(i).size(); k++) {
				DimensionEl dimel = new DimensionEl();
				String el = sloader.getCols().get(i).get(k);
				Path path = new Path();
				Method method = new Method();
				if (el.contains(".")) {
					path.setPath(el.split("\\.")[0]);
					if (el.split("\\.").length == 2)
						method.setMethodId(el.split("\\.")[1]);

				} else
					path.setPath(el);
				dimel.setMethod(method);
				dimel.setPath(path);
				dimelset.addDimensionEl(dimel);

			}
			col_el.setDimensionElSet(dimelset);
			col_el.setFunction(func);
			col_.addAxisElement(col_el);
		}
		setColumn(col_);
		
		Axis row_ = new Axis();
		for (int i = 0; i < sloader.getRows().size(); i++) {
			AxisElement row_el = new AxisElement();
			DimensionElSet dimelset = new DimensionElSet();
			for (int k = 0; k < sloader.getRows().get(i).size(); k++) {
				DimensionEl dimel = new DimensionEl();
				String el = sloader.getRows().get(i).get(k);
				Path path = new Path();
				Method method = new Method();
				if (el.contains(".")) {
					path.setPath(el.split("\\.")[0]);
					if (el.split("\\.").length == 2)
						method.setMethodId(el.split("\\.")[1]);

				} else
					path.setPath(el);
				dimel.setMethod(method);
				dimel.setPath(path);
				dimelset.addDimensionEl(dimel);

			}
			row_el.setDimensionElSet(dimelset);
			row_el.setFunction(func);
			row_.addAxisElement(row_el);
		}
		setRow(row_);
		
		DimensionElSet dimelset = new DimensionElSet();
		for (int k = 0; k < sloader.getBackground().size(); k++) {
			DimensionEl dimel = new DimensionEl();
			String el = sloader.getBackground().get(k);
			Path path = new Path();
			Method method = new Method();
			if (el.contains(".")) {
				path.setPath(el.split("\\.")[0]);
				if (el.split("\\.").length == 2)
					method.setMethodId(el.split("\\.")[1]);

			} else
				path.setPath(el);
			dimel.setMethod(method);
			dimel.setPath(path);
			dimelset.addDimensionEl(dimel);

		}
		setSlice(dimelset);
	}
}