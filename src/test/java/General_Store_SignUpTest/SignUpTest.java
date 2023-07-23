package General_Store_SignUpTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import General_Store_App_Test.GenericUtilities.BaseClass;
import General_Store_App_Test.ObjectRepository.SignUpPage;
import io.appium.java_client.AppiumBy;

public class SignUpTest extends BaseClass{
	@Test(enabled=true,dataProvider="getData")
	public void signUpTest(String name, String gender, String countryName) throws InterruptedException {
		signUpPage.setNameField(name);
		signUpPage.selectCountry(countryName);
		signUpPage.selectGender(gender);
		signUpPage.clickOnShopButton();
		Thread.sleep(2000);
		signUpPage.setActivity();
	}
	
	@Test(enabled=false)
	public void toastMessageValidation() {
		SignUpPage page = new SignUpPage(driver);
		page.selectGender("female");
		page.selectCountry("Canada");
		page.clickOnShopButton();
		String actualToastMessage = page.getToastMsg(1);
		Assert.assertEquals(actualToastMessage, "Please enter your name");
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"Disha","Female","Argentina"},{"Bhola", "Male", "Canada"}};
	}

}
