package org.demo.testScripts;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.demo.reporting.ReportHandling;
import org.demo.responseValidation.validateResponse;
import org.demo.testSteps.HTTPMethods;
import org.demo.utilities.JsonHandle;
import org.demo.utilities.JsonParsing;
import org.demo.utilities.JsonReplacement;
import org.demo.utilities.PropertiesHandle;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC7_Post_Roberto {
	
	public static String returnIdValue;
	
	@Test(groups = {"group4","post"})
	public void testCase1() throws IOException {
		
		//Report.
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tc1 = report.startTest("TC7_Post_Roberto");
		
		//Values.
		Properties p = PropertiesHandle.loadProperties("..//API_demo_local/URI.properties");
		String requestBody = JsonHandle.readJsonData("..//API_demo_local/src/test/java/org/demo/resources/requestBody_array_4.json"); 
		String urikeyname = "KST_URI";
		
		//Generate random id on requestBody.
		Random r = new Random();		
		Integer idValue = r.nextInt();
		requestBody = JsonReplacement.assignJsonValue(requestBody, "id", idValue.toString());
		
		//POST.
		HTTPMethods http = new HTTPMethods(p);
		Response resobj =  http.postMethod(requestBody, urikeyname);
		
		//Validation.
		Object result = validateResponse.statusCodeVAlidate(201, resobj, "TC7_Post_Roberto" );
		
		if(result.equals(true)) {
			tc1.log(LogStatus.PASS, "Test Case 7, is getting pass.");
			report.endTest(tc1);
			report.flush();
		}
		else {
			tc1.log(LogStatus.FAIL, "Test Case 7, is getting fail.");
			report.endTest(tc1);
			report.flush();
		}
		
		returnIdValue = JsonParsing.doParsing(resobj, "id");
	}
}