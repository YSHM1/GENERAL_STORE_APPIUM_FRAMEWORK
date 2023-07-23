package General_Store_App_Test.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import General_Store_App_Test.ObjectRepository.SignUpPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public SignUpPage signUpPage;
	public JsonUtility jsonUtility;
	
	@BeforeClass(alwaysRun = true)
	public void appiumConfiguration() throws FileNotFoundException, IOException {
		
		 Properties properties = new Properties();
		 properties.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/CommonData.properties"));
		 String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : properties.getProperty("ipAddress");
		 String port = properties.getProperty("port");
		 String deviceName = properties.getProperty("AndroidDeviceName");
		
//		service = new AppiumServiceBuilder().withAppiumJS(new File("C:/Users/asus/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
//							.withIPAddress(ipAddress).usingPort(Integer.partseInt(port)).build();
//		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\resources\\General-Store.apk");
		options.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		
//		driver = new AndroidDriver(service.getUrl(), options); getting url and port num from service object as it knows all the appium servers details
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		signUpPage = new SignUpPage(driver);
		jsonUtility = new JsonUtility();
	}
	
	
	@AfterClass(alwaysRun = true)
	public void closeConnections() {
		driver.quit();
//		service.stop();
	}

}
