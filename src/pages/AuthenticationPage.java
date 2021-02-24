package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class AuthenticationPage extends PreDefinedAction{
	private PropertiesFileReader authPageProperties;
	private static AuthenticationPage authenticationPage;
	
	private AuthenticationPage () {
		authPageProperties=new PropertiesFileReader(ConfigFilePath.AUTHENTICATION_PAGE_PROPERTIES);
	}
	
	public static AuthenticationPage getInstance() {
		if(authenticationPage==null)
			authenticationPage=new AuthenticationPage();
		return authenticationPage;
	}
	
	public void enterEmailAdress(String email) {
		System.out.println("STEP-Enter email address");
		enterText(email, authPageProperties.getValue("createAccountEmail"), true);
	}
	
	public CreateAccountPage clickOnCreateAccount() {
	/*	WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(authPageProperties.getValue("CreateAccountPage")))).click();*/
		clickOnElement(authPageProperties.getValue("CreateAccountPage"), true);
		System.out.println("STEP-Clicked on the submit button to fill the other details");
		return CreateAccountPage.getInstance();
	}
	
	public boolean isAuthenticationHeaderVisible() {
		/*WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authPageProperties.getValue("authenticationHeaderVisible"))));*/
		//WebElement element=getElement(authPageProperties.getValue("authenticationHeaderVisible"), true);
		return isElementDisplayed(authPageProperties.getValue("authenticationHeaderVisible"), true);
		//return element.isDisplayed();
	}
	
	public MyProfilePage doLogin(String emailAddress, String pwd) {
		enterEmailAddressInLogin(emailAddress);
		enterPasswordInLogin(pwd);
		clickOnSignInButton();
		return MyProfilePage.getInstance();
	}

	public void clickOnSignInButton() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(authPageProperties.getValue("SignInButton")))).click();*/
		System.out.println("STEP - Click on sign button");
		clickOnElement(authPageProperties.getValue("SignInButton"), true);
	}

	public void enterPasswordInLogin(String pwd) {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authPageProperties.getValue("passwordInLogin")))).sendKeys(pwd);*/
		System.out.println("STEP - Enter Password on login section");
		enterText(pwd, authPageProperties.getValue("passwordInLogin"), true);
	}

	public void enterEmailAddressInLogin(String emailAddress) {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(authPageProperties.getValue("emailAddressInLogin")))).sendKeys(emailAddress);*/
		enterText(emailAddress, authPageProperties.getValue("emailAddressInLogin"), true);
		System.out.println("STEP - Enter email address on login section");
	}

	public String verifyErrorMessage() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(authPageProperties.getValue("loginErrorMessage"))))
				.getText();*/
		return getElementText(authPageProperties.getValue("loginErrorMessage"), true);
	}
}
