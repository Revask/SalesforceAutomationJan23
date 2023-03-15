package com.Salesforce.Automation.TestScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Salesforce.Automation.Base.SalesforceAutomationBaseTest;

@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)

public class SalesforceTestScript_Account extends SalesforceAutomationBaseTest{
	
	@BeforeClass
	@Parameters("browserName")
	public void accountsBeforeClass(String browser) throws InterruptedException
	{
		launch(browser);
		Thread.sleep(2000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected,"Accounts tab- Firefox Browser SF login page not found");
		enterUsernamePassword();
		String userName  = getLogin();
		login();
		Thread.sleep(8000);
		 actualPageTitle = getPageTitle();
		 valueExpected = expectedValue("SFHomePageTitle");
		boolean match1 = actualPageTitle.equals(valueExpected);
		assertTrue(match1);
		locate("xpath","//span[@id='userNavLabel']");
		waitExplicit();
		setImplicitWait();
	}

	@BeforeMethod
	public void accountsBeforeMethod()throws InterruptedException
	{
		Thread.sleep(1000);
		//Accounts tab
		locate("xpath","//body/div[@id='contentWrapper']/div[@id='AppBodyHeader']/div[1]/div[1]/nav[1]/ul[1]/li[2]/a[1]");
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("accountsPageTitle");
		boolean match = actualPageTitle.equals(valueExpected);
		assertTrue(match);
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void accountsAfterClass()	throws InterruptedException
	{
		Thread.sleep(2000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		close();
	}
	
	@Test (priority = 1)	//TEST CASE 10
		public static void createAccountTest10() throws InterruptedException // TEST CASE 10
	{
		closeLexDialog();
		locate("xpath","//tbody/tr[1]/td[2]/input[1]");//New tab
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='acc2']");//Account name tab
		waitExplicit();
		String accountName = "Parker corp";
		enter(accountName);
		//save Button
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]"); 
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[1]/div[2]/h2[1]");
		waitExplicit();
		String actualAccountName = read();
		assertEquals(actualAccountName,accountName);
		}
	@Test	 (priority = 2)	//TEST CASE 11
	
