package org.pwr.odwa.common.selection;

import java.io.Serializable;

/**
 *
 * Communication class which contains abstraction user selection (an analogy to
 * MDX)
 *
 * @author Katarzyna Rzerzicha
 * @author Michal‚ Brzezinski-Spiczak
 */
public class UserSelection implements Serializable {

	private static final long serialVersionUID = -7023065549287722166L;
	private String dataBaseId;
	private Measure measure;
	private Axis column;
	private Axis row;
	private DimensionElSet slice;

	public UserSelection() {
	}

	/**
	 * Get database id
	 *
	 */
	public String getDataBaseId() {
		return dataBaseId;
	}

	/**
	 * Set dataBaseId
	 *
	 */
	public void setDataBaseId(String dataBaseId) {
		this.dataBaseId = dataBaseId;
	}

	/**
	 * Get {@link {@link Measure} ({@link null} if Measure elements put on
	 * axis)
	 *
	 */
	public Measure getMeasure() {
		return measure;
	}

	/**
	 * Set {@link Measure}
	 */
	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	/**
	 * Get (1) nested ({@link Axis})
	 *
	 */
	public Axis getColumn() {
		return column;
	}

	/**
	 * Set (1) nested ({@link Axis})
	 *
	 */
	public void setColumn(Axis column) {
		this.column = column;
	}

	/**
	 * Get (2) nested ({@link Axis})
	 *
	 */
	public Axis getRow() {
		return row;
	}

	/**
	 * Set (2) nested ({@link Axis})
	 *
	 */
	public void setRow(Axis row) {
		this.row = row;
	}

	/**
	 * Get slice/background ({@link DimensionElSet}) filtering selection
	 *
	 */
	public DimensionElSet getSlice() {
		return slice;
	}

	/**
	 * Set slice/background ({@link DimensionElSet}) filtering selection
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

	/**
	 * Load {@link UserSelection} from {@link SelectionLoader}
	 * @param sloader
	 */

	public void load(SelectionLoader sloader) {
		Measure m = new Measure();
		m.setMeasureUid(sloader.getMeasure());
		setMeasure(m);

		// setDataBaseId(sloader.getDatabaseId());

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

    public String toMDX() {
        StringBuilder builder = new StringBuilder();

        builder.append("select ");

        if (column != null) {
            builder.append(column.toMDX() + " on columns");

            if (row != null) {
                builder.append(", ");
            }
        }

        if (row != null) {
            builder.append(row.toMDX() + " on rows");
        }

        builder.append(" from [" + dataBaseId + "]");

        if (measure != null && slice != null) {
            builder.append(" where { ");

            builder.append(measure.toMDX());
            builder.append(", ");
            builder.append(slice.toMDX());

            builder.append(" }");
        } else if (measure != null) {
            builder.append(" where ");
            builder.append(measure.toMDX());
        } else if (measure != null) {
            builder.append(" where ");
            builder.append(slice.toMDX());
        }

        return builder.toString();
    }
}

