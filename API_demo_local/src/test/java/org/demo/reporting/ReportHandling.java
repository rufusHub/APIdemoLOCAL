package org.demo.reporting;

import com.relevantcodes.extentreports.ExtentReports;

public class ReportHandling {

	public static ExtentReports handleReport() {
		
		ExtentReports rep = new ExtentReports("C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\API_demo_local\\demoLocalReport.html", false);
		return rep;
	}
}
