package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class CheckoutPaymentPage extends PreDefinedAction {
	private PropertiesFileReader checkoutPaymentPageProperties;
	private static CheckoutPaymentPage checkoutPaymentPage;
	
	private CheckoutPaymentPage () {
		checkoutPaymentPageProperties=new PropertiesFileReader(ConfigFilePath.CHECKOUT_PAYMENT_PAGE_PROPERTIES);
	}
	
	public static CheckoutPaymentPage getInstance() {
		if(checkoutPaymentPage==null)
			checkoutPaymentPage=new CheckoutPaymentPage();
		return checkoutPaymentPage;
	}
	public String getToalPrice() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total_price"))).getText().substring(1);*/
		return getToalPricePayment(checkoutPaymentPageProperties.getValue("totalPrice"), true);
	}

	public OrderSummaryPage paymentBy(String paymentType) {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		switch (paymentType.toUpperCase()) {
		case "CHECK":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#HOOK_PAYMENT a[title*='check']")))
					.click();
			break;

		case "BANK WIRE":
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#HOOK_PAYMENT a[title*='bank wire']"))).click();
			break;
		default:
			break;
		}
		return new OrderSummaryPage();*/
		return paymentBy(paymentType, checkoutPaymentPageProperties.getValue("paymentByCheck"), checkoutPaymentPageProperties.getValue("paymentByWire"), true);
	}

}
