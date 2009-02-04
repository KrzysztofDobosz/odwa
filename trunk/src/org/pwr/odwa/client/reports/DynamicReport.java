package org.pwr.odwa.client.reports;

import java.util.Date;

import org.pwr.odwa.client.visualization.ReportStyle;
import org.pwr.odwa.common.selection.UserSelection;

/**
 * 
 * DynamicReport class extends Report class ({#ling Report})
 * 
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 */
public class DynamicReport extends Report {
	private static final long serialVersionUID = -5321517277324547126L;
	UserSelection selection;
	ReportStyle format;

	public DynamicReport() {
	};

	/**
	 * Constructor of dynamic report baed on User Selection
	 * {@link UserSelection} and report style {@link ReportStyle}.
	 */
	public DynamicReport(UserSelection selection, ReportStyle format) {
		System.out.println("ODWAClient: DynamicReport: constructor executed");
		this.selection = selection;
		this.format = format;
		// description.add("Date = " + new Date().toString());
		// description.add("Author = " + "");
	}

	/**
	 * Read the report from file
	 */
	public DynamicReport(String filepath) {
		System.out
				.println("ODWAClient: DynamicReport: filepath constructor executed");
		/*
		 * try { FileInputStream file = new FileInputStream(filepath);
		 * ObjectInputStream objectstream = new ObjectInputStream(file); Object
		 * report = objectstream.readObject(); if (report instanceof
		 * DynamicReport) { DynamicReport rep = (DynamicReport) report;
		 * this.description = rep.description; this.selection = rep.selection;
		 * this.format = rep.format; }
		 * 
		 * } catch (IOException e) {
		 * System.out.println("Error in opening report " + e); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); }
		 */
	}

	/**
	 * Get user selection from report {@link UserSelection}
	 */

	public UserSelection getUserSelection() {
		System.out
				.println("ODWAClient: DynamicReport: getUserSelection executed");
		return selection;
	}

	/**
	 * Set user selection {@link UserSelection}
	 * 
	 * @param selection
	 *            - zapytanie użytkowinka
	 */
	public void setUserSelection(UserSelection selection) {
		this.selection = selection;
	}

}
