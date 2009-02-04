package org.pwr.odwa.client.reports;

import java.util.ArrayList;

import org.pwr.odwa.client.visualization.ReportStyle;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Abstract class extended by Static and DynamicReport.
 *  *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public abstract class Report implements IsSerializable {
	private static final long serialVersionUID = 1726723046281459668L;
	protected ArrayList<String> description;
	ReportStyle format;

	/**
	 * Method responsible for saving report to the file declared by the path filepath
	 */
	public void save(String filepath) {
	   System.out.println("ODWAClient: Report: save executed");
	   /*try {
		   FileOutputStream fileOutputStream = new FileOutputStream(filepath);
			ObjectOutputStream objectStream = new ObjectOutputStream(
					fileOutputStream);
			objectStream.writeObject(this);
			objectStream.close();
			System.out.println("Report saved to file " + filepath);
		} catch (IOException e) {
			System.out.println("Error in saving " + e);
			throw e;
		}*/
	}

	/**
	 * Get ReportStyle ({@link ReportStyle}) - all information about displaying the report by visualization module
	 */
	public ReportStyle getReportStyle() {
	   System.out.println("ODWAClient: Report: getReportStyle executed");
		return format;
	}

	/**
	 * Set ReportStyle ({@link ReportStyle}) 
	 */
	public void setReportStyle(ReportStyle format) {
		this.format = format;
	}

	/**
	 * Get containter ({@link ArrayList}) of bacic information about the report (ie date, author etc)
	 *
	 */
	public ArrayList<String> getDescription() {
		return description;
	}

	/**
	 * Set container ({@link ArrayList}) - description of the report
	 */
	public void setDescription(ArrayList<String> description) {
		this.description = description;
	}


}
