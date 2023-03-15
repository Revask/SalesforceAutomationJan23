package com.Salesforce.Automation.TestScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Salesforce.Automation.Base.SalesforceAutomationBaseTest;

@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)

public class SalesforceTestScript_leads extends SalesforceAutomationBaseTest {

	
	@BeforeClass
	@Parameters("browserName")
	public void leadsBeforeClass(String browser) throws InterruptedException
	{
		launch(browser);
		Thread.sleep(1000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected,"Leads tab- Firefox Browser SF login page not found");
		enterUsernamePassword();
		Thread.sleep(2000);
		login();
		Thread.sleep(6000);
	}
	
	@AfterClass
	public void leadsAfterClass() throws InterruptedException
	{
		Thread.sleep(4000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		report.logTestInfo("Logged out of salesforce account");
		Thread.sleep(2000);
		close();
		report.logTestInfo("Closing browser window");
	}
		
	
	@Test (priority =3,dependsOnMethods = {"leadsDropDownTest21"})       //TEST CASE 22
	public static void leadsDropDownTest22() throws InterruptedException
	{
		Thread.sleep(2000);
		setDropDown(3);
		String option = chosenOption();
		Thread.sleep(6000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		Thread.sleep(2000);
		enterUsernamePassword();
		Thread.sleep(1000);
		login();
		Thread.sleep(6000);
		locate("xpath","//a[contains(text(),'Leads')]"); //Leads Tab
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//select[@id='fcf']"); //Leads Drop down Tab
		waitExplicit();
		String actualOption = chosenOption();
		Thread.sleep(6000);
		assertEquals(actualOption,option);
		locate("xpath","//span[@class='fBody']//input[@type='button']"); // Go Button
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		
		
	}
	
	@Test   (priority = 2,dependsOnMethods = {"leadsTabLinkTest20"})     //TEST CASE 21
	public static void leadsDropDownTest21() throws InterruptedException
	{
		Thread.sleep(6000);
		locate("xpath","//select[@id='fcf']"); //Leads Drop down Tab
		waitExplicit();
		String[] options = {"All open leads","My unread leads","Recently viewed leads","Today's leads","View - Custom 1","View - Custom 2"};
		List<String> str = listOptions();
		if(str == null)
			report.logTestInfo("List options is null");
		boolean match = chkEquality(str,options);
		assertTrue(match);
		Thread.sleep(2000);
	}
	
	@Test(priority = 1)        //TEST CASE 20
	public static void leadsTabLinkTest20() throws InterruptedException
	{
		Thread.sleep(2000);
		locate("xpath","//a[contains(text(),'Leads')]"); //Leads Tab
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		try
		{
			closeLexDialog();
			report.logTestInfo("Lightning experience dialog box closed");
		}catch(NoSuchElementException e)
		{
			report.logTestInfo("No Lexdialog box found");
		}
		Thread.sleep(2000);
		String actualPageTitle = getPageTitle();
		 String valueExpected = expectedValue("leadsPageTitle");
		boolean match1 = actualPageTitle.equals(valueExpected);
		try
		{
			assertTrue(match1);
			report.logTestInfo("SFHome page loaded");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC20Step:1 SF Leads tab Home Page not found");
		}
		
	}
	@Test  (priority = 4)      //TEST CASE 23
	public static void todaysLeadsTest23() throws InterruptedException
	{
		
		Thread.sleep(3000);
		locate("xpath","//a[contains(text(),'Leads')]"); //Leads Tab
		waitExplicit();
		clickOn();
		Thread.sleep(6000);
		locate("xpath","//select[@id='fcf']"); //Leads Drop down Tab
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		setDropDown(3);
		String expectedOption = chosenOption();
		clickOn();
		Thread.sleep(10000);
		locate("xpath","//span[@class='fBody']//input[@type='button']"); // Go Button
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		locate("xpath","//select[@id='00B5i000004Wpsi_listSelect']"); //Leads Drop down Tab
		waitExplicit();
		String actualOption = chosenOption();
		Thread.sleep(6000);
		try
		{
			assertEquals(actualOption,expectedOption);
			report.logTestInfo("Leads page with selected option displayed");
		}catch(AssertionError e)
		{
			report.logTestInfo("leads page  displayed with different option");
		}
		
	}
	
	@Test(priority = 5)        //TEST CASE 24
	public static void checkNewLeadsTest24() throws InterruptedException
	{
		Thread.sleep(3000);
		locate("xpath","//a[contains(text(),'Leads')]"); //Leads Tab
		waitExplicit();
		clickOn();
		Thread.sleep(6000);
		locate("xpath","//td[contains(@class,'pbButton')]//input[contains(@type,'button')]"); //New Button
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		locate("xpath","//input[@id='name_lastlea2']"); //Last name field
		waitExplicit();
		enter("ABCD");
		Thread.sleep(3000);
		locate("xpath","//input[@id='lea3']"); //Company name field
		waitExplicit();
		enter("ABCD");
		Thread.sleep(2000);
		// Save button
		locate("xpath","//body[contains(@class,'ext-gecko ext-gecko3 sfdcBody brandQuaternaryBgr')]/div[@id='contentWrapper']/div[contains(@class,'bodyDiv brdPalette brandPrimaryBrd')]/table[@id='bodyTable']/tbody/tr/td[@id='bodyCell']/form[@id='editPage']/div[@id='ep']/div[contains(@class,'pbHeader')]/table[contains(@cellspacing,'0')]/tbody/tr/td[@id='topButtonRow']/input[1]"); 
		waitExplicit();
		clickOn();
		Thread.sleep(6000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("leadsNewPage");
		boolean match = actualPageTitle.equalsIgnoreCase(valueExpected);
		try
		{
			assertTrue(match);
			report.logTestInfo("New created lead view page displayed");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC24Step:5 Newly created lead view page not found");
		}	
	}
}