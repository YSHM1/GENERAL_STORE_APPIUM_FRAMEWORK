package General_Store_App_Test.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import General_Store_App_Test.GenericUtilities.GesturesUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUpPage extends GesturesUtility{

	public AndroidDriver driver;
//	public static AndroidDriver driver;
//	public AppiumDriver drivers;
	
	public SignUpPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
//		drivers = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//		PageFactory.initElements(new AppiumFieldDecorator(drivers), this);
//		SignUpPage.driver = driver;
//		PageFactory.initElements(new AppiumFieldDecorator(SignUpPage.driver), this);
//	    PageFactory.initElements(driver, this);

	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleRadioBtn;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleRadioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropDownMenu;
	
	private String dynamic_CountryXpath = "//android.widget.TextView[@text='%s']";
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopBtn;
	
	private String dynamic_ToastMsg_Xpath = "//android.widget.Toast)[%s]";
	
	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("female")) {
			femaleRadioBtn.click();
		}
		else if (gender.equalsIgnoreCase("Male")) {
			maleRadioBtn.click();
		}			
	}
	
	public void selectCountry(String countryName) {
		countryDropDownMenu.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
//		String countryXpath = String.format(dynamic_CountryXpath, countryName);
//		driver.findElement(By.xpath(countryXpath)).click();;
	}
	
	public ProductPage clickOnShopButton() {
		shopBtn.click();
		return new ProductPage(driver);
	}
	
	public String getToastMsg(int index) {
		String toastMsg_xpath = String.format(dynamic_ToastMsg_Xpath, index);
		return driver.findElement(By.xpath(toastMsg_xpath)).getAttribute("name");
	}
	
}
