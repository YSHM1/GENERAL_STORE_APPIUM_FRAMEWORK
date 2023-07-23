package General_Store_ProductTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import General_Store_App_Test.GenericUtilities.BaseClass;
import General_Store_App_Test.ObjectRepository.CartPage;
import General_Store_App_Test.ObjectRepository.ProductPage;
import General_Store_App_Test.ObjectRepository.SignUpPage;
import io.appium.java_client.AppiumBy;

public class CheckProductInCartTest extends BaseClass {
	@Test(groups= {"Smoke Testing"})
	public void checkProductInCartTest() throws InterruptedException {
		signUpPage.setNameField("Disha");
		signUpPage.selectCountry("Argentina");
		signUpPage.selectGender("Female");
		ProductPage productPage = signUpPage.clickOnShopButton();
		
		productPage.selectProduct("Jordan 6 Rings");
		
		CartPage cartPage = productPage.goToCartPage();
		
		String actualProductName = cartPage.getProductName();
		Assert.assertEquals(actualProductName, "Jordan 6 Rings");
	}

}
