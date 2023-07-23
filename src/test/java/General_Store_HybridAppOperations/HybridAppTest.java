package General_Store_HybridAppOperations;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HybridAppTest extends BaseClass {
	@Test
	public void hybridAppTest() throws InterruptedException {
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
		
		Set<String> context = driver.getContextHandles();
		for(String contextName: context) {
			System.out.println(contextName);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("Appium Mobile Automation",Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}

}
