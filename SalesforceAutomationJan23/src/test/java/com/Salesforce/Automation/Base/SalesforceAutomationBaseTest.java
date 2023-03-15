package com.Salesforce.Automation.Base;

import java.awt.AWTException;
import org.openqa.selenium.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.Salesforce.Automation.Utility.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SalesforceAutomationBaseTest {
	
	protected static WebDriver driver;
	protected static WebElement element = null;
	public static GenerateReports report = GenerateReports.getInstance(); //same report object created in listener will be returned
	Logger logfourj = LogManager.getLogger(SalesforceAutomationBaseTest.class.getName());
	public static void getDriver(String choice)
	{
		choice = choice.toLowerCase();
		switch(choice)
		{
			case "chrome" :
			{
				System.out.println("Chrome");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			case  "edge" :
			{
				System.out.println("edge");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			}
			case "firefox" :
			{
				System.out.println("firefox");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
}
	public static String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public static String getLogin()
	{
		String uname = SalesforceCommonUtilities.getLoginId();
		return uname;
	}
	public static String setLogin()
	{
		String uname = SalesforceCommonUtilities.getLoginId();
		return uname;
	}
	public static String setPwd()
	{
		String pwd = SalesforceCommonUtilities.getLoginpwd();
		return pwd;
	}
	
	public static void clickLogin()
	{
		locate("xpath","//input[@id='Login']");
			waitExplicit();
			clickOn();
	}
	
		public static void setLogin(String uname)
		{
			locate("xpath","//input[@id='username']");
			waitExplicit();
			enter(uname);
		}
		public static void setPassword(String pwd)
		{
			locate("xpath","//input[@id='password']");
			waitExplicit();
			enter(pwd);
		}
		public static void enterUsernamePassword()
		{
		String uname = setLogin();
		locate("xpath","//input[@id='username']");
		waitExplicit();
		enter(uname);
		String pwd = setPwd();
		locate("xpath","//input[@id='password']");
		waitExplicit();
		enter(pwd);
		}
		
		public static void login()
		{
			clickLogin();
		}
		
		public static void clickUserMenu()
		{
			locate("xpath","//span[@id='userNavLabel']");
			waitExplicit();
			clickOn();
		}
		public static void logout()
		{
			locate("xpath","//a[contains(text(),'Logout')]");
			waitExplicit();
			clickOn();
		}
	public static void launch(String browser) throws InterruptedException
	{
		
		getDriver(browser);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		gotoUrl();
		Thread.sleep(6000);
	}
	
	public static String read()
	{
		String s1 = element.getText();
		return s1;
	}
	
	//Visiting the URL specified
	public static void gotoUrl()
	{
		String url = SalesforceCommonUtilities.getLoginUrl();
		driver.get(url);
	}
	public static void locate(String by,String value)
	{
		by = by.toLowerCase();
		switch(by)
		{
		case "xpath":
		{
			element = driver.findElement(By.xpath(value));
			break;
		}
		case "cssselector":
		{
			element = driver.findElement(By.cssSelector(value));
			break;
		}
		case "classname":
		{
			element = driver.findElement(By.className(value));
			break;
		}
		case "linktext":
		{
			element = driver.findElement(By.linkText(value));
			break;
		}
		case "partiallinktext":
		{
			element = driver.findElement(By.partialLinkText(value));
			break;
		}
		case "tagname":
		{

			element = driver.findElement(By.tagName(value));
			break;
		}
		case "id":
		{
			element = driver.findElement(By.id(value));
			break;
		}
		case "name":
		{
			element = driver.findElement(By.name(value));
			break;
		}
		
		}
	}
	
	public static List<WebElement> locate(String by,String value,boolean chk)
	{
		List<WebElement> list = null;
		by = by.toLowerCase();
		switch(by)
		{
		case "xpath":
		{
			list = driver.findElements(By.xpath(value));
			break;
		}
		case "cssselector":
		{
			list = driver.findElements(By.cssSelector(value));
			break;
		}
		case "classname":
		{
			list = driver.findElements(By.className(value));
			break;
		}
		case "linktext":
		{
			list= driver.findElements(By.linkText(value));
			break;
		}
		case "partiallinktext":
		{
			list = driver.findElements(By.partialLinkText(value));
			break;
		}
		case "tagname":
		{

			list = driver.findElements(By.tagName(value));
			break;
		}
		case "id":
		{
			list = driver.findElements(By.id(value));
			break;
		}
		case "name":
		{
			list = driver.findElements(By.name(value));
			break;
		}
		
		}
		return list;
	}
	
	public static void close()
	{
		driver.quit();
	}
	// Input values to a web Element
	public static boolean chkIfDisplayed()
	{
		return (element.isDisplayed());
			
	}
	public static boolean chkIfFocused()
	{
		report.logTestInfo("Checking if element is in focus");
		return element.equals(driver.switchTo().activeElement());
	}
	public static void enter(String s)	
	{
		if(element.isEnabled())
		element.sendKeys(s);
		else
			report.logTestInfo("text not  entered" + s);
	}
	//clear Contents of the webelement
	public static void emptyContent()
	{
		element.clear();
		report.logTestInfo(" Element cleared");
	}
	public static boolean checkIfEmpty()
	{
		String str = element.getText();
		return str.isEmpty();
	}
	public static String content()
	{
		String str = element.getAttribute("value");
		return str;
	}
	
	
	//implicit wait
	public static void setImplicitWait()
	{
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	//Waiting for the web elements to load
	public static void waitExplicit()
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void checkIfFrameAvailableAndSwitch(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}
	public static void waitFluent(String by,String name)
	{
		final String s1 = by;
		final String s2 = name;
		FluentWait<WebDriver> fwait = new FluentWait(driver).withTimeout(Duration.ofSeconds(60))
				                                 .pollingEvery(Duration.ofSeconds(5)) 
				                                 .ignoring(NoSuchElementException.class);
		Function<WebDriver,Boolean> fn = new Function<WebDriver,Boolean>()
		{
			public Boolean apply(WebDriver driver)
			{
				 locate(s1,s2);
				 if(element.isEnabled())
				 {
					 report.logTestInfo("Element is enabled after fluent wait");
					 return true;

				 }
				return null;
			}
		};
		fwait.until(fn);
	}
	//Window handling
	public static void maximizeWindow()
	{
		driver.manage().window().maximize();
	}
	public static String getCurrentWindowHandle()
	{
		String str = driver.getWindowHandle();
		return str;
	}
	public static String getChildWindowHandle(String parent)
	{
		Set<String> handles = driver.getWindowHandles();
		String child = "";
		for(String s : handles )
		{
			if (!(parent.equals(s)))
			{
				report.logTestInfo("child window handle found :" + s);
				child = s;
				break;
			}
		}
		return child;
	}
	public static void switchToWindow(String s)
	{
		driver.switchTo().window(s);
		report.logTestInfo("Switched to new window" + s);
	}
	public static void closeChildWindow(String handle)
	{
		Set<String> handles = driver.getWindowHandles();
		for(String s : handles )
		{
			if (!(handle.equals(s)))
			{
				driver.switchTo().window(s);
				driver.close();
				report.logTestInfo("child window closed");
			}
				
		}
	}
	
	//Selecting from a dropdown menu
	public static int findIndexInDropDown(String str1)
	{
		Select s = new Select(element);
		List<WebElement> list= s.getOptions();
		int n = list.size();
		int index = -1;
		report.logTestInfo("given string " + str1);
		for(int i=0;i<n;i++)
		{
			String str2 = list.get(i).getText();
			report.logTestInfo("test string " + str2);
			if(str1.equalsIgnoreCase(str2))
			{
				report.logTestInfo(" option from dropdown "+ str2 + " found at index " + i);
				index = i;
				break;
			}
		}
		return index;
			
	}
	public static void setDropDown(int i)
	{
		if(element.isDisplayed())
		{	
			Select list = new Select(element);
			list.selectByIndex(i);
			report.logTestInfo("Dropdown choice "+i+" selected");
		}
		else
			report.logTestInfo("Element not displayed in drop down");
	}
	public static void setDropDown(String s)
	{
		Select list = new Select(element);
		list.selectByVisibleText(s);
	}
	
	public static String chosenOption()
	{
		Select list = new Select(element);
		return list.getFirstSelectedOption().getText();
	}
	
	public static List<String> listOptions()
	{
		Select sel = new Select(element);
		List<WebElement> list = sel.getOptions();
		List<String> strList = new ArrayList<String> ();
		if(list.size() > 0)
		{
			for(WebElement l : list)
			{
				strList.add(l.getText());
			}
		}
		return strList;
 	}
	//Select CheckBox
	public static void selectCheckBox() 
	{
	 if(element.isSelected())	
		 report.logTestInfo("checkbox already selected");
		 else
		 {
			 clickOn();
			 report.logTestInfo("Checkbox selected now");
		 }
	}
	public static boolean chkIffLinkIsPresent()
	{
		boolean present = false;
		report.logTestInfo("Finding all links in a page");
		List<WebElement> list = driver.findElements(By.tagName("a"));
		if (list.size() > 0)
		{
			for(WebElement e : list)
			{
				if(e.getText() == "Reva Kumar")
				{
					report.logTestInfo("link found" + e.getText());
					present = true;
					break;
				}
			}
		}
		return present;
	}
	public static boolean chkIfPresentInList(String str)
	{
		List<WebElement> tabElements = element.findElements(By.tagName("li"));
		boolean present = false;
		for(WebElement t : tabElements)
		{
			if(str.equalsIgnoreCase(t.getText()))
			{
				report.logTestInfo("Search item found in tab bar");
				present = true;
				break;
			}
		}
		return present;
	}
	
	//Un select CheckBox
		public static void unselectCheckBox() 
		{
			if(element.isSelected())	
			{
				clickOn();
				report.logTestInfo("checkbox unselected");
			}
		}
		
		public static boolean isCheckBoxSelected()
		{
			return element.isSelected();
		}
	//Switch control to Alert box
 
	public static void acceptAlert()
	{
		driver.switchTo().alert().accept();
		report.logTestInfo("Ok - accpeted in alert");
	}
	
	public static void switchToAlertBox()
	{
	Alert alert = driver.switchTo().alert();
	report.logTestInfo("switched control to alert");
	}
	
	public static void closeLexDialog()
	{
		locate("id","tryLexDialogX");
		waitExplicit();
		clickOn();
		report.logTestInfo("LexDialogbox closed");
	}
	public static void switchToFrame(String frameId)
	{
		driver.switchTo().frame(frameId);
		report.logTestInfo("switched control to frame");
	}
	public static void switchToFrame(int index)
	{
		driver.switchTo().frame(index);
		report.logTestInfo("switched control to frame");
	}
	public static void switchToFrame(WebElement element)
	{
		driver.switchTo().frame(element);
		report.logTestInfo("switched control to frame");
	}
	public static void switchToActiveElement()
	{
		element = driver.switchTo().activeElement();
		report.logTestInfo("switched to last active element : ");
	}
	public static void goToParentFrame()
	{
		driver.switchTo().parentFrame();
		report.logTestInfo("Control back to Parent Frame");
	}
	
	public static void moveTo()
	{
		Actions act = new Actions(driver);
		if(element.isEnabled())
		{
    		act.moveToElement(element).build().perform();
    		
    		report.logTestInfo(" Element enabled nd moved to");
		}
		else
			report.logTestInfo("Element not moved to");
	}
	//Click on a web element
		public static void clickOn()
		{
			Actions act = new Actions(driver);
			if(element.isEnabled())
			{
				act.click(element).build().perform();
			}
		}
		public static int[][] findElementLocation()
		{
			Point point = element.getLocation();
			int a[][] = new int[1][2];
			a[0][0] = point.getX();
			a[0][1]= point.getY();
			return a;
		}
		
		public static void mouseDragAndDrop(int x,int y)
		{
			Actions act = new Actions(driver);
			if(element.isEnabled())
			{
				report.logTestInfo("Element "+element.toString()+ " is enabled for mouse drag and drop");
				int a[][] = new int[1][2];
				a = findElementLocation();
				act.dragAndDropBy(element, a[0][0]+x, a[0][1]+y).build().perform();
			}
			
		}
	
		//ROBOT CLASS
		
		public static void keyPressTab() throws AWTException
		{
			try
			{
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_TAB);
				report.logTestInfo("Tab key pressed");
			}catch(AWTException e)
			{
				report.logTestInfo("robot class error");
			}
		}
		public static void pressTab()  throws AWTException
		{
			try
			{
				keyPressTab();
			}catch(AWTException e)
			{
				report.logTestInfo("error pagedown key press");
				
				
			}
		}
		public static void keyPressEnter() throws AWTException
		{
			try
			{
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_TAB);
				report.logTestInfo("Enter key pressed");
			}catch(AWTException e)
			{
				report.logTestInfo("robot class error");
			}
		}
		public static void pressEnter() throws AWTException
		{
			try
			{
				keyPressEnter();
			}catch(AWTException e)
			{
				report.logTestInfo("error Enter key press");
				
				
			}
		}
		public static void keyPageDown() throws AWTException
		{
			try
			{
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_PAGE_DOWN);
				r.keyRelease(KeyEvent.VK_PAGE_DOWN);
				report.logTestInfo("Page down key pressed");
			}catch(AWTException e)
			{
				report.logTestInfo("robot class error");
			}
		}
		public static void pressPageDown() 
		{
			try
			{
				keyPageDown();
			}catch(AWTException e)
			{
				report.logTestInfo("error pagedown key press");
				
				
			}
		}
		public static String uniqValue()
		{
			Random r = new Random(10);
			return String.valueOf(r.nextInt(10));
		}
		
		public static String getDate()
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddhhmm");
			LocalDateTime now = LocalDateTime.now(); 
			String s = now.format(dtf);
			return s;
		}
		
		public static String dtPicker(int n)
		{
			Date dt = new Date();
			Calendar c1 = Calendar.getInstance();
			c1.setTime(dt);
			c1.add(Calendar.DAY_OF_MONTH, n);
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/YYYY");
			String date = f.format(c1.getTime());
			return date;
		}
		public static boolean chkInTable(List<WebElement> l1,String str)
		{
			String text = null;
			boolean chk = true;
			if(l1 != null)
				for(WebElement l : l1)
				{
					text = l.getText();
					if(!(text.contains(str)))
						chk = false;
				}
			return chk;
		}
		public static boolean chkEquality(List<String>str1,String[] str2)
		{
			if(str2 == null || str1 == null || str1.size() != str2.length)
			{
				return false;
			}
			boolean present = false;
			Iterator<String> it = str1.iterator();
			while(it.hasNext())
			{
				String chk = it.next().toString();
				present = false;
				for(int j=0;j <str2.length;j++)
				{
					if(chk.equalsIgnoreCase(str2[j]))
					{
						present = true;
						break;
					}
				}
				if(present == false)
					return present;
			}
			return present;
		}
		public static String expectedValue(String s)
		{
			String str = SalesforceCommonUtilities.getExpectedValue(s);
			return str;
		}

	public static void screenShot(String fileName)
	{
		TakesScreenshot scr = (TakesScreenshot) driver;
		File sourceFile = scr.getScreenshotAs(OutputType.FILE);
		String location = SalesforceAutomationConstants.SCREEN_SHOT_PATH+fileName;
		File scrFile = new File(location);
		try {
			FileUtils.copyFile(sourceFile, scrFile);
			report.logTestInfo("Screen shot captured");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static boolean chkIfTextIsPresent(String str)
	{
		if(driver.getPageSource().contains(str))
		return true;
		else return false;
	}
}
