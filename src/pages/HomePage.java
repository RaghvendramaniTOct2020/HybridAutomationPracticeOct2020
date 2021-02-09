package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreDefinedAction;

public class HomePage extends PreDefinedAction{
	
	public AuthenticationPage clickOnSignIn() {
		WebDriverWait wait=new WebDriverWait(driver, 5);
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header_user_info>a")));
		System.out.println("Clicked on Sign In");
		element.click();
		return new AuthenticationPage();
	}
}
