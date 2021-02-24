package base;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.common.io.Files;
import exceptionHandling.ElementNotEnabled;
import exceptionHandling.InvalidLocatorStrategyException;
import exceptionHandling.ProductNotFoundException;
import pages.CheckoutAddressPage;
import pages.CheckoutPaymentPage;
import pages.CheckoutShippingPage;
import pages.MyProfilePage;
import pages.OrderConfirmationPage;
import pages.OrderHistoryPage;
import pages.OrderSummaryPage;
import pages.ProductCategoryPage;
import pages.ProductDetailsPage;
import pages.ShoppingSummaryPage;
import pojo.ProductDetailsPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class PreDefinedAction {
	protected static WebDriver driver;
	private static WebDriverWait wait;

	public static void start() {
		String path="./resources/windows/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		System.out.println("Open Browser");
		driver = new ChromeDriver();
		wait=new WebDriverWait(driver, 30);
		driver.get("http://automationpractice.com/index.php");
		System.out.println("Navigate to Application");
		System.out.println("Maximize browser window");
		driver.manage().window().maximize();
		
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	private String getLocatorType(String locator) {
		return locator.split("]:-")[0].substring(1);
	}
	
	private String getLocatorValue(String locator) {
		return locator.split("]:-")[1];
	}
	public WebElement getElement(String locator, boolean isWaitRequired) {
		String locatorType=getLocatorType(locator);
		String locatorValue=getLocatorValue(locator);
		WebElement element=null;
		switch(locatorType.toUpperCase()) {
			case "CSS":
				if(isWaitRequired)
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				else
					element=driver.findElement(By.cssSelector(locatorValue));
				break;
			case "XPATH":
				if(isWaitRequired)
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				else
					element=driver.findElement(By.xpath(locatorValue));
				break;
			case "ID":
				if(isWaitRequired)
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				else
					element=driver.findElement(By.id(locatorValue));
				break;
			default:
				throw new InvalidLocatorStrategyException("Locator Type is invlaid");
		}
			return element;
	}
	
	public List<WebElement> getElements(String locator, boolean isWaitRequired) {
		String locatorType=getLocatorType(locator);
		String locatorValue=getLocatorValue(locator);
		List<WebElement> elements=null;
		switch(locatorType.toUpperCase()) {
			case "CSS":
				if(isWaitRequired)
					elements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
				else
					elements=driver.findElements(By.cssSelector(locatorValue));
				break;
			case "XPATH":
				if(isWaitRequired)
					elements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
				else
					elements=driver.findElements(By.xpath(locatorValue));
				break;
			case "ID":
				if(isWaitRequired)
					elements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
				else
					elements=driver.findElements(By.id(locatorValue));
				break;
			default:
				throw new InvalidLocatorStrategyException("Locator Type is invlaid");
		}
			return elements;
	}
	
	protected void clickOnElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	protected void clickOnElement(String locator, boolean isWaitRequired) {
		WebElement element=getElement(locator, isWaitRequired);
		clickOnElement(element);
	}
	
	protected void scrollToElement(WebElement element) {
		if(!element.isDisplayed()) {
			JavascriptExecutor je=(JavascriptExecutor)driver;
			je.executeScript("arguments[0].scrollIntoView(true)", element);
		}
	}
	
	protected String getElementText(String locator, boolean isWaitRequired) {
		WebElement element=getElement(locator, isWaitRequired);
		if(!element.isDisplayed())
			scrollToElement(element);
		return element.getText();
	}
	
	protected String getElementValue(String locator, boolean isWaitRequired, String value) {
		WebElement element=getElement(locator, isWaitRequired);
		if(!element.isDisplayed())
			scrollToElement(element);
		return element.getAttribute(value);
	}
	
	protected boolean isElementDisplayed(String locator, boolean isWaitRequired) {
		WebElement element=getElement(locator, isWaitRequired);
			scrollToElement(element);
		return element.isDisplayed();
	}
	protected void clearText(WebElement element) {
		element.clear();
	}
	protected void enterText(String text,String locator,boolean isWaitRequired) {
		WebElement element=getElement(locator, isWaitRequired);
		if(element.isEnabled()) {
			clearText(element);
			element.sendKeys(text);
		}
		else
			throw new ElementNotEnabled("Locator "+locator+" Element is Not enabled");
	}
	
	
	protected boolean isHeadingText(String locator,String text, boolean isWaitRequired) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text1=driver.findElement(By.cssSelector("#noSlide h1")).getText();
		System.out.println(text1);
		return wait.until(ExpectedConditions.textToBe(By.cssSelector(getLocatorValue(locator)), text));
	}
	
	protected void selectGender(String gender,String maleGenderLocator,String femaleGenderLocator,boolean isWaitRequired) {
		System.out.println("STEP-Enter Gender");
		if(gender!=null) {
			WebElement titleElement;
			titleElement=gender.equalsIgnoreCase("male")? getElement(maleGenderLocator, isWaitRequired)
					:getElement(femaleGenderLocator, isWaitRequired);
			System.out.println(titleElement.getText());
			titleElement.click();
		}
		else
			System.out.println("Gender is blank");
	}
	
	protected void getFirstName(String firstName,String locator, boolean isWaitRequired) {
		System.out.println("STEP-Enter First Name");
		if (firstName!=null)
			enterText(firstName, locator, isWaitRequired);
		else
			System.out.println("First name is blank");
	}
	
	protected void getLastName(String lastName,String locator, boolean isWaitRequired) {
		if(lastName!=null)
			enterText(lastName, locator, isWaitRequired);
		else
			System.out.println("Last name is blank");
	}
	
	protected void getPassword(String password, String locator, boolean isWaitRequired) {
		if(password!=null) 
			enterText(password, locator, isWaitRequired);
		else
			System.out.println("Password field is blank");
		
	}
	
	protected void getDays(String days,String locatordaysDropdown,String daysLocator,boolean isWaitRequired) {
		/*if(days!=null) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-days"))).click();
		Select s = new Select(driver.findElement(By.id("days")));
		s.selectByValue(days);
	}else
		System.out.println("Birth day selected from drop down");*/
		if(days!=null) {
			clickOnElement(locatordaysDropdown, isWaitRequired);;
			Select s = new Select(getElement(daysLocator, false));
			s.selectByValue(days);
		}else
			System.out.println("Birth day selected from drop down");
		
	}
	
	protected void getMonth(String month,String locatorMonthDropdown,String locatorMonth,boolean isWaitRequired) {
		
		if(month!=null) {
			clickOnElement(locatorMonthDropdown, isWaitRequired);
			Select s = new Select(getElement(locatorMonth, isWaitRequired));
			s.selectByValue(month);
			}else
				System.out.println("Month is blank");
	}
	
	protected void getYear(String year,String locatorYearDropdown,String locatorYear,boolean isWaitRequired) {
		if(year!=null) {
			clickOnElement(locatorYearDropdown, isWaitRequired);
			Select s = new Select(getElement(locatorYear, isWaitRequired));
			s.selectByValue(year);
		}else
			System.out.println("Birth year is blank");
	}
	
	protected void getCompany(String company,String locator,boolean isWaitRequired) {
		if(company!=null)
			enterText(company, locator, isWaitRequired);
		else
			System.out.println("Company Name is Blank");
	}
	
	protected void getAddress(String address,String locator,boolean isWaitRequired) {
		if(address!=null)
			enterText(address, locator, isWaitRequired);
		else
			System.out.println("Address is blank");
	}
	
	protected void getCity(String city,String locator,boolean isWaitRequired) {
		if(city!=null)
			enterText(city, locator, isWaitRequired);
		else
			System.out.println("City Name is Blank");
	}
	
	protected void getState(String state,String locatorSateDropdown,String locatorState,boolean isWaitRequired) {
		if(state!=null) {
			clickOnElement(locatorSateDropdown, isWaitRequired);
			Select s = new Select(getElement(locatorState, isWaitRequired));
			s.selectByVisibleText(state);
		}else
			System.out.println("State is Blank");
	}
	
	protected void getPostCode(String postCode,String locator,boolean isWaitRequired) {
		if(postCode!=null)
			enterText(postCode, locator, isWaitRequired);
		else
			System.out.println("Postcode is blank");
	}
	
	protected void getOther(String other,String locator,boolean isWaitRequired) {
		if(other!=null)
			enterText(other, locator, isWaitRequired);
		else
			System.out.println("Other information is blank");
	}
	
	protected void getPhone(String phone,String locator,boolean isWaitRequired) {
		if(phone!=null)
			enterText(phone, locator, isWaitRequired);
		else
			System.out.println("Home mobile number is blank");
	}
	
	protected void getMobile(String mobile,String locator,boolean isWaitRequired) {
		if(mobile!=null) 
			enterText(mobile, locator, isWaitRequired);
		else
			System.out.println("Mobile number is blank");
	}
	
	protected MyProfilePage clickOnRegistration(String locator,boolean isWaitRequired) {
		clickOnElement(locator, isWaitRequired);
		System.out.println("Details Registered in Application");
		return MyProfilePage.getInstance();
	}
	
	protected List<String> getErrorMessage(String errorLocator, String errorTextLocator,boolean isWaitRequired ) {
		List<WebElement> listOfError=getElements(errorLocator, isWaitRequired);
		List<String> listOfErrorText=new ArrayList<String>();
		listOfErrorText.add(getElementText(errorTextLocator, isWaitRequired));
		for(WebElement element:listOfError) {
			listOfErrorText.add(element.getText());
		}
		return listOfErrorText;
	}
	
	protected String getHeaderText(String locator,boolean isWaitRequired) {
		String headerText=getElementText(locator, isWaitRequired);
		return headerText;
	}
	
	protected String getUserFullName(String locator,boolean isWaitRequired) {
		String fullName=getElementText(locator, isWaitRequired);
		return fullName;
	}
	
	protected ProductCategoryPage selectSection(String sectionName,String locatorWomen,String locatorAdress,String locatorTshirt,boolean isWaitRequired) {
		WebElement ele = null;
		switch (sectionName.toUpperCase()) {
		case "WOMEN":
			ele = getElement(locatorWomen, isWaitRequired);
			break;
		case "DRESSES":
			ele = getElement(locatorAdress, isWaitRequired);
			break;

		case "T-SHIRTS":
			ele = getElement(locatorTshirt, isWaitRequired);
			break;
		default:
			break;
		}
		ele.click();
		return ProductCategoryPage.getInstance();
	}
	
	protected List<WebElement> getProductList(String locator, boolean isWaitRequired) {
		List<WebElement> productList=getElements(locator, isWaitRequired);
		return productList;
	}

	protected ProductDetailsPage selectFirstAvailableProductInList(List<WebElement> productList) {
		if (productList.size() > 0) {
			productList.get(0).click();
		} else {
			throw new ProductNotFoundException("Product Not displayed");
		}
		System.out.println("STEP - First product selected");
		return ProductDetailsPage.getInstance();
	}

	public ProductDetailsPojo capatureProductDetails(ProductDetailsPojo productDetailsPojo,String locatorProductName,
			String locatorUnitPrice, String locatorQuantity,String locatorSize, String locatorDes,String locatorColor,
			boolean isWaitRequired) {
		String productName = getElementText(locatorProductName, isWaitRequired);
		String unitPrice = getElementText(locatorUnitPrice, isWaitRequired);
		unitPrice = unitPrice.substring(1);
		String quantity = getElementValue(locatorQuantity, isWaitRequired,"value");
		String size = getElementText(locatorSize, isWaitRequired);
		String des = getElementText(locatorDes, isWaitRequired);
		String color = getElementValue(locatorColor, isWaitRequired, "title");
		productDetailsPojo.setProductName(productName);
		productDetailsPojo.setUnitRatePrice(unitPrice);
		productDetailsPojo.setQuantity(quantity);
		productDetailsPojo.setSize(size);
		productDetailsPojo.setProductDescription(des);
		productDetailsPojo.setColor(color);
		return productDetailsPojo;
	}

	public void setQuantity(String numOfQuantity, String locatorQuantity,boolean isWaitRequired) {
		enterText(numOfQuantity, locatorQuantity, isWaitRequired);
		System.out.println("STEP - Quantity is set");
	}

	public void setSize(String size, String locatorSetSize, boolean isWaitRequired) {
		Select select = new Select(getElement(locatorSetSize, isWaitRequired));
		select.selectByVisibleText(size);
		System.out.println("STEP - Size selected");
	}

	public void selectColour(String color, String locatorBlue, String locatorOrange,boolean isWaitRequired ) {
		switch (color.toUpperCase()) {
		case "BLUE":
			clickOnElement(locatorBlue, isWaitRequired);
			break;
		case "ORANGE":
			clickOnElement(locatorOrange, isWaitRequired);
			break;
		default:
			break;
		}
	}

	public void clickOnAddToCart(String locator, boolean isWaitRequired) {
		JavascriptExecutor je=(JavascriptExecutor)driver;
		je.executeScript("arguments[0].scrollIntoView(true)", getElement(locator, isWaitRequired));
		clickOnElement(locator, isWaitRequired);
	}

	public String verifyProductName(String locator, boolean isWaitRequired) {
		String productName = getElementText(locator, isWaitRequired);
		return productName;
	}

	public String verifyQuantity(String locator, boolean isWaitRequired) {
		String quantity = getElementText(locator, isWaitRequired);
		return quantity;
	}

	public String verifySizeAndColor(String locator, boolean isWaitRequired) {
		String sizeAndColor = getElementText(locator, isWaitRequired);;
		return sizeAndColor;
	}

	public String verifyTotalPrice(String locator, boolean isWaitRequired) {
		String totalPrice = getElementText(locator, isWaitRequired).substring(1);
		return totalPrice;
	}

	public ShoppingSummaryPage clickOnProceedToCheckout(String locator, boolean isWaitRequired) {
		clickOnElement(locator, isWaitRequired);
		System.out.println("STEP - Click Proceed to checkout on Product detail page");
		return ShoppingSummaryPage.getInstance();
	}
	
	protected String getProductName(String locator,boolean isWaitRequired ) {
		return getElementText(locator, isWaitRequired);
	}

	public String getColorAndSize(String locator,boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired);
	}

	public String getProductPrice(String locator,boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired).substring(1);
	}

	public String getTotalPrice(String locator,boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired).substring(1);
	}

	public CheckoutAddressPage clickOnProceedToCheckoutShoppingSummary(String locator,boolean isWaitRequired) {
		System.out.println("STEP - Click Proceed to checkout on Shopping Summary page");
		clickOnElement(locator, isWaitRequired);
		return CheckoutAddressPage.getInstance();
	}

	public ProductDetailsPojo capatureShippingCharges(ProductDetailsPojo productDetailsPojo,String locator,boolean isWaitRequired) {
		String shippingCharge = getElementText(locator, isWaitRequired).substring(1);
		productDetailsPojo.setTotalShipping(shippingCharge);
		return productDetailsPojo;
	}

	public ProductDetailsPojo capatureFinalPrice(ProductDetailsPojo productDetailsPojo,String locator,boolean isWaitRequired) {
		String finalPrice = getElementText(locator, isWaitRequired).substring(1);
		productDetailsPojo.setTotalPrice(finalPrice);
		return productDetailsPojo;
	}
	protected List<WebElement> visibilityOfAllElement(String locator,boolean isWaitRequired) {
		List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector(locator)));
		return eleList;
	}
	public List<String> getDeliveryAddress(String locator, boolean isWaitRequired) {
		List<WebElement> eleList=getElements(locator, isWaitRequired);
		List<String> deliveryList = new ArrayList<>();
		for (WebElement ele : eleList) {
			deliveryList.add(ele.getText());
		}
		return deliveryList;
	}

	public List<String> getBilingAddress(String locator, boolean isWaitRequired) {
		List<WebElement> eleList=getElements(locator, isWaitRequired);
		List<String> billingList = new ArrayList<>();
		for (WebElement ele : eleList) {
			billingList.add(ele.getText());
		}
		return billingList;
	}
	
	public CheckoutShippingPage clickOnProcessToCheckoutAddressPage(String locator, boolean isWaitRequired) {
		System.out.println("STEP - Click Proceed to checkout on Address page");
		clickOnElement(locator, isWaitRequired);
		return CheckoutShippingPage.getInstance();
	}
	
	public String capatureShippingCharges(String locator, boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired).substring(1);
	}

	public void clickOnTermAndCondition(String locator, boolean isWaitRequired) {
		clickOnElement(locator, isWaitRequired);
	}

	public CheckoutPaymentPage clickOnProcessToCheckout(String locator, boolean isWaitRequired) {
		System.out.println("STEP - Click Proceed to checkout on Payment page");
		clickOnElement(locator, isWaitRequired);
		return CheckoutPaymentPage.getInstance();
	}
	
	public String getToalPricePayment(String locator, boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired).substring(1);
	}

	public OrderSummaryPage paymentBy(String paymentType,String locatorCheck,String locatorWire, boolean isWaitRequired) {
		switch (paymentType.toUpperCase()) {
		case "CHECK":
					clickOnElement(locatorCheck, isWaitRequired);
			break;

		case "BANK WIRE":
						clickOnElement(locatorWire, isWaitRequired);
			break;
		default:
			break;
		}
		return OrderSummaryPage.getInstance();
	}
	
	public String captureAmount(String locator, boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired).substring(1);
	}

	public OrderConfirmationPage clickOnConfirmOrder(String locator, boolean isWaitRequired) {
		System.out.println("STEP - Click Proceed to checkout on Order confirmation page");
		clickOnElement(locator, isWaitRequired);
		return OrderConfirmationPage.getInstance();
	}
	
	public String capatureTotalAmount(String locator, boolean isWaitRequired) {
		return getElementText(locator, isWaitRequired).substring(1);
	}

	public OrderHistoryPage clickOnBackToOrder(String locator, boolean isWaitRequired) {
		System.out.println("STEP - Click Proceed to checkout on Order History page");
		clickOnElement(locator, isWaitRequired);
		return OrderHistoryPage.getInstance();
	}
	public String getPageTitleOrderHistory() {
		return driver.getTitle();
	}
	public static void captureScreenshot(ITestResult result) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File file=ts.getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file, new File("./src/screenShot/"+result.getName()+".jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
