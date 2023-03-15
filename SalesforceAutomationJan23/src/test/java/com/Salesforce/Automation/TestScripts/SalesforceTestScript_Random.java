package com.Salesforce.Automation.TestScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.util.NoSuchElementException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Salesforce.Automation.Base.SalesforceAutomationBaseTest;

@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)
public class SalesforceTestScript_Random extends SalesforceAutomationBaseTest
{

	@BeforeClass
	@Parameters("browserName")
	public void randomScenBeforeClass(String browser)	throws InterruptedException
	{
		launch(browser);
		Thread.sleep(1000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected);
		enterUsernamePassword();
		login();
	}
	
	@AfterClass
	public void randomScenAfterClass()	throws InterruptedException
	{
		Thread.sleep(2000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		report.logTestInfo("Logged out og Salesforce account");
		Thread.sleep(3000);
		close();
		report.logTestInfo("Browser Window closed");
	}
	
	@Test (priority = 1)		  //Test Case 33
	public static void RandonTestFirtAndLastTest33() throws InterruptedException
	{
		
		locate("xpath","//a[contains(text(),'Home')]");//Home Tab
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		closeLexDialog();
		Thread.sleep(2000);
		boolean present;
		try
		{
			locate("linktext","Sid Kumar");
			waitExplicit();
			present = true;
		}catch(NoSuchElementException e)
		{
			present = false;
		}
		assertTrue(present);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/a[1]/span[1]");//Username as link
		waitExplicit();
		clickOn();
		String actualPageTitle = getPageTitle();
		String valueExpected =expectedValue("userFnLnPageTitle");
		boolean match = actualPageTitle.equalsIgnoreCase(valueExpected);
		assertTrue(match);
		locate("cssselector","#userNavButton");	 //user Navigation Button
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("linktext","My Profile"); // My profile link in User Drop down Menu
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		valueExpected = getPageTitle();
		match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
	}
	@Test  (priority = 2) //Test case 34
	public static void updateProfileTest34() throws InterruptedException
	{
		Thread.sleep(2000);
		locate("xpath","//a[contains(text(),'Home')]");//Home Button
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/a[1]/span[1]");//First,Last name link
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/div[1]/div[1]/a[1]/img[1]");//Edit icon
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		waitFluent("xpath","//iframe[@id='contactInfoContentId']");//Edit User Info Frame
		switchToFrame(element);
		waitFluent("xpath","//a[contains(text(),'About')]");//About tab
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//input[@id='firstName']");//first name tab
		waitExplicit();
		boolean focused = chkIfFocused();
		assertFalse(focused,"TC34Step:4 Focus is not in firt name field");
		locate("xpath","//input[@id='lastName']");
		waitExplicit();
		clickOn();
		emptyContent();
		enter("Abcd");
		locate("xpath","//body/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]");
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		boolean present;
		locate("xpath","//span[@id='tailBreadcrumbNode']");
		waitExplicit();
		String[] name = read().split(" ");
		assertEquals(name[1],"Abcd","TC34Step:5 Updated last name not found");
		locate("cssselector","#userNavButton");	 //user Navigation Button
		waitExplicit();
		name = read().split(" ");
		assertEquals(name[1],"Abcd","TC34Step:5 Updated last name not found in User Menu button");
		String pageTitle =  getPageTitle();
		name = pageTitle.split(" ");
		assertEquals(name[2],"Abcd");
	}
	@Test (priority = 3) //TEST CASE 35
	
	public static void tabCustomizationTest35() throws InterruptedException
	{
		locate("xpath","//li[@id='AllTab_Tab']");//+ tab
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//tbody/tr[1]/td[2]/input[1]");// customise my tabs
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//select[@id='duel_select_1']");//dropdown of selected tabs
		waitExplicit();
		setDropDown(2);
		String removedTab = read();
		Thread.sleep(1000);
		locate("xpath","//tbody/tr[1]/td[2]/div[3]/a[1]/img[1]");//remove button
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//tbody/tr[1]/td[2]/input[1]");//save button
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		Thread.sleep(3000);
		enterUsernamePassword();
		login();
		Thread.sleep(3000);
		locate("xpath","//ul[@id='tabBar']");
		waitExplicit();
		boolean present = chkIfPresentInList(removedTab);
		assertFalse(present);
	}
	
	@Test (priority = 4) //TestCase 36
	
	public static void eventBlockingInCalendarTest36() throws InterruptedException
	{
		locate("xpath","//a[contains(text(),'Home')]");//Home Button
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[2]/span[2]/a[1]"); //Date Link under username
		waitExplicit();
		String expectedDate = getDate();
		String actualDate = read();
		boolean match = actualDate.equals(expectedDate);
		assertTrue(match);
		clickOn();
		Thread.sleep(2000);
		pressPageDown();
		Thread.sleep(3000);
		locate("xpath","//a[contains(text(),'8:00 PM')]");//8 PM Link on Calendar
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("newCalendarEventPageTite");
		match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
		String parent = getCurrentWindowHandle();
		Thread.sleep(4000);
		//clicking on the subject selection tab icon
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[2]/div[4]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/a[1]/img[1]");
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		String child = getChildWindowHandle(parent);
		switchToWindow(child);// switching to pop up window
		locate("xpath","//html/body/div[2]/ul/li[5]"); //List Element 5 "Others" in child Window
		waitExplicit();
		String str = read();
		moveTo();
		clickOn();
		switchToWindow(parent); //switching back to parent window
		locate("xpath","//input[@id='evt5']");
		waitExplicit();
		enter(str);
		locate("xpath","//input[@id='EndDateTime_time']");		//End date time selection drop down
		waitExplicit();
		clickOn();
		locate("xpath","//div[@id='simpleTimePicker']");	//Endtime selection tab
		locate("xpath","//div[@id='timePickerItem_42']");	//Selecting Today tab from drop down calendar
		waitExplicit();
		clickOn();
		pressPageDown();
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[2]/input[1]"); //Save Button
		waitExplicit();
		clickOn();
	}
	
	@Test (priority = 5)	//TEST CASE 37
	
	public static void eventBlockingWithRecurrenceTest37() throws InterruptedException   // 
	{
		locate("xpath","//a[contains(text(),'Home')]");//Home Button
		waitExplicit();
		clickOn();
		Thread.sleep(5000);
		Thread.sleep(2000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[2]/span[2]/a[1]"); //Date Link under username
		waitExplicit();
		clickOn();
		pressPageDown();
		Thread.sleep(1000);
		locate("xpath","//a[contains(text(),'4:00 PM')]");//4 PM Link on Calendar
		waitExplicit();
		clickOn();
		String parent = getCurrentWindowHandle();
		Thread.sleep(2000);
		//clicking on the subject selection tab icon
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[2]/div[4]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/a[1]/img[1]");
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		String child = getChildWindowHandle(parent);
		switchToWindow(child);// switching to pop up window
		Thread.sleep(1000);
		locate("xpath","//html/body/div[2]/ul/li[5]"); //list Element 5 "Other" in child window
		waitExplicit();
		String str = read();
		moveTo();
		clickOn();
		switchToWindow(parent);                               //switching back to parent window
		Thread.sleep(1000);
		locate("xpath","//input[@id='evt5']");
		waitExplicit();
		enter(str);
		Thread.sleep(1500);
		locate("xpath","//input[@id='EndDateTime_time']");		//End Date tab
		waitExplicit();
		clickOn();
		Thread.sleep(1000);
		locate("xpath","//div[@id='simpleTimePicker']");	//End time drop down
		Thread.sleep(1000);
		locate("xpath","//div[@id='simpleTimePicker']");//selecting 7:00 PM in list
		waitExplicit();
		clickOn();
		Thread.sleep(1000);
		pressPageDown();
		Thread.sleep(2000);
		locate("xpath","//input[@id='IsRecurrence']");//Recurrence checkbox
		waitExplicit();
		selectCheckBox();
		Thread.sleep(2000);
		locate("xpath","//input[@id='rectypeftw']");//Radiobutton Weekly
		waitExplicit();
		clickOn();
		Thread.sleep(1000);
		locate("xpath","//input[@id='RecurrenceEndDateOnly']");//Recurrence End date field
		waitExplicit();
		Thread.sleep(1000);
		String dt = dtPicker(14);
		enter(dt);
		Thread.sleep(2000);
		try
		{
		pressEnter();
		}catch(AWTException e)
		{
			System.out.println("error enter key press");
		}
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[2]/input[1]"); //save button
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		locate("xpath","//tbody/tr[1]/td[2]/div[1]/div[1]/div[2]/span[2]/a[3]/img[1]"); // Month view Icon
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
	}
}