	public static void createNewViewTest11() throws InterruptedException 
	{
		closeLexDialog();
		locate("xpath","//a[contains(text(),'Create New View')]");//Create new view tab
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='fname']"); //View Name
		waitExplicit();
		String viewName = "MyView"+getDate();
		enter(viewName);
		locate("xpath","//input[@id='devname']"); //Unique View Name
		waitExplicit();
		enter("MYuniqview"+getDate());
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]");//Save Button
		waitExplicit();
		clickOn();
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/form[1]/div[1]/div[1]/select[1]");
		waitExplicit();
		int n = findIndexInDropDown(viewName);
		assertTrue(n>=0);
	}

	@Test	 (priority = 3)	 //TEST CASE 12
	
	public static void editViewTest12() throws InterruptedException
	{
		closeLexDialog();
		locate("xpath","//select[@id='fcf']");// View Drop down
		waitExplicit();
		clickOn();
		//int index1 = findIndexInDropDown("MyNewView");
		setDropDown("MyNewView119");
		locate("linktext","Edit");//edit View Button
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='fname']");//Set View Name
		waitExplicit();
		emptyContent();
		String newViewName = "MyNewView125";
		enter(newViewName);
		locate("xpath","//select[@id='fcol1']");//Filter in Selected Accounts 
		waitExplicit();
		int index2 = findIndexInDropDown("Account Name");
		setDropDown(index2);
		locate("xpath","//select[@id='fop1']");//
		waitExplicit();
		int index3 = findIndexInDropDown("contains");
		setDropDown(index3);
		locate("xpath","//input[@id='fval1']");		//value tab
		waitExplicit();
		enter("a");
		pressPageDown();
		locate("xpath","//select[@id='colselector_select_0']");	// Last activity
		waitExplicit();
		int index4 = findIndexInDropDown("Last Activity");
		setDropDown(index4);
		//Add button
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[2]/div[3]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/a[1]/img[1]");
		waitExplicit();
		clickOn();
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[2]/div[3]/div[1]");
		waitExplicit();
		clickOn();
		pressPageDown();
		locate("xpath","//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[3]/table[1]/tbody[1]/tr[1]/td[2]/input[1]");	// Save Button
		waitExplicit();
		Thread.sleep(1000);
		clickOn();
		Thread.sleep(3000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/form[1]/div[1]/div[1]/select[1]");
		waitExplicit();
		int ind = findIndexInDropDown(newViewName);
		assertNotEquals(ind,-1);
		try
		{
			Thread.sleep(2000);
			locate("xpath","//div[title='Last Activity']");
			waitExplicit();
			report.logTestInfo("LastActivity column added");
		}catch(NoSuchElementException e)
		{
			report.logTestInfo("TC12Step:3 LastActivity column not added");
		}
		List<WebElement> list = locate("xpath","//tbody/tr/td[4]",true);
		boolean condition = chkInTable(list,"a");
		assertTrue(condition);
	}
	
	@Test  (priority = 4)		//TEST CASE 13
	
	public static void mergeAccountsTest13() throws InterruptedException //TEST CASE 13
	{
		closeLexDialog();
		Thread.sleep(1000);
		locate("linktext","Merge Accounts");
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='srch']"); //enter account name to find Accounts tab
		waitExplicit();
		enter("ab");
		locate("xpath","//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[4]/input[2]"); //find Accounts tab
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='cid0']"); //select first account
		waitExplicit();
		selectCheckBox();
		locate("xpath","//input[@id='cid1']"); //select second account
		waitExplicit();
		selectCheckBox();
		Thread.sleep(2000);
		locate("xpath","//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[1]/div[1]/input[1]"); //Next tab
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		boolean present = false;
		try
		{
			locate("xpath","//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[4]");
			waitExplicit();
			present = true;
		}catch(NoSuchElementException e)
		{
			present = false;
		}
		assertTrue(present);
		Thread.sleep(2000);
		locate("xpath","//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[1]/div[1]/input[2]"); //Merge tab
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		boolean isAlertThere = false;
		try
		{
			acceptAlert();//Merge Confirmation Popup
			isAlertThere = true;
		}catch(NoAlertPresentException e)
		{
			isAlertThere = false;
		}
		assertTrue(isAlertThere);
		Thread.sleep(5000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("accountsPageTitle");
		assertTrue(actualPageTitle.equals(valueExpected));
		}
	
	@Test  (priority = 5)	//TEST CASE 14
	
	public static void createReportTest14() throws InterruptedException
	{
		closeLexDialog();
		locate("linktext","Accounts with last activity > 30 days"); //Report Generation link tab
		waitExplicit();
		clickOn();
		Thread.sleep(5000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("unsavedReportPageTitle");
		assertEquals(actualPageTitle,valueExpected);
		Thread.sleep(1000);
		locate("cssselector","#ext-gen148"); // Date Field drop down arrow button
		waitExplicit();
		clickOn();
		locate("xpath","//div[contains(text(),'Created Date')]");//choosing option as Created Date
		waitExplicit();
		clickOn();
		locate("cssselector","#ext-gen152");//From  date selection button
		waitExplicit();
		clickOn();
		locate("xpath","//table[@id='ext-comp-1112']");//set to today date on calendar
		waitExplicit();
		clickOn();
		locate("cssselector","#ext-gen154");//End date selection button
		waitExplicit();
		clickOn();
		locate("cssselector","#ext-comp-1114");//set to today date on calendar
		waitExplicit();
		clickOn();
		locate("cssselector","#ext-gen49");//Save Report Button
		waitExplicit();
		String parent = getCurrentWindowHandle();
		clickOn();
		String child = getChildWindowHandle(parent);
		 switchToWindow(child);
		 Thread.sleep(3000);
		 String reportName = "Report"+getDate();
		 waitFluent("xpath","//input[@id='saveReportDlg_reportNameField']"); //Report Name Field
		  enter(reportName);
		 waitFluent("xpath","//input[@id='saveReportDlg_DeveloperName']");  //Report Unique name field
		 enter("UniqRep"+getDate());
		 Thread.sleep(1000);
		 waitFluent("xpath","//table[@id='dlgSaveAndRun']"); // Save and run report button
		clickOn();
		Thread.sleep(5000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h1[1]"); //Report Name field in Report generated
		waitExplicit();
		String actualReportName = read();
		assertEquals(actualReportName,reportName);
	}
}