package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class OrderSummaryPage extends PreDefinedAction{
	private PropertiesFileReader orderSummaryPageProperties;
	private static OrderSummaryPage orderSummaryPage;
	
	private OrderSummaryPage () {
		orderSummaryPageProperties=new PropertiesFileReader(ConfigFilePath.ORDER_SUMMARY_PAGE_PROPERTIES);
	}
	
	public static OrderSummaryPage getInstance() {
		if(orderSummaryPage==null)
			orderSummaryPage=new OrderSummaryPage();
		return orderSummaryPage;
	}
	public String captureAmount() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount"))).getText().substring(1);*/
		return captureAmount(orderSummaryPageProperties.getValue("captureAmount"), true);
	}

	public OrderConfirmationPage clickOnConfirmOrder() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#cart_navigation button[type='submit']>span"))).click();
		System.out.println("STEP - Click Proceed to checkout on Order confirmation page");
		return new OrderConfirmationPage();*/
		return clickOnConfirmOrder(orderSummaryPageProperties.getValue("clickOnConfirmOrder"), true);
	}
}
