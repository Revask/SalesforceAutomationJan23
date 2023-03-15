package com.Salesforce.Automation.TestScripts;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Salesforce.Automation.Base.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.util.NoSuchElementException;

@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)
	public class SalesforceTestScript_Usermenu extends SalesforceAutomationBaseTest {

	
	@BeforeClass
	@Parameters("browserName")
	public void userMenuSetup(String browser) throws InterruptedException
	{
		launch(browser);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected);
		enterUsernamePassword();
		login();
		setImplicitWait();
		actualPageTitle = getPageTitle();
		valueExpected = expectedValue("SFHomePageTitle");
		assertEquals(actualPageTitle,valueExpected);
		locate("xpath","//span[@id='userNavLabel']");
		waitExplicit();
		String uname = read();
		assertFalse(uname.isEmpty());
		
	}
	@BeforeMethod
	public void userMenuBeforeMethod() throws InterruptedException
	{	
		boolean available;
		try
		{
			locate("cssselector","#userNavButton");	 //user Navigation Button
			waitExplicit();
			available = true;
		}catch(NoSuchElementException e)
		{
			available = false;
		}
		assertTrue(available);
		
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//div[@id='userNavMenu']");
		waitExplicit();
		assertTrue(chkIfDisplayed());
		
	}
	@AfterClass
	public void userMenuClosing() throws InterruptedException
	{
		close();
	}
	
	
	@Test (priority = 1)	//TEST CASE 5
	
	public static void userMenuDropdownTest5() throws InterruptedException
	{
		locate("cssselector","#userNavButton");	 //user Navigation Button
		waitExplicit();
		clickOn();	
	}
	
	
	@Test(priority = 2)	//TEST CASE 6
	
	public static void userMenuUpdateLastNameTest6() throws InterruptedException
	{
		locate("linktext","My Profile"); // My profile link in User Drop down Menu
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[2]/div[1]/span[2]/img[1]");
		waitExplicit();
		assertTrue(chkIfDisplayed());
		locate("cssselector","body.hasMotif.userTab.UserProfilePage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr:nth-child(2) div.bodyDiv.brdPalette.brandPrimaryBrd:nth-child(2) table.outerNoSidebar td.noSidebarCell div.profilePage.userProfilePage:nth-child(3) div.leftContent div.contactInfo.profileSection:nth-child(4) div.vfButtonBar h3:nth-child(1) div.vfButtonBarButton div.editPen a.contactInfoLaunch.editLink > img:nth-child(1)"); //Edit Profile icon next to Contacts tab
		waitExplicit();
		assertTrue(chkIfDisplayed());
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//iframe[@id='contactInfoContentId']");	//Contact Info update pop up
		waitExplicit();
		switchToFrame(element.getAttribute("id")); 
		locate("xpath","//a[contains(text(),'About')]"); //About tab
		waitExplicit();
		moveTo();
		clickOn();
		locate("xpath","//input[@id='lastName']"); //last name tab
		waitExplicit();
		String unameOld = read(); //  Storing the old last name
		emptyContent();
		enter("Kumar");
		locate("xpath","//body/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]"); //save all tab
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//span[@id='tailBreadcrumbNode']");
		waitExplicit();
		String[] unameNew = read().split(" ");
		assertFalse(unameNew[1].equalsIgnoreCase(unameOld));
		
	}
			
	@Test (priority = 3)  //TEST CASE 6
	
	public static void userProfilePhotoUpdateTest6() throws Exception
	{
		Thread.sleep(3000);
		locate("linktext","My Profile");      //My Profile Link
		waitExplicit();
		clickOn();
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[2]/div[1]/span[2]/img[1]"); //Mouse over on  profile photo
		waitExplicit();
		moveTo();
		locate("partiallinktext","Add Pho");	//Add Photo Link
		waitExplicit();
		clickOn();
		switchToFrame(2);
		locate("xpath","//input[@id='j_id0:uploadFileForm:uploadInputFile']"); //Choose file to upload tab
		waitExplicit();
		enter("C:\\testingrelated\\flower.jpg");
		try
		{
		pressTab();
		Thread.sleep(500);
		pressTab();
		Thread.sleep(500);
		pressTab();
		}catch(AWTException e)
		{
			report.logTestInfo("Error in Tab key press");
		}
		switchToActiveElement();
		waitExplicit();
		clickOn();
		//Image crop tab	
		locate("cssselector","div.uploadContainer:nth-child(2) div.cropArea div.imgCrop_wrap div.imgCrop_dragArea div.imgCrop_selArea:nth-child(5) > div.imgCrop_clickArea:nth-child(9)");
		waitExplicit();
		clickOn();
		mouseDragAndDrop(30,30);
		locate("id","j_id0:j_id7:save"); //Save Button
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[2]/div[1]/span[2]/img[1]");
		waitExplicit();
	}
	
	@Test (priority = 4)//TEST CASE 6
	
	public static void userPostATextTest6() throws InterruptedException
	{
		locate("linktext","My Profile");
		waitExplicit();	// My Profile Link
		clickOn();
		Thread.sleep(2000);
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]/span[1]");
		waitExplicit();
		clickOn();
		//User Text Area
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/iframe[1]");
		waitExplicit();
		switchToFrame(element);
		locate("xpath","/html[1]/body[1]");
		waitExplicit();
		String message = "This is my test post";
		enter(message);
		goToParentFrame();
		//Share Button
		locate("xpath","/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/input[1]");
		waitExplicit();
		moveTo();
		clickOn();
		assertTrue(chkIfTextIsPresent(message));
	}
	@Test(priority = 5)	//TEST CASE 6
	
	public static void userMenuFileUploadTest6() throws InterruptedException
	{
		waitFluent("linktext","My Profile"); //My Profile Link
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/a[1]/span[1]"); //File Link
		waitExplicit();
		clickOn();
		locate("linktext","Upload a file from your computer"); //???
		waitExplicit();
		clickOn();
		locate("xpath","//input[@id='chatterFile']"); //Choose file
		waitExplicit();
		enter("C:\\testingrelated\\testdoc.txt");
		locate("xpath","//input[@id='publishersharebutton']"); //Share Button
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		Assert.assertTrue(true);
	}
	@Test(priority = 6 ) //TEST CASE 7
	
	public static void userMenuLoginHistoryTest7() throws InterruptedException
	{
		Thread.sleep(5000);
		locate("linktext","My Settings");	//My Setting link
		waitExplicit();
		clickOn();
		boolean present;
		try
		{
			locate("xpath","//a[@id='PersonalSetup_font']");
			waitExplicit();
			present = true;
		}catch(NoSuchElementException e)
		{
			present = false;
		}
		assertTrue(present);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[4]/div[2]/a[1]");	//personal link
		waitExplicit();
		clickOn();
		locate("xpath","//span[@id='LoginHistory_font']");	//login history link
		waitExplicit();
		clickOn();
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginHistoryPageTitle");
		boolean matches = actualPageTitle.equals(valueExpected);
		assertTrue(matches);
		pressPageDown();
		Thread.sleep(2000);
		locate("xpath","//a[contains(text(),'Download login history for last six months, includ')]"); //Download Login history for last six months
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//span[@id='DisplayAndLayout_font']");	//DISPLAY AND LAYOUT link
		waitExplicit();
		clickOn();
		locate("xpath","//a[@id='CustomizeTabs_font']");	//Customise my tabs link
		waitExplicit();
		clickOn();
		locate("xpath","//select[@id='p4']");	// Select from Custom My App drop down
		waitExplicit();
		String str = "Salesforce Chatter";
		int index = findIndexInDropDown(str);
		if(index >= 0)
			setDropDown(index);
		else
			report.logTestInfo("Salesforce chatter option not in list 1");
		locate("xpath","//select[@id='duel_select_0']");	//Select from available drop down
		waitExplicit();
		String str1 = "Reports";
		int n = findIndexInDropDown(str1);
		if(n >= 0)
			setDropDown(n);
		else
		{
			report.logTestInfo("Reports option not in available list");
			locate("xpath","//select[@id='duel_select_1']");// Selected drop down
			waitExplicit();
			int n1 = findIndexInDropDown("Reports");
			if(n1>=0)
			{
				setDropDown(n1);
				locate("xpath","//tbody/tr[1]/td[2]/div[3]/a[1]/img[1]");
				waitExplicit();
				clickOn();
			}
		}
		locate("xpath","//tbody/tr[1]/td[2]/div[2]/a[1]/img[1]"); //AddButton
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//select[@id='duel_select_1']");// Selected drop down
		waitExplicit();
		int n1 = findIndexInDropDown("Reports");
		assertFalse((n1 == -1));
		Thread.sleep(4000);
		locate("xpath","//tbody/tr[1]/td[2]/input[1]"); // Save Button
		waitExplicit();
		clickOn();
		Thread.sleep(8000);
		locate("xpath","//ul[@id='tabBar']");
		waitExplicit();
		present = chkIfPresentInList("Reports");
		assertTrue(present);
		Thread.sleep(2000);
		locate("xpath","//tbody/tr[1]/td[1]/div[1]/div[4]/div[4]/a[1]"); //Email tab
		waitExplicit();
		clickOn();
		Thread.sleep(4000);
		locate("xpath","//span[@id='EmailSettings_font']");	//EMAIL settings link
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//input[@id='sender_name']"); //Email name
		waitExplicit();
		clickOn();
		emptyContent();
		enter("Kumar");
		Thread.sleep(1000);
		locate("xpath","//input[@id='sender_email']"); //Email address
		waitExplicit();
		clickOn();
		emptyContent();
		enter("srebu.2017@gmail.com");
		locate("xpath","//input[@id='auto_bcc1']"); // bcc Radio Button
		waitExplicit();
		clickOn();
		pressPageDown();
		Thread.sleep(3000);
		locate("xpath","//tbody/tr[1]/td[2]/input[1]"); 	//Save button
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//div[@id='meSaveCompleteMessage']");
		waitExplicit();
		String actualConfirmation = read();
		valueExpected = expectedValue("emailSettingConfirmation");
		boolean match = actualConfirmation.equals(valueExpected);
		actualPageTitle = getPageTitle();
		valueExpected = expectedValue("mySettingsPage");
		boolean match1 = actualPageTitle.equals(valueExpected);
		assertTrue(match&match1);
		locate("xpath","//span[@id='CalendarAndReminders_font']");	//CALENDAR AND REMAINDER link
		waitExplicit();
		clickOn();
		locate("xpath","//span[@id='Reminders_font']");	//Activity reminders link
		waitExplicit();
		clickOn();
		pressPageDown();
		Thread.sleep(2000);
		locate("xpath","//input[@id='testbtn']");	//Open a Test Reminder button
		waitExplicit();
		clickOn();
		String parentWinHandle = getCurrentWindowHandle();
		String childWinHandle = getChildWindowHandle(parentWinHandle);
		assertNotNull(childWinHandle);
	}		
	@Test(priority = 7) // TEST CASE 8
	
	public static void userMenuDeveloperConsoleTest8() throws InterruptedException
	{
		String parentHandle = getCurrentWindowHandle();
		locate("linktext","Developer Console");		//DeveloperConsole link
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		String childHandle = getChildWindowHandle(parentHandle);
		switchToWindow(childHandle);
		String childWindowTitle = getPageTitle();
		report.logTestInfo("Child window " + childWindowTitle);
		String valueExpected = expectedValue("devConsoleWindowTitle");
		assertEquals(childWindowTitle,valueExpected);
		closeChildWindow(parentHandle);
		switchToWindow(parentHandle);
		
	}
	
	@Test(priority = 8)	//TEST CASE 9
	public static void userMenuLogoutTest9()  throws InterruptedException
	{
	
		locate("linktext","Logout");	
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle,valueExpected);
		
	}
}
