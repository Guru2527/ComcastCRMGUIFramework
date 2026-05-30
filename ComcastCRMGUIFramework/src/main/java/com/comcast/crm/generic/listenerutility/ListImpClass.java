package com.comcast.crm.generic.listenerutility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IConfigurationListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener, IConfigurationListener{

	public static ExtentReports report;

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report Configuration");

		// Spark report config
		String time = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/"+suite.getName()+"_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add Env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Winodws-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report BackUp");

		report.flush();

	}
	
	@Override
	public void onConfigurationFailure(ITestResult result) {

	    UtilityClassObject.getTest().fail("CONFIGURATION FAILED: "+ result.getMethod().getMethodName());

	    UtilityClassObject.getTest().fail(result.getThrowable());
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("========>" + result.getMethod().getMethodName() + ">======START========");

		ExtentTest test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		UtilityClassObject.getTest().log(Status.INFO, result.getMethod().getMethodName() + "===>STARTED<===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("========>" + result.getMethod().getMethodName() + ">======END========");
		UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName() + "===>COMPLETED<===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();

//		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
//		File srcFile = ts.getScreenshotAs(OutputType.FILE);
//		
//		File destFile = new File("./ScreenShots/"+testName+".png");
//		try {
//			FileHandler.copy(srcFile, destFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(filePath, testName + "_" + time);
		UtilityClassObject.getTest().fail(result.getThrowable());
		//UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "===>FAILED<===");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	
	

}
