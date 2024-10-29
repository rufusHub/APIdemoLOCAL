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


public class TC6_GetAll_Elie {
	
	@Test(groups = {"group3","get"})
	public void testCase2() throws IOException {
		
		//Report.
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tc2 = report.startTest("TC6_GetAll_Elie");
		
		//Values.
		Properties p = PropertiesHandle.loadProperties("..//API_demo_local/URI.properties");
		String expectedData = "Elie";
		
		//GET.
		HTTPMethods http = new HTTPMethods(p);
		Response res = http.getAlltMethod("OSE_URI");
		
		//Validation.
		Object result = validateResponse.statusCodeVAlidate(200, res, "TC6_GetAll_Elie");
		
		//Status validation.
		if(result.equals(true)) {	
			
			//Data validation.
			Object result_data = validateResponse.dataValidate(expectedData, res, "[0].firstName", "TC6_GetAll_Elie");
			if(result_data.equals(true)) {
				System.out.println("Satus OK, Data OK");
				tc2.log(LogStatus.PASS, "Test Case 6, is getting pass");
				report.endTest(tc2);
				report.flush();
			}
			else {
				System.out.println("Satus OK, Data NOK");
				tc2.log(LogStatus.FAIL, "Test Case 6, is getting fail");
				report.endTest(tc2);
				report.flush();
			}
		}
		else {
			System.out.println("Satus NOK");
			tc2.log(LogStatus.FAIL, "Test Case 6, is getting fail");
			report.endTest(tc2);
			report.flush();
		}
	}
}
