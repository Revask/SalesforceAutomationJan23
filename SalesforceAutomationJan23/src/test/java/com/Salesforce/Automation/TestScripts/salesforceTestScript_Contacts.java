package com.Salesforce.Automation.TestScripts;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Salesforce.Automation.Base.SalesforceAutomationBaseTest;
@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)
public class salesforceTestScript_Contacts extends SalesforceAutomationBaseTest {

	@BeforeClass
	@Parameters("browserName")
	public void contactBeforeClass(String browser)	throws InterruptedException
	{
		launch(browser);
		Thread.sleep(3000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected,"Contacts tab- Edge Browser SF login page not found");
		enterUsernamePassword();
		login();
	}
	
	@AfterClass
	public void contactAfterClass() throws InterruptedException
	{
		Thread.sleep(2000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		report.logTestInfo("Logged out of Salesforce account");
		Thread.sleep(2000);
		close();
		report.logTestInfo("Browser window closed");
	}
	@Test (priority = 1)		//TEST CASE 25
	public static void createNewContactTest25() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		closeLexDialog();
		Thread.sleep(2000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("contactsHomePageTitle");
		boolean match=actualPageTitle.equalsIgnoreCase(valueExpected);
		assertTrue(match);
		Thread.sleep(2000);
		locate("xpath","//input[@name='new']"); 	// New Button
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='name_lastcon2']"); 	// Last name field
		waitExplicit();
		String lname = "george";
		enter(lname);
		locate("xpath","//input[@id='con4']"); 	// Account name field
		waitExplicit();
		enter("Abby Corp");
		locate("xpath","//div[contains(@class,'pbHeader')]//input[1]"); 	// Save Button
		clickOn();
		Thread.sleep(2000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[1]/div[2]/h2[1]");
		waitExplicit();
		String actualLName = read();
		assertEquals(actualLName,lname);
	}
	
	@Test 	(priority = 2)	//TEST CASE 26
	public static void createNewViewTest26() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		closeLexDialog();
		Thread.sleep(2000);
		locate("xpath","//a[contains(text(),'Create New View')]"); 	// Create new View link
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='fname']"); 	// View name field
		waitExplicit();
		String viewName = "ExView"+getDate();
		enter(viewName);
		locate("xpath","//input[@id='devname']"); 	// View Unique name field
		waitExplicit();
		enter(viewName+"Unique");
		locate("xpath","//div[@class='pbHeader']//input[@name='save']"); 	// Save Button
		clickOn();
		Thread.sleep(5000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/form[1]/div[1]/div[1]/select[1]");
		waitExplicit();
		String actualViewName =  chosenOption();
		boolean match = actualViewName.equals(viewName);
		assertTrue(match);
	}
	
	@Test 	(priority = 3)	//TEST CASE 27
	public static void checkRecentlyCreatedContactTest27() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		locate("xpath","//select[@id='hotlist_mode']"); 	// Drop down from Recent contacts
		waitExplicit();
		clickOn();
		setDropDown(0);
		clickOn();
		String option = expectedValue("recentlyCreatedTab");
		String actualOption = chosenOption();
		boolean match = actualOption.equalsIgnoreCase(option);
		assertTrue(match);
	}
	
	@Test (priority = 4)		//TEST CASE 28
	public static void checkMyContactsViewTest28() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		locate("xpath","//select[@id='fcf']"); 	// Drop down for "view My Contacts"
		waitExplicit();
		String actualOption = chosenOption();
		setDropDown(3);  	//Selecting Choice as "My Contacts"
		String option = "My Contacts";
		boolean match = actualOption.equalsIgnoreCase(option);
		assertTrue(match);
	}
	
	@Test 	(priority = 5)	//TEST CASE 29
	public static void viewAContactTest29() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		Thread.sleep(4000);
		//Name in recent contacts  
		locate("xpath","//a[contains(text(),'Gonzalez, Rose')]");
		waitExplicit();
		String lNameFname = read();
		String[] lnameAndFname = lNameFname.split(",");
		String lname = lnameAndFname[0];
		clickOn();
		Thread.sleep(2000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[1]/div[2]/h2[1]");
		waitExplicit();
		lNameFname = read();
		lnameAndFname = lNameFname.split(" ");
		String actualLname = lnameAndFname[2];
		boolean match = actualLname.equalsIgnoreCase(lname);
		assertTrue(match);
	}
	
	@Test (priority = 6)		//TEST CASE 30
	public static void errorMessageInCreateNewViewTest30() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		locate("xpath","//a[contains(text(),'Create New View')]"); 	// Create new View link
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='devname']"); 	// View Unique Name Field
		waitExplicit();
		enter("RevUniqView");
		locate("xpath","//div[@class='pbHeader']//input[@name='save']"); 	// Save Button
		clickOn();
	}
	
	@Test (priority = 7)		//TEST CASE 31
	public static void cancelreateNewViewTest31() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		Thread.sleep(5000);
		//closeLexDialog();
		Thread.sleep(1000);
		locate("xpath","//a[contains(text(),'Create New View')]"); 	// Create new View link
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='fname']"); 	// View name field
		waitExplicit();
		enter("ABCD");
		locate("xpath","//input[@id='devname']"); 	// View Unique name field
		waitExplicit();
		enter("EFGH");
		locate("xpath","//div[@class='pbHeader']//input[@name='cancel']"); 	// Cancel Button
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//select[@id='fcf']");
		waitExplicit();
		int n = findIndexInDropDown("ABCD");
		assertEquals(n,-1);
	}
	@Test 	(priority = 8)	//TEST CASE 32
	public static void saveAndNewContactPageTest32() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Contacts')]"); //Contacts Tab
		waitExplicit();
		clickOn();
		Thread.sleep(5000);
		//closeLexDialog();
		Thread.sleep(1000);
		locate("xpath","//input[@name='new']"); 	// New Button
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='name_lastcon2']"); 	// Last name field
		waitExplicit();
		enter("Indian");
		locate("xpath","//input[@id='con4']"); 	// Account name field
		waitExplicit();
		enter("Global Media");
		locate("xpath","//td[@id='topButtonRow']//input[@name='save_new']"); 	// Save And New Button
		clickOn();
		Thread.sleep(4000);
		locate("xpath","//h2[contains(text(),'New Contact')]");
		waitExplicit();
		String lName = "Indian";
		String actualLName = read();
		assertEquals(actualLName,lName);
		}
}
