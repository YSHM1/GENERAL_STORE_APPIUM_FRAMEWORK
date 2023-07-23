package General_Store_SignUpTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import General_Store_App_Test.GenericUtilities.BaseClass;
import General_Store_App_Test.ObjectRepository.SignUpPage;
import io.appium.java_client.AppiumBy;

public class SignUpTestWithJsonData extends BaseClass{
	@Test(enabled=true,dataProvider="getData")
	public void signUpTest(HashMap<String, String> input) throws InterruptedException {
		signUpPage.setNameField(input.get("name"));
		signUpPage.selectCountry(input.get("country"));
		signUpPage.selectGender(input.get("gender"));
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
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = jsonUtility.getJSONData(System.getProperty("user.dir")+"/src/test/resources/GeneralStoreTestData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
	}

}
