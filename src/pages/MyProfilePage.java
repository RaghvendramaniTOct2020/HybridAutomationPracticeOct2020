package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PreDefinedAction;

public class MyProfilePage extends PreDefinedAction{
	
	public String getHeaderText() {
		WebDriverWait wait =new WebDriverWait(driver,5);
		String headerText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info span")))
				.getText();
		return headerText;
	}
}
