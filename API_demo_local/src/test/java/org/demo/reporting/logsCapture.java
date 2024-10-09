package org.demo.reporting;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class logsCapture {

	public static void takeLog(String className, String msg) {
		DOMConfigurator.configure("../API_demo_local/layout.xml");
		Logger L = Logger.getLogger(className);
		L.info(msg);
		
	}
}
