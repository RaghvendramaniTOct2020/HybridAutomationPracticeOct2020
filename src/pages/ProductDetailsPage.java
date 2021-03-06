package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import pojo.ProductDetailsPojo;
import util.PropertiesFileReader;

public class ProductDetailsPage extends PreDefinedAction{
	
	private PropertiesFileReader productDetailsPageProperties;
	private static ProductDetailsPage productDetailsPage;
	
	private ProductDetailsPage () {
		productDetailsPageProperties=new PropertiesFileReader(ConfigFilePath.PRODUCT_DETAILS_PAGE_PROPERTIES);
	}
	
	public static ProductDetailsPage getInstance() {
		if(productDetailsPage==null)
			productDetailsPage=new ProductDetailsPage();
		return productDetailsPage;
	}
	
	
	public ProductDetailsPojo capatureProductDetails(ProductDetailsPojo productDetailsPojo) {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		String productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
		String unitPrice = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#our_price_display"))).getText();
		unitPrice = unitPrice.substring(1);

		String quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#quantity_wanted")))
				.getAttribute("value");

		String size = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uniform-group_1 span")))
				.getText();

		String des = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#short_description_content p")))
				.getText();

		String color = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("#color_to_pick_list li[class='selected'] a")))
				.getAttribute("title");

		productDetailsPojo.setProductName(productName);
		productDetailsPojo.setUnitRatePrice(unitPrice);
		productDetailsPojo.setQuantity(quantity);
		productDetailsPojo.setSize(size);
		productDetailsPojo.setProductDescription(des);
		productDetailsPojo.setColor(color);

		return productDetailsPojo;*/
		return capatureProductDetails(productDetailsPojo, productDetailsPageProperties.getValue("productName"), productDetailsPageProperties.getValue("unitPrice"), 
				productDetailsPageProperties.getValue("quantity"),productDetailsPageProperties.getValue("size"), 
				productDetailsPageProperties.getValue("des"), productDetailsPageProperties.getValue("color"), true);
	}

	public void setQuantity(String numOfQuantity) {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#quantity_wanted"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#quantity_wanted")))
				.sendKeys(numOfQuantity);
		System.out.println("STEP - Quantity is set");*/
		setQuantity(numOfQuantity, productDetailsPageProperties.getValue("quantity"), true);
	}

	public void setSize(String size) {
		/*Select select = new Select(driver.findElement(By.id("group_1")));
		select.selectByVisibleText(size);
		System.out.println("STEP - Size selected");*/
		setSize(size,productDetailsPageProperties.getValue("setSize"), false);
	}

	public void selectColour(String color) {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		switch (color.toUpperCase()) {
		case "BLUE":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("color_14"))).click();
			break;
		case "ORANGE":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("color_13"))).click();
			break;
		default:
			break;
		}*/
		selectColour(color, productDetailsPageProperties.getValue("blueColor"), productDetailsPageProperties.getValue("orangeColor"), false);
	}

	public void clickOnAddToCart() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#add_to_cart button"))).click();*/
		clickOnAddToCart(productDetailsPageProperties.getValue("clickOnAddToCart"), true);
	}

	public String verifyProductName() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		String productName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_title"))).getText();
		return productName;*/
		return verifyProductName(productDetailsPageProperties.getValue("verifyProductName"), true);
	}

	public String verifyQuantity() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		String quantity = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_quantity"))).getText();
		return quantity;*/
		return verifyQuantity(productDetailsPageProperties.getValue("verifyQuantity"), true);
	}

	public String verifySizeAndColor() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		String sizeAndColor = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_attributes"))).getText();
		return sizeAndColor;*/
		return verifySizeAndColor(productDetailsPageProperties.getValue("verifySizeAndColor"), true);
	}

	public String verifyTotalPrice() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		String totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_price")))
				.getText().substring(1);
		return totalPrice;*/
		return verifyTotalPrice(productDetailsPageProperties.getValue("verifyTotalPrice"), true);
	}

	public ShoppingSummaryPage clickOnProceedToCheckout() {
		/*driver.findElement(By.xpath("//a/span[contains(text(), 'Proceed to checkout')]")).click();
		System.out.println("STEP - Click Proceed to checkout on Product detail page");
		return new ShoppingSummaryPage();*/
		return clickOnProceedToCheckout(productDetailsPageProperties.getValue("proceedCheckOut"), true);
	}
	
	/* private String getSelectedOption() {
		 String size = "";
		 Select s = new Select(driver.findElement(By.id("group_1"))); 
		 List<WebElement> list =s.getOptions(); 
		 for (WebElement ele : list) 
		 { 
			 if (ele.isSelected()) 
				 System.out.println(ele.getText()); size = ele.getAttribute("title"); 
		 }
		 return size; 
	}*/
}
