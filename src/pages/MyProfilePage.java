package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class MyProfilePage extends PreDefinedAction{
	private PropertiesFileReader myProfilePageProperties;
	private static MyProfilePage myProfilePage;
	
	private MyProfilePage () {
		myProfilePageProperties=new PropertiesFileReader(ConfigFilePath.MY_PROFILE_PAGE_PROPERTIES);
	}
	
	public static MyProfilePage getInstance() {
		if(myProfilePage==null)
			myProfilePage=new MyProfilePage();
		return myProfilePage;
	}
	public String getHeaderText() {
		/*WebDriverWait wait =new WebDriverWait(driver,5);
		String headerText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info span")))
				.getText();
		return headerText*/;
		return getHeaderText(myProfilePageProperties.getValue("getHeaderText"), true);
	}
	
	public String getUserFullName() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		String fullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account>span")))
				.getText();
		return fullName;*/
		return getUserFullName(myProfilePageProperties.getValue("getUserFullName"), true);
	}
	
	public ProductCategoryPage selectSection(String sectionName) {
		return selectSection(sectionName, myProfilePageProperties.getValue("woemnProductCategory"), myProfilePageProperties.getValue("dressProductCategory"),
				myProfilePageProperties.getValue("tshirtProductCategory=") , true);
	}
}
