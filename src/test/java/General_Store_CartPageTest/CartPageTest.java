package General_Store_CartPageTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import General_Store_App_Test.GenericUtilities.BaseClass;
import General_Store_App_Test.GenericUtilities.GesturesUtility;
import General_Store_App_Test.ObjectRepository.CartPage;
import General_Store_App_Test.ObjectRepository.ProductPage;
import General_Store_App_Test.ObjectRepository.SignUpPage;
import io.appium.java_client.AppiumBy;

public class CartPageTest extends BaseClass{
	@Test
	public void cartPageTest() throws InterruptedException {
		signUpPage.setNameField("Disha");
		signUpPage.selectCountry("Argentina");
		signUpPage.selectGender("Female");
		ProductPage productPage = signUpPage.clickOnShopButton();	
		
		productPage.addToCartByIndext(0);
		productPage.addToCartByIndext(0);
		CartPage cartPage = productPage.goToCartPage();
		
		cartPage.checkTermsAndCondition();
		cartPage.selectCheckBox();
		cartPage.clickPurchaseButton();
	}

}
