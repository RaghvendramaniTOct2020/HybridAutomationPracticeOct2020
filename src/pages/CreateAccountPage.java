package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreDefinedAction;
import pojo.CreateAccountDetailspojo;

public class CreateAccountPage extends PreDefinedAction {
	WebDriverWait wait;
	
	public boolean isHeadingText() {
		wait = new WebDriverWait(driver,30);
		return wait.until(ExpectedConditions.textToBe(By.cssSelector("#noSlide h1"), "CREATE AN ACCOUNT"));
	}
	private void getGender(String gender) {
		System.out.println("STEP-Enter Gender");
		if(gender!=null) {
			WebElement titleElement;
			titleElement=gender.equalsIgnoreCase("male")? wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_gender1")))
					:wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_gender2")));
			wait.until(ExpectedConditions.elementToBeClickable(titleElement));
			titleElement.click();
		}
		else
			System.out.println("Gender is blank");
	}
	
	private void getFirstName(String firstName) {
		System.out.println("STEP-Enter First Name");
		if(firstName!=null)
			driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(firstName);
		else
			System.out.println("First name is blank");
	}
	
	private void getLastName(String lastName) {
		System.out.println("STEP-Enter Last Name");
		if(lastName!=null)
			driver.findElement(By.cssSelector("#customer_lastname")).sendKeys(lastName);
		else
			System.out.println("Last name is blank");
	}
	
	private void getPassword(String password) {
		System.out.println("Enter Password");
		if(password!=null) 
			driver.findElement(By.id("passwd")).sendKeys(password);
		else
			System.out.println("Password field is blank");
	}
	
	private void getDays(String days) {
		System.out.println("STEP-Select day  from drop down");
		if(days!=null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-days"))).click();
			Select s = new Select(driver.findElement(By.id("days")));
			s.selectByValue(days);
		}else
			System.out.println("Birth day selected from drop down");
	}
	
	private void getMonth(String month) {
		System.out.println("STEP-Select Month");
		if(month!=null) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months"))).click();
		Select s = new Select(driver.findElement(By.id("months")));
		s.selectByValue(month);
		}else
			System.out.println("Month is blank");
	}
	
	private void getYear(String year) {
		System.out.println("STEP-Select Year");
		if(year!=null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years"))).click();
			Select s = new Select(driver.findElement(By.id("years")));
			s.selectByValue(year);
		}else
			System.out.println("Birth year is blank");
	}
	
	private void getCompany(String company) {
		System.out.println("STEP-Enter Company name");
		if(company!=null)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("company"))).sendKeys(company);
		else
			System.out.println("Company Name is Blank");
	}
	
	private void getAddress(String address) {
		System.out.println("STEP-Enter Address");
		if(address!=null)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address1"))).sendKeys(address);
		else
			System.out.println("Address is blank");
	}
	
	private void getCity(String city) {
		System.out.println("STEP-Select City");
		if(city!=null)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys(city);
		else
			System.out.println("City Name is Blank");
	}
	
	private void getState(String state) {
		System.out.println("STEP-Select State");
		if(state!=null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-id_state")))).click();
			Select s = new Select(driver.findElement(By.id("id_state")));
			s.selectByVisibleText(state);
		}else
			System.out.println("State is Blank");
	}
	
	private void getPostCode(String postCode) {
		System.out.println("STEP-Enter Post code");
		if(postCode!=null)
			driver.findElement(By.id("postcode")).sendKeys(postCode);
		else
			System.out.println("Postcode is blank");
	}
	
	private void getOther(String other) {
		System.out.println("STEP-Enter Other Information");
		if(other!=null)
			driver.findElement(By.id("other")).sendKeys(other);
		else
			System.out.println("Other information is blank");
	}
	
	private void getPhone(String phone) {
		System.out.println("STEP-Enter Home mobile number");
		if(phone!=null)
			driver.findElement(By.id("phone")).sendKeys(phone);
		else
			System.out.println("Home mobile number is blank");
	}
	
	private void getMobile(String mobile) {
		System.out.println("STEP-Enter Mobile number");
		if(mobile!=null) 
			driver.findElement(By.id("phone_mobile")).sendKeys(mobile);
		else
			System.out.println("Mobile number is blank");
	}

	public void enterCreateAccountDetails(CreateAccountDetailspojo createAccountDetailspojo) {
	/*	if(createAccountDetailspojo.getGender().equalsIgnoreCase("male")) {
			wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id_gender1"))).click();
			System.out.println("Select Mr. as title");
		}
		else {
			wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id_gender2"))).click();
			System.out.println("Select Mrs. as title");
		}*/
		getGender(createAccountDetailspojo.getGender());
		getFirstName(createAccountDetailspojo.getFirstname());
		getLastName(createAccountDetailspojo.getLastname());
/*
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("test1_test@gmail.com");
		System.out.println("Enter Email");*/
		getPassword(createAccountDetailspojo.getPassword());
		getDays(createAccountDetailspojo.getDays());
		getMonth(createAccountDetailspojo.getMonth());
		getYear(createAccountDetailspojo.getYear());
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#uniform-newsletter")))).click();
		System.out.println("Click on Sign up for our newsletter!");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-optin"))).click();
		System.out.println("Click on Receive special offers from our partners!");
		getCompany(createAccountDetailspojo.getCompany());
		getAddress(createAccountDetailspojo.getAddress());
		getCity(createAccountDetailspojo.getCity());
		getState(createAccountDetailspojo.getState());
		getPostCode(createAccountDetailspojo.getPostcode());
		getOther(createAccountDetailspojo.getOther());
		getPhone(createAccountDetailspojo.getPhone());
		getMobile(createAccountDetailspojo.getMobile());
	}
	
	public MyProfilePage clickOnRegistration() {
		wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		System.out.println("Details Registered in Application");
		return new MyProfilePage();
	}
	
	public List<String> getErrorMessage() {
		List<WebElement> listOfError=driver.findElements(By.cssSelector("ol>li"));
		List<String> listOfErrorText=new ArrayList<String>();
		listOfErrorText.add(driver.findElement(By.cssSelector(".alert.alert-danger>p")).getText());
		for(WebElement element:listOfError) {
			listOfErrorText.add(element.getText());
		}
		return listOfErrorText;
	}
}
