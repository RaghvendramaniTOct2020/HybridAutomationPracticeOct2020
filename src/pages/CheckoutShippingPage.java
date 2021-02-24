package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class CheckoutShippingPage extends PreDefinedAction {
	private PropertiesFileReader checkoutShippingPageProperties;
	private static CheckoutShippingPage checkoutShippingPage;
	
	private CheckoutShippingPage () {
		checkoutShippingPageProperties=new PropertiesFileReader(ConfigFilePath.CHECKOUT_SHIPPING_PAGE_PROPERTIES);
	}
	
	public static CheckoutShippingPage getInstance() {
		if(checkoutShippingPage==null)
			checkoutShippingPage=new CheckoutShippingPage();
		return checkoutShippingPage;
	}
	public String capatureShippingCharges() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.delivery_option_price")))
				.getText().substring(1);*/
		return capatureShippingCharges(checkoutShippingPageProperties.getValue("shippingCharges"), true);
	}

	public void clickOnTermAndCondition() {
		clickOnTermAndCondition(checkoutShippingPageProperties.getValue("termAndCondition"), true);
	}

	public CheckoutPaymentPage clickOnProcessToCheckout() {
		return clickOnProcessToCheckout(checkoutShippingPageProperties.getValue("processToCheckout"), true);
	}
}
