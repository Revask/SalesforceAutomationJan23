package com.Salesforce.Automation.TestScripts;

import com.Salesforce.Automation.Base.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;


@Listeners(com.Salesforce.Automation.Utility.SalesforceListeners.class)
public class SalesforceTestScript_Login extends SalesforceAutomationBaseTest
{
	@BeforeClass
	@Parameters("browserName")
	public void salesforceLoginTestBeforeclass(String browser) throws InterruptedException
	{
		launch(browser);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("loginPageTitle");
		assertEquals(actualPageTitle, valueExpected,"Login Page Tests Step1 : Error in launching browser");
		
	}
	
	@AfterClass
	public void salesforceLoginTestAfterClass() throws InterruptedException
	{
		close();
	}
	
	
	@Test(priority = 1) //TEST CASE 1
	
	public static void loginErrorMessageTest1() throws InterruptedException
	{
		//report.logTestInfo("Browser Instance started");
		String uname = getLogin();	//Reading the User name
		locate("cssselector","#username");
		waitExplicit();
		enter(uname);
		report.logTestInfo("Username entered " + uname);
		clickOn();
		Thread.sleep(1000);
		String str = content();
		try
		{
			assertFalse(str.isEmpty());
			report.logTestInfo("username entered");
			
		}catch(AssertionError e)
		{
			report.logTestInfo("TC1Step3 : UserName is not displayed");
		}
		waitFluent("xpath","//input[@id='password']");
		emptyContent();
		try
		{
			assertTrue(checkIfEmpty());
			report.logTestInfo("password entered");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC1Step3 : Password Field is not empty");
		}
		//Empty Password Field
		login();
		Thread.sleep(3000);
		locate("xpath","//div[@id='error']");
		waitExplicit();
		String actualErrMsg = read();
		Thread.sleep(5000);
		String valueExpected = expectedValue("pwdEmptyErrMsg");
		try
		{
			assertEquals(actualErrMsg,valueExpected);
			report.logTestInfo("Password field is empty error message present");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC1Step5 : login Error message not present");
		}
	}
	
	@Test(priority = 3)	//TEST CASE 2
	public static void loginToSalesforceTest2() throws InterruptedException
	{
		
		enterUsernamePassword();
		Thread.sleep(2000);
		login();
		Thread.sleep(6000);
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		
	}
	
	@Test(priority = 4)	//TEST CASE 3
	public static void checkRememberMeTest3() throws Exception
	{
		
		Thread.sleep(3000);
		enterUsernamePassword();
		locate("id","rememberUn"); //Remember username checkbox
		waitExplicit();
		selectCheckBox();
		Thread.sleep(2000);
		login();
		Thread.sleep(6000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("SFHomePageTitle");
		try
		{
			assertEquals(actualPageTitle,valueExpected);
			report.logTestInfo("Login Successful..");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC3Step2 : SF HomePage not found");
		}
		clickUserMenu();
		Thread.sleep(2000);
		logout();
		Thread.sleep(4000);
		actualPageTitle = getPageTitle();
		valueExpected = expectedValue("loginPageTitle");
		Boolean step3Conditions = (actualPageTitle.equalsIgnoreCase(valueExpected));
		try
		{
			assertTrue(step3Conditions);
			report.logTestInfo("Checking Login Page is displayed");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC3Step3 : SF Login Page Not Displayed");
		}
		Thread.sleep(2000);
		locate("xpath","//span[@id='idcard-identity']");
		waitExplicit();
		step3Conditions = step3Conditions & !(checkIfEmpty());
		try
		{
			assertTrue(step3Conditions);
			report.logTestInfo(" Username field is populated");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC3Step3 : Username is empty");
		}
		Thread.sleep(2000);
		locate("id","rememberUn");
		waitExplicit();
		Thread.sleep(3000);
		try
		{
			step3Conditions = step3Conditions & isCheckBoxSelected();
			assertTrue(step3Conditions);
			report.logTestInfo("Checkbox remember me  is selected");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC3Step3 : Remember Me Checkbox not selected");
		}
	}
	
	@Test(priority = 5)	//TEST CASE 4A
	
	public static void forgotPasswordTest4A() throws InterruptedException
	{
		
		Thread.sleep(3000);
		locate("linktext","Forgot Your Password?");//Forgot your password link
		waitExplicit();
		clickOn();
		Thread.sleep(3000);
		String actualPageTitle = getPageTitle();
		String valueExpected = expectedValue("forgotPwdPageTitle");
		report.logTestInfo("Page now loaded " + actualPageTitle);
		try
		{
			assertEquals(actualPageTitle,valueExpected);
			report.logTestInfo("Forgot Password Page loaded");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC4AStep2 : Forgot Password page not found");
		}
		locate("xpath","//input[@id='un']");  //Username tab 
		waitExplicit();
		enter("srebu@tekarch.com");
		Thread.sleep(1000);
		locate("xpath","//input[@id='continue']"); //Continue to forgot password page
		waitExplicit();
		clickOn();
		Thread.sleep(2000);
		locate("xpath","//p[contains(text(),'We’ve sent you an email with a link to finish rese')]");
		waitExplicit();
		String actualForgotPwdResetMsg = read();
		valueExpected = expectedValue("forgotPwdResetMesg");
		try
		{
			assertTrue(actualForgotPwdResetMsg.equalsIgnoreCase(valueExpected));
			report.logTestInfo("Password reset page loaded ");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC4AStep3 : Password reset message not found");
		}
	}
	
	@Test(priority = 2)	//TEST CASE 4B
	public static void forgotPasswordTest4B() throws InterruptedException
	{
		setLogin("123");
		Thread.sleep(4000);
		String actualUserName = element.getAttribute("value");
		try
		{
			assertEquals(actualUserName,"123");
			report.logTestInfo("Username is entered as 123.");
		}catch(AssertionError e)
		{
			report.logTestInfo("TC4BStep2 : Username is not 123");
		}
		setPassword("22131");
		String actualPassword = element.getAttribute("value");
		Thread.sleep(1000);
		login();
		Thread.sleep(3000);
		locate("xpath","//div[@id='error']");
		waitExplicit();
		String actualErrorMsg = read();
		String valueExpected = expectedValue("invalidLoginIdErrorMsg");
		assertTrue(actualErrorMsg.equalsIgnoreCase(valueExpected));
		
	}
}
