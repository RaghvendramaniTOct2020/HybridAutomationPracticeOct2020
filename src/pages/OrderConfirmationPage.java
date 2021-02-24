package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class OrderConfirmationPage extends PreDefinedAction{
	private PropertiesFileReader orderConfirmationPageProperties;
	private static OrderConfirmationPage orderConfirmationPage;
	
	private OrderConfirmationPage () {
		orderConfirmationPageProperties=new PropertiesFileReader(ConfigFilePath.ORDER_CONFIRMATION_PAGE_PROPERTIES);
	}
	
	public static OrderConfirmationPage getInstance() {
		if(orderConfirmationPage==null)
			orderConfirmationPage=new OrderConfirmationPage();
		return orderConfirmationPage;
	}
	public String capatureTotalAmount() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price strong"))).getText()
				.substring(1);*/
		return capatureTotalAmount(orderConfirmationPageProperties.getValue("capatureTotalAmount"), true);
	}

	public OrderHistoryPage clickOnBackToOrder() {

		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Back to orders']"))).click();
		System.out.println("STEP - Click Proceed to checkout on Order History page");
		return new OrderHistoryPage();*/
		return clickOnBackToOrder(orderConfirmationPageProperties.getValue("clickOnBackToOrder"), true);
	}

}
