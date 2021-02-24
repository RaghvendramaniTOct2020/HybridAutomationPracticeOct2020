package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class HomePage extends PreDefinedAction{
	private PropertiesFileReader homePageProperties;
	private static HomePage homePage;
	
	private HomePage () {
		homePageProperties=new PropertiesFileReader(ConfigFilePath.HOME_PAGE_PROPERTIES);
	}
	
	public static HomePage getInstance() {
		if(homePage==null)
			homePage=new HomePage();
		return homePage;
	}
	public AuthenticationPage clickOnSignIn() {
		clickOnElement(homePageProperties.getValue("SignIn"), true);
		System.out.println("STEP-Clicked on Sign In");
		return AuthenticationPage.getInstance();
	}
}
