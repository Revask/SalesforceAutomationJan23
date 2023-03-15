package com.Salesforce.Automation.TestScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Salesforce.Automation.Base.SalesforceAutomationBaseTest;

@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)
public class SalesforceTestScript_Opportunity extends SalesforceAutomationBaseTest{
	@BeforeClass
	@Parameters("browserName")
	public void opportunitiesBeforeClass(String browser) throws InterruptedException
	{
		launch(browser);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected);
		enterUsernamePassword();
		login();
		Thread.sleep(4000);
		 actualPageTitle = getPageTitle();
		 valueExpected = expectedValue("SFHomePageTitle");
		boolean match1 = actualPageTitle.equals(valueExpected);
		assertTrue(match1);
		
	}
	
	@BeforeMethod
	public void opportunitiesBeforeMethod()throws InterruptedException
	{
		Thread.sleep(6000);
		locate("id","Opportunity_Tab");     //opportunity tab
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("opportunitiesHomePageTitle");
		boolean match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
		
	}

	
	@AfterClass
	public void opportunitiesAfterClass() throws InterruptedException
	{
		Thread.sleep(2000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		report.logTestInfo("Logged out of Salesforce account");
		Thread.sleep(2000);
		close();
		report.logTestInfo("closing the browser window");
	}
	@Test(priority =1)		//TEST CASE 15
	public static void opportunitiesDropDownVerifyTest15() throws InterruptedException
	{
		boolean present;
		try
		{
			closeLexDialog();
			Thread.sleep(1000);
			locate("xpath","//select[@id='fcf']");
			waitExplicit();
			clickOn();
			present = true;
			assertTrue(present);
		}catch(NoSuchElementException e)
		{
			present = false;
		}
	}
	@Test    (priority =2)        //TEST CASE 16
	public static void createNewOpportunityTest16() throws InterruptedException
	{
		Thread.sleep(3000);
		locate("xpath","//td[@class='pbButton']//input[@type='button']");//NewTab
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//input[@id='opp3']"); // Opportunity name field
		waitExplicit();
		enter("Kite Oppn");
		locate("xpath","//input[@id='opp4']"); // Account name field
		waitExplicit();
		enter("Abby corp");
		locate("xpath","//input[@id='opp9']"); // Close Date field
		waitExplicit();
		clickOn();
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/div[2]/table[1]/tbody[1]/tr[4]/td[3]"); // Choose Date field
		waitExplicit();
		clickOn();
		locate("xpath","//select[@id='opp11']"); // Stage field
		waitExplicit();
		setDropDown(4);
		locate("xpath","//input[@id='opp12']"); // Probability field
		waitExplicit();
		enter("");
		locate("xpath","//select[@id='opp6']"); // Lead field
		waitExplicit();
		setDropDown(3);
		locate("xpath","//input[@id='opp17']"); // Primary campaign source field
		waitExplicit();
		enter("");
		// Save Button
		locate("xpath","//body[contains(@class,'ext-gecko ext-gecko3 sfdcBody brandQuaternaryBgr')]/div[@id='contentWrapper']/div[contains(@class,'bodyDiv brdPalette brandPrimaryBrd')]/table[@id='bodyTable']/tbody/tr/td[@id='bodyCell']/form[@id='editPage']/div[@id='ep']/div[contains(@class,'pbHeader')]/table[contains(@cellspacing,'0')]/tbody/tr/td[@id='topButtonRow']/input[1]"); 
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		boolean present = false;
		try
		{
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[4]/div[1]/table[1]/tbody[1]/tr[1]/td[1]");
		waitExplicit();
		report.logTestInfo("Opportunity  details displayed");
		present = true;
		}catch(NoSuchElementException e)
		{
			present = false;			
			report.logTestInfo("opportunity details not found");
		}
		assertTrue(present);
	}
	
	@Test    (priority =3)    //TEST CASE 17
	public static void opportunityPipelineTest17() throws InterruptedException
	{
		
		locate("xpath","//a[contains(text(),'Opportunity Pipeline')]");//opportunity Pipeline link under reports 
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("opportunitiesPipelinePageTitle");
		boolean match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
	}
	@Test   (priority = 4)     //TEST CASE 18
	public static void StuckOpportunitiesReportTest18() throws InterruptedException
	{
		
		locate("xpath","//a[contains(text(),'Stuck Opportunities')]");// Stuck Opportunity Link under reports 
		waitExplicit();
		clickOn();
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("opportunitiesStuckPageTitle");
		boolean match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
	}	
	
	@Test   (priority =5)     //TEST CASE 19
	public static void quarterlySummarReportTest19() throws InterruptedException
	{
		
		locate("xpath","//select[@id='quarter_q']");//Quarterly reports-Interval
		waitExplicit();
		setDropDown(3);
		locate("xpath","//select[@id='open']");//Quarterly reports-Include
		waitExplicit();
		setDropDown(1);
		locate("xpath","//table[@class='opportunitySummary']//tbody//tr//td//input[@type='submit']");//Run Report
		waitExplicit();
		clickOn();
		
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("opportunitiesQurterlyReportPageTitle");
		boolean match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
	}
}
