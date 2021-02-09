package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreDefinedAction;

public class AuthenticationPage extends PreDefinedAction{
	
	public void enterEmailAdress(String email) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")))
		.sendKeys(email);
		System.out.println("Enter email address");
	}
	
	public CreateAccountPage clickOnCreateAccount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		System.out.println("Clicked on the submit button to fill the other details");
		return new CreateAccountPage();
	}
	
	public boolean isAuthenticationHeaderVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='	Authentication']")));
		return element.isDisplayed();
	}
}
