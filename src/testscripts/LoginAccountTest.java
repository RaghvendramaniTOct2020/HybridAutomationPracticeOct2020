package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyProfilePage;

public class LoginAccountTest extends TestBase {
	@Test(dataProvider="LoginDataProvider")
	public void verifyValidLogin(String email, String password,String firstName, String lastName) {
		HomePage homePage = new HomePage();
		System.out.println("STEP-Click on Sign in Option");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP-Enter email address and Password");
		MyProfilePage myProfilePage = authenticationPage.doLogin(email, password);

		String expectedUser = firstName+" "+lastName;
		String actualUser = myProfilePage.getUserFullName();
		System.out.println("STEP-Verify User Name displayed after login");
		Assert.assertEquals(actualUser, expectedUser);
	}
	
	@DataProvider(name="LoginDataProvider")
	String[][] loginTest() throws IOException {
		return util.dataAccountValidate.dataAccountCreate("DataAccoutCreate.xlsx","dataLogin");
	
	}
	
	@Test(enabled = false)
	public void verifyInvalidEmailAddressLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin("hiu@gmail.com", "Test_3429");

		String expectedMessage = "Authentication failed.";
		String actualMessage = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(enabled = false)
	public void verifyInvalidPasswordLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin("hui.test1@gmail.com", "Pest_3429");

		String expectedMessage = "Authentication failed.";
		String actualMessage = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(enabled = false)
	public void verifyEnterCredentialsLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.clickOnSignInButton();

		String expectedMessage = "An email address required.";
		String actualMessage = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
}
