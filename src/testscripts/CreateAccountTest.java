package testscripts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailspojo;
import util.dataAccountValidate;
public class CreateAccountTest extends TestBase{
	
	@Test(dataProvider="AccountUIValidationDataProvider")
	public void dataDrivenAccountUIValidationTest(String  email,String  Gender,String  firstname,String  lastname,String  password,String  days,String  month,String  year,String  company,String  address,String  city,String  state,String  postcode,
			String  other,String  phone,String  mobile) {
		  SoftAssert soft = new SoftAssert();
			HomePage homePage = new HomePage();
			System.out.println("STEP-Click on Sign in Option");
			AuthenticationPage authenticationPage = homePage.clickOnSignIn();
			System.out.println("STEP-Verify Authentication Header is displayed ");
			boolean isAuthHeader=authenticationPage.isAuthenticationHeaderVisible();
			soft.assertTrue(isAuthHeader, "Authentication Header is not displayed");
			System.out.println("STEP-Enter email address");
			authenticationPage.enterEmailAdress(email);
			CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
			System.out.println("Verify-Create Account heading page is as expected");
			boolean isAccountHeadingDisplayed=createAccountPage.isHeadingText();
			soft.assertTrue(isAccountHeadingDisplayed, "Create Account  Header is not displayed");
			System.out.println("Navigate to create account page");
			CreateAccountDetailspojo createAccountDetailspojo=new CreateAccountDetailspojo();
			createAccountDetailspojo.setGender(Gender);
			createAccountDetailspojo.setFirstname(firstname);
			createAccountDetailspojo.setLastname(lastname);
			createAccountDetailspojo.setPassword(password);
			createAccountDetailspojo.setDays(days);
			createAccountDetailspojo.setMonth(month);
			createAccountDetailspojo.setYear(year);
			createAccountDetailspojo.setCompany(company);
			createAccountDetailspojo.setAddress(address);
			createAccountDetailspojo.setCity(city);
			createAccountDetailspojo.setState(state);
			createAccountDetailspojo.setPostcode(postcode);
			createAccountDetailspojo.setOther(other);
			createAccountDetailspojo.setPhone(phone);
			createAccountDetailspojo.setMobile(mobile);
			
			createAccountPage.enterCreateAccountDetails(createAccountDetailspojo);
			MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
			String actual = myProfilePage.getHeaderText();
			String expected = "Raghv Tiwari";
			Assert.assertEquals(actual, expected, "Verification of headertext failed");
			soft.assertAll();
	}
	
	@Test(dataProvider="PojoDataProvider")
	public void dataDrivenAccountUIValidationTestpojo(CreateAccountDetailspojo createAccountDetailspojo) {
		SoftAssert soft = new SoftAssert();
		HomePage homePage = new HomePage();
		System.out.println("STEP-Click on Sign in Option");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP-Verify Authentication Header is displayed ");
		boolean isAuthHeader=authenticationPage.isAuthenticationHeaderVisible();
		soft.assertTrue(isAuthHeader, "Authentication Header is not displayed");
		System.out.println("STEP-Enter email address");
		authenticationPage.enterEmailAdress(createAccountDetailspojo.getEmail());
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		System.out.println("Verify-Create Account heading page is as expected");
		boolean isAccountHeadingDisplayed=createAccountPage.isHeadingText();
		soft.assertTrue(isAccountHeadingDisplayed, "Create Account  Header is not displayed");
		System.out.println("Navigate to create account page");
			createAccountPage.enterCreateAccountDetails(createAccountDetailspojo);
			MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
			String actual = myProfilePage.getHeaderText();
			String expected = createAccountDetailspojo.getFirstname()+" "+createAccountDetailspojo.getLastname();
			Assert.assertEquals(actual, expected, "Verification of headertext failed");
			soft.assertAll();
	}
	
	@DataProvider(name="PojoDataProvider")
	Object[][] pojoAccountTest() throws IOException {
		String[][] dataAccount=dataAccountValidate.dataAccountCreate("DataAccoutCreate.xlsx","dataAccountCreate");
		Object[][] output=new Object[dataAccount.length][1];
		for(int index=0;index<dataAccount.length;index++) {
			CreateAccountDetailspojo createAccountDetailspojo=new CreateAccountDetailspojo();
			createAccountDetailspojo.setEmail(dataAccount[index][0]);
			createAccountDetailspojo.setGender(dataAccount[index][1]);
			createAccountDetailspojo.setFirstname(dataAccount[index][2]);
			createAccountDetailspojo.setLastname(dataAccount[index][3]);
			createAccountDetailspojo.setPassword(dataAccount[index][4]);
			createAccountDetailspojo.setDays(dataAccount[index][5]);
			createAccountDetailspojo.setMonth(dataAccount[index][6]);
			createAccountDetailspojo.setYear(dataAccount[index][7]);
			createAccountDetailspojo.setCompany(dataAccount[index][8]);
			createAccountDetailspojo.setAddress(dataAccount[index][9]);
			createAccountDetailspojo.setCity(dataAccount[index][10]);	
			createAccountDetailspojo.setState(dataAccount[index][11]);
			createAccountDetailspojo.setPostcode(dataAccount[index][12]);
			createAccountDetailspojo.setOther(dataAccount[index][13]);
			createAccountDetailspojo.setPhone(dataAccount[index][14]);
			createAccountDetailspojo.setMobile(dataAccount[index][15]);
			output[index][0]=createAccountDetailspojo;
		}
			return output ;
	}
	@DataProvider(name="AccountUIValidationDataProvider")
	String[][] dataAccountTest() {
		String[][] dataAccount=null;
		try {
			dataAccount=util.dataAccountValidate.dataAccountCreate("DataAccoutCreate.xlsx","dataAccountCreate");
		}
		catch(Exception e) {
			System.out.println("File not present");
		}
		return dataAccount;
	}
	
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
		authenticationPage.enterEmailAdress("test1_testvalid5800bb@gmail.com");
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
