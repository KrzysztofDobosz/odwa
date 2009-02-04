package org.pwr.odwa.client.reports;

import java.util.Date;

import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.common.result.DBResult;

/**
 * Static report class extends {@link Report}
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class StaticReport extends Report {
	private static final long serialVersionUID = -4481525925179844309L;
	private DBResult result;

	public StaticReport() {
	}

	/**
	 * Static report constructor based on DBResult (result of selection - {@link DBResult})
	 * and ReportStyle ({@link ReportStyle}).
	 *
	 */
	public StaticReport(DBResult result, ReportStyle format) {
		System.out.println("ODWAClient: StaticReport: constructor executed");
	   this.result = result;
		this.format = format;
		//description.add("Date = " + new Date().toString());
		//description.add("Author = " + "");
	}

	/**
	*Read report from file
	 *
	 */
	public StaticReport(String filepath) {
	   System.out.println("ODWAClient: StaticReport: filepath constructor executed");
		/*try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			ObjectInputStream objectstream = new ObjectInputStream(
					fileInputStream);
			Object readObject = objectstream.readObject();
			if (readObject instanceof StaticReport) {
				StaticReport rep = (StaticReport) readObject;
				this.description = rep.description;
				this.result = rep.result;
				this.format = rep.format;
			}

		} catch (IOException e) {
			System.out.println("Error in opening report " + e);
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}*/
	}

	/**
	 * Get DBResult from report
	 */
	public DBResult getResult() {
	   System.out.println("ODWAClient: StaticReport: getResult executed");
		//return result;
	   return new DBResult();
	}

	/**
	* Set DBresult to be saved in the report
	 */
	public void setResult(DBResult result) {
		this.result = result;
	}
}