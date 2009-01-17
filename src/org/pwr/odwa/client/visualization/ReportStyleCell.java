package org.pwr.odwa.client.visualization;


/*
 * ReportStyleCell is style handler for single cell. It's aim is to provide easy interface (constructor and getter) for ReportStyle class to manage styles.
 * Single instance of this class refers to one cell.
 *
 * @author Wojciech Skórski
 * @author Paweł Szołtysek
 */
public class ReportStyleCell {
	private String font_weight;
	private String font_style;
	private String font_decoration;
	private String color;

	/**
	 * Class constructor.
	 *
	 * @param font_weight1 CSS value for font-weight attribute
	 * @param font_style1 CSS value for font-style attribute
	 * @param font_decoration1 CSS value for font-decoration attribute
	 * @param color1 CSS value for color attribute
	 */
	public ReportStyleCell(String font_weight1, String font_style1, String font_decoration1, String color1) {
		font_weight = font_weight1;
		font_style = font_style1;
		font_decoration = font_decoration1;
		color = color1;
	}

	/**
	 * Checks the style value of Cell.
	 *
	 * @param style attribute which value is being checked.
	 * @return value of specified style.
	 * @return null if attribute style does not exist.
	 */
	public String getCellStyle(String style) {
		if (style.compareTo("font-weight") == 0) {
			return font_weight;
		}
		else if (style.compareTo("font-style") == 0) {
			return font_style;
		}
		else if (style.compareTo("font-decoration") == 0) {
			return font_decoration;
		}
		else if (style.compareTo("color") == 0) {
			return color;
		}
		else {
			return null;
		}
	}
}
