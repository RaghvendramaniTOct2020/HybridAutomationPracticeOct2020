package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreDefinedAction;
import util.PropertiesFileReader;

public class AuthenticationPage extends PreDefinedAction{
	PropertiesFileReader authPage;
	
	public AuthenticationPage() {
		authPage=new PropertiesFileReader("./src/pageProperties/authenticationPageProperties.properties");
	}
	
	public void enterEmailAdress(String email) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(authPage.getValue("createAccountEmail"))))
		.sendKeys(email);
		System.out.println("Enter email address");
	}
	
	public CreateAccountPage clickOnCreateAccount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(authPage.getValue("CreateAccountPage")))).click();
		System.out.println("Clicked on the submit button to fill the other details");
		return new CreateAccountPage();
	}
	
	public boolean isAuthenticationHeaderVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authPage.getValue("authenticationHeaderVisible"))));
		return element.isDisplayed();
	}
	
	public MyProfilePage doLogin(String emailAddress, String pwd) {
		enterEmailAddressInLogin(emailAddress);
		enterPasswordInLogin(pwd);
		clickOnSignInButton();
		return new MyProfilePage();
	}

	public void clickOnSignInButton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(authPage.getValue("SignInButton")))).click();
		System.out.println("STEP - Click on sign button");
	}

	public void enterPasswordInLogin(String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authPage.getValue("passwordInLogin")))).sendKeys(pwd);
		System.out.println("STEP - Enter Password on login section");
	}

	public void enterEmailAddressInLogin(String emailAddress) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(authPage.getValue("emailAddressInLogin")))).sendKeys(emailAddress);
		System.out.println("STEP - Enter email address on login section");
	}

	public String verifyErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(authPage.getValue("loginErrorMessage"))))
				.getText();
	}
}
