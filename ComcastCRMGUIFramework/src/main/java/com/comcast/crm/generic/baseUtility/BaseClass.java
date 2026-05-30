package com.comcast.crm.generic.baseUtility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.LogOut;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public DatabaseUtility dbLib = new DatabaseUtility();
	
	protected WebDriver driver;
    protected WebDriverUtility wLib;

	@BeforeSuite(alwaysRun = true)
	public void connectTodB() throws SQLException {
		dbLib.getDbConnection();
		System.out.println("===Execute Before Suite===");
	}
	
	@BeforeTest
	public void configBT(){
		System.out.println("===Execute Before Test===");
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser() throws Throwable {
		
		String BROWSER = fLib.getDataFromPropFile("browser");
		//String BROWSER = browser;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		UtilityClassObject.setDriver(driver);

		//UtilityClassObject.getDriver().manage().window().maximize();
		wLib = new WebDriverUtility(driver);
		wLib.setImplicitlyWait();

		System.out.println("==Execute Before Class==");
	}

	@BeforeMethod(alwaysRun = true)
	public void logInToApp() throws Throwable {

		String URL = fLib.getDataFromPropFile("url");
		String USERNAME = fLib.getDataFromPropFile("username");
		String PASSWORD = fLib.getDataFromPropFile("password");

		UtilityClassObject.getDriver().get(URL);

		LoginPage lp = new LoginPage(driver);
		wLib.waitForElementPresent(lp.getLoginBtn());
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("=Execute Before Method=");
	}

	@AfterMethod(alwaysRun = true)
	public void logOutFromApp() {
		
		LogOut lo = new LogOut(driver);
		lo.logOut();
	}
	

	@AfterClass(alwaysRun = true)
	public void configAC() {
	    if (driver != null) {
	        driver.quit();
	        UtilityClassObject.unload();
	    }
		
		System.out.println("==Execute After Class==");
	}
	
	@AfterTest
	public void configAT(){
		System.out.println("===Execute After Test===");
	}

	@AfterSuite(alwaysRun = true)
	public void closeThedB() throws SQLException {
		
		dbLib.closeDbConnection();
		System.out.println("===Execute After Suite===");
	}

}
