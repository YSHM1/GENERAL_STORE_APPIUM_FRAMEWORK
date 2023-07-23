package General_Store_App_Test.GenericUtilities;

import java.io.File;
import java.io.IOException;

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

import io.appium.java_client.android.AndroidDriver;

public class ListenersUtility extends BaseClass implements ITestListener, ISuiteListener {

	ExtentSparkReporter sparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;
	AndroidDriverUtility androidDriverUtility;
	AndroidDriver driver;
	
	public void onStart(ISuite suite) {
		System.out.println(suite.getClass().getSimpleName());
	}

	public void onFinish(ISuite suite) {
		
	}

	public void onTestStart(ITestResult result) {
		//String reportName = result.getClass().getSimpleName();
		sparkReporter = new ExtentSparkReporter(new File("./Extent-Report/"+result.getClass().getSimpleName()+".html"));
		sparkReporter.config().setDocumentTitle("Android Automation Result");
		sparkReporter.config().setReportName(result.getMethod().getMethodName());
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", "Android");
		extentReports.setSystemInfo("Device", "Pixel 2 Xl_Emulator");
		extentReports.setSystemInfo("App Name", "General-Store.apk");
		
		String className = result.getMethod().getClass().getSimpleName();
		extentTest = extentReports.createTest(className+"_"+result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.pass(result.getMethod().getMethodName()+" is Passed" );
		extentTest.log(Status.PASS, "Passed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.fail(result.getMethod().getMethodName()+" is Failed" );
		extentTest.log(Status.FAIL, "Failed");
		extentTest.fail(result.getThrowable());
		
		try {
			driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		androidDriverUtility = new AndroidDriverUtility();
		try {
			extentTest.addScreenCaptureFromPath(androidDriverUtility.getScreenShotPath(result.getMethod().getMethodName(), driver),
												result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.skip(result.getMethod().getMethodName()+" is Skipped" );
		extentTest.skip(result.getThrowable());
		extentTest.log(Status.SKIP, "Skipped");
		
		try {
			extentTest.addScreenCaptureFromPath(androidDriverUtility.getScreenShotPath(result.getMethod().getMethodName(), driver),
												result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
	
	

}
