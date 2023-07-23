package General_Store_App_Test.GenericUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import General_Store_App_Test.ObjectRepository.SignUpPage;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass_1 {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public SignUpPage signUpPage;
	
	@BeforeClass
	public void appiumConfiguration() throws MalformedURLException {
		
//		service = new AppiumServiceBuilder().withAppiumJS(new File("C:/Users/asus/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
//							.withIPAddress("127.0.0.1").usingPort(4723).build();
//		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 2 XL_Emulator");
		options.setApp("E:\\APPIUM_WORKSPACE\\GENERAL_STORE_APP_TESTING\\src\\test\\resources\\General-Store.apk");
		options.setChromedriverExecutable("E:\\APPIUM_WORKSPACE\\GENERAL_STORE_APP_TESTING\\src\\test\\resources\\chromedriver.exe");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		SignUpPage signUpPage = new SignUpPage(driver);
		JavaUtility javaUtility = new JavaUtility();
	}
	
	@BeforeMethod(enabled=false)
	public void preSetUp() {
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
	@AfterClass
	public void closeConnections() {
		driver.quit();
//		service.stop();
	}

}
