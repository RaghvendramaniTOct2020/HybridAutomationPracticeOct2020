package testscripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PreDefinedAction;

public class TestBase {
	@BeforeMethod
	public void setup() {
		System.out.println("STEP-Open Browser");
		PreDefinedAction.start();
	}
	@AfterMethod
	public void teardown() {
		System.out.println("STEP-Close Browser");
		PreDefinedAction.closeBrowser();
	}
}
