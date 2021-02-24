package pages;
import java.util.List;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class CheckoutAddressPage extends PreDefinedAction{
	private PropertiesFileReader checkoutAddressPageProperties;
	private static CheckoutAddressPage checkoutAddressPage;
	
	private CheckoutAddressPage () {
		checkoutAddressPageProperties=new PropertiesFileReader(ConfigFilePath.CHECKOUT_ADDRESS_PAGE_PROPERTIES);
	}
	
	public static CheckoutAddressPage getInstance() {
		if(checkoutAddressPage==null)
			checkoutAddressPage=new CheckoutAddressPage();
		return checkoutAddressPage;
	}
	public List<String> getDeliveryAddress() {
/*		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("#address_delivery li:not([class='address_update']):not([class='address_title'])")));

		List<String> deliveryList = new ArrayList<>();
		for (WebElement ele : eleList) {
			deliveryList.add(ele.getText());
		}

		return deliveryList;*/
		return getDeliveryAddress(checkoutAddressPageProperties.getValue("deliveryAddress"), true);
	}

	public List<String> getBilingAddress() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("#address_invoice li:not([class='address_update']):not([class='address_title'])")));

		List<String> billingList = new ArrayList<>();
		for (WebElement ele : eleList) {
			billingList.add(ele.getText());
		}
		return billingList;*/
		return getBilingAddress(checkoutAddressPageProperties.getValue("billingAddress"), false);
	}

	public CheckoutShippingPage clickOnProcessToCheckout() {
		/*driver.findElement(By.cssSelector("button[name='processAddress']")).click();
		System.out.println("STEP - Click Proceed to checkout on Address page");
		return CheckoutShippingPage.getInstance();*/
		return clickOnProcessToCheckoutAddressPage(checkoutAddressPageProperties.getValue("ProcessToCheckout"), true);
	}

}
