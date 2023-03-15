package com.Salesforce.Automation.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Salesforce.Automation.Base.SalesforceAutomationBaseTest;

public class Log4jReports {
	public static void main(String args[])
	{
		Logger logfourj = LogManager.getLogger(SalesforceAutomationBaseTest.class.getName());
		logfourj.debug("debugmessage");
		logfourj.info("debugmessage");
		logfourj.warn("debugmessage");
		logfourj.error("debugmessage");
		logfourj.fatal("debugmessage");
	}
}
