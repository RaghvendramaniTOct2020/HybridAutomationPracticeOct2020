package testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import base.PreDefinedAction;

public class TestBase {
	@BeforeMethod
	public void setup() {
		System.out.println("STEP-Open Browser");
		PreDefinedAction.start();
	}
	@AfterMethod
	public void teardown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			PreDefinedAction.captureScreenshot(result);
			System.out.println("Screen shot taken for Failure Step: "+result.getName());
		}
		System.out.println("STEP-Close Browser");
		PreDefinedAction.closeBrowser();
	}
}
