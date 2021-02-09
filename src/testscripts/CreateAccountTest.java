package testscripts;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailspojo;
public class CreateAccountTest extends TestBase{
	
	@Test
	public void automationPracticeLogin() {
	    SoftAssert soft = new SoftAssert();
		HomePage homePage = new HomePage();
		System.out.println("STEP-Click on Sign in Option");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP-Verify Authentication Header is displayed ");
		boolean isAuthHeader=authenticationPage.isAuthenticationHeaderVisible();
		soft.assertTrue(isAuthHeader, "Authentication Header is not displayed");
		System.out.println("STEP-Enter email address");
		authenticationPage.enterEmailAdress("test1_testvalid58@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		System.out.println("Verify-Create Account heading page is as expected");
		boolean isAccountHeadingDisplayed=createAccountPage.isHeadingText();
		soft.assertTrue(isAccountHeadingDisplayed, "Create Account  Header is not displayed");
		System.out.println("Navigate to create account page");
		CreateAccountDetailspojo createAccountDetailspojo=new CreateAccountDetailspojo();
		createAccountDetailspojo.setGender("male");
		createAccountDetailspojo.setFirstname("Raghv");
		createAccountDetailspojo.setLastname("Tiwari");
		createAccountDetailspojo.setPassword("Test_2346");
		createAccountDetailspojo.setDays("1");
		createAccountDetailspojo.setMonth("12");
		createAccountDetailspojo.setYear("1987");
		createAccountDetailspojo.setCompany("Equinox");
		createAccountDetailspojo.setAddress("9001 crowne springs circle");
		createAccountDetailspojo.setCity("Louisville");
		createAccountDetailspojo.setState("Kentucky");
		createAccountDetailspojo.setPostcode("40241");
		createAccountDetailspojo.setOther("NA");
		createAccountDetailspojo.setPhone("5027898146");
		createAccountDetailspojo.setMobile("9856145287");
		
		createAccountPage.enterCreateAccountDetails(createAccountDetailspojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = "Raghv Tiwari";
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
		soft.assertAll();
	}
	
	@Test
	public void createAccountUIValidationTest() {
	    SoftAssert soft = new SoftAssert();
		HomePage homePage = new HomePage();
		System.out.println("STEP-Click on Sign in Option");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP-Verify Authentication Header is displayed ");
		boolean isAuthHeader=authenticationPage.isAuthenticationHeaderVisible();
		soft.assertTrue(isAuthHeader, "Authentication Header is not displayed");
		System.out.println("STEP-Enter email address");
		authenticationPage.enterEmailAdress("test1_testvalid008@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		System.out.println("Verify-Create Account heading page is as expected");
		boolean isAccountHeadingDisplayed=createAccountPage.isHeadingText();
		soft.assertTrue(isAccountHeadingDisplayed, "Create Account  Header is not displayed");
		System.out.println("Navigate to create account page");
		CreateAccountDetailspojo createAccountDetailspojo=new CreateAccountDetailspojo();
		createAccountPage.enterCreateAccountDetails(createAccountDetailspojo);
		createAccountPage.clickOnRegistration();
		List<String>expectedErrorMessages=new ArrayList<String>();
		expectedErrorMessages.add("There are 8 errors");
		expectedErrorMessages.add("You must register at least one phone number.");
		expectedErrorMessages.add("lastname is required.");
		expectedErrorMessages.add("firstname is required.");
		expectedErrorMessages.add("passwd is required.");
		expectedErrorMessages.add("address1 is required.");
		expectedErrorMessages.add("city is required.");
		expectedErrorMessages.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		expectedErrorMessages.add("This country requires you to choose a State.");
		List<String>actualErrorMessages=createAccountPage.getErrorMessage();
		soft.assertEquals(actualErrorMessages, expectedErrorMessages);
		soft.assertAll();
	}
}