package General_Store_App_Test.ObjectRepository;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import General_Store_App_Test.GenericUtilities.GesturesUtility;
import General_Store_App_Test.GenericUtilities.JavaUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends GesturesUtility {
	
	private AndroidDriver driver;
	private JavaUtility javaUtility = new JavaUtility();
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> productName;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPurchaseAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditionBtn;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement closeBtn;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement purchaseBtn;
	
	public String getProductName() {
		int count = productName.size();
		String name = "";
		for (int i = 0; i < count; i++) {
			name = productName.get(i).getAttribute("text");
		}
		return name;
	}
	
	
	public double getProductSum() {
		int count = productPrice.size();
		double sum=0;
		for (int i = 0; i < count; i++) {
			String stringAmount = productPrice.get(i).getText();
			Double amount = javaUtility.getDoubleFormattedAmount(stringAmount.substring(1));
			sum = sum+amount;
		}
		return sum;
	}
	
	public double getTotalPurchaseAmount() {
		String totalAmount = totalPurchaseAmount.getAttribute("text");
		return javaUtility.getDoubleFormattedAmount(totalAmount.substring(1));
	}
	
	public void checkTermsAndCondition() {
		longClickGesture(termsAndConditionBtn);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		closeBtn.click();
	}
	
	public void selectCheckBox() {
		checkBox.click();
	}
	
	public void clickPurchaseButton() {
		purchaseBtn.click();
	}

}
