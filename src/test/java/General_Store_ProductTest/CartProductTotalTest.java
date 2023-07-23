package General_Store_ProductTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import General_Store_App_Test.GenericUtilities.BaseClass;
import General_Store_App_Test.ObjectRepository.CartPage;
import General_Store_App_Test.ObjectRepository.ProductPage;
import General_Store_App_Test.ObjectRepository.SignUpPage;

public class CartProductTotalTest extends BaseClass{
	@Test(groups= {"Smoke Testing"})
	public void checkProductInCartTest() throws InterruptedException {
		signUpPage.setNameField("Disha");
		signUpPage.selectGender("Female");
		signUpPage.selectCountry("Argenitina");
		ProductPage productPage = signUpPage.clickOnShopButton();
		
		productPage.addToCartByIndext(0);
		productPage.addToCartByIndext(0);
		CartPage cartPage = productPage.goToCartPage();
		
		double sum = cartPage.getProductSum();
		double actualProductPrice = cartPage.getTotalPurchaseAmount();
		Assert.assertEquals(actualProductPrice, sum);
	}

}
