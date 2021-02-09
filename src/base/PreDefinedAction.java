package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PreDefinedAction {
	protected static WebDriver driver;

	public static void start() {
		String path="./resources/windows/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		System.out.println("Open Browser");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		System.out.println("Navigate to Application");
		System.out.println("Maximize browser window");
		driver.manage().window().maximize();
		
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	
}
