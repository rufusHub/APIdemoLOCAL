package org.demo.testScripts;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

import java.io.IOException;
import java.util.Properties;

import org.demo.reporting.ReportHandling;
import org.demo.responseValidation.validateResponse;
import org.demo.testSteps.HTTPMethods;
import org.demo.utilities.PropertiesHandle;
import org.testng.annotations.Test;


public class TC4_GetAll_Jesus {
	
	@Test(groups = {"group2","get"})
	public void testCase2() throws IOException {
		
		//Report.
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tc2 = report.startTest("TC4_GetAll_Jesus");
		
		//Values.
		Properties p = PropertiesHandle.loadProperties("..//API_demo_local/URI.properties");
		String expectedData = "Jesús";
		
		//GET.
		HTTPMethods http = new HTTPMethods(p);
		Response res = http.getAlltMethod("OEN_URI");
		
		//Validation.
		Object result = validateResponse.statusCodeVAlidate(200, res, "TC4_GetAll_Jesus");
		
		//Status validation.
		if(result.equals(true)) {	
			
			//Data validation.
			Object result_data = validateResponse.dataValidate(expectedData, res, "[0].firstName", "TC4_GetAll_Jesus");
			if(result_data.equals(true)) {
				System.out.println("Satus OK, Data OK");
				tc2.log(LogStatus.PASS, "Test Case 4, is getting pass");
				report.endTest(tc2);
				report.flush();
			}
			else {
				System.out.println("Satus OK, Data NOK");
				tc2.log(LogStatus.FAIL, "Test Case 4, is getting fail");
				report.endTest(tc2);
				report.flush();
			}
		}
		else {
			System.out.println("Satus NOK");
			tc2.log(LogStatus.FAIL, "Test Case 4, is getting fail");
			report.endTest(tc2);
			report.flush();
		}
	}
}
