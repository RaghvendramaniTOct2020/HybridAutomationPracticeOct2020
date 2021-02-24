package pages;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import pojo.ProductDetailsPojo;
import util.PropertiesFileReader;
public class ShoppingSummaryPage extends PreDefinedAction {
	private PropertiesFileReader shoppingSummaryPageProperties;
	private static ShoppingSummaryPage shoppingSummaryPage;
	
	private ShoppingSummaryPage () {
		shoppingSummaryPageProperties=new PropertiesFileReader(ConfigFilePath.SHOPPING_SUMMARY_PAGE_PROPERTIES);
	}
	
	public static ShoppingSummaryPage getInstance() {
		if(shoppingSummaryPage==null)
			shoppingSummaryPage=new ShoppingSummaryPage();
		return shoppingSummaryPage;
	}
	public String getProductName() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("#order-detail-content p[class='product-name'] a")))
				.getText();*/
		return getProductName(shoppingSummaryPageProperties.getValue("productName"), true);
	}

	public String getColorAndSize() {
		/*return driver.findElement(By.cssSelector(".cart_item .cart_description small:nth-child(3)>a")).getText();*/
		return getColorAndSize(shoppingSummaryPageProperties.getValue("colorAndSize"), true);
	}

	public String getProductPrice() {
		/*return driver.findElement(By.cssSelector(".cart_item .cart_unit span>span")).getText().substring(1);*/
		return getProductPrice(shoppingSummaryPageProperties.getValue("ProductPrice"),true);
	}

	public String getTotalPrice() {
		/*return driver.findElement(By.cssSelector(".cart_item .cart_total>span")).getText().substring(1);*/
		return getTotalPrice(shoppingSummaryPageProperties.getValue("TotalPrice"), true);
	}

	public CheckoutAddressPage clickOnProceedToCheckout() {
		/*driver.findElement(By.xpath("//div[@id='center_column']//a/span[contains(text(), 'Proceed to checkout')]"))
				.click();
		System.out.println("STEP - Click Proceed to checkout on Shopping Summary page");
		return new CheckoutAddressPage();*/
		return clickOnProceedToCheckoutShoppingSummary(shoppingSummaryPageProperties.getValue("clickOnProceedToCheckout"), true);
	}

	public ProductDetailsPojo capatureShippingCharges(ProductDetailsPojo productDetailsPojo) {
		/*String shippingCharge = driver.findElement(By.id("total_shipping")).getText().substring(1);
		productDetailsPojo.setTotalShipping(shippingCharge);
		return productDetailsPojo;*/
		return capatureShippingCharges(productDetailsPojo, shoppingSummaryPageProperties.getValue("capatureShippingCharges"), true);
	}

	public ProductDetailsPojo capatureFinalPrice(ProductDetailsPojo productDetailsPojo) {
		/*String finalPrice = driver.findElement(By.id("total_price")).getText().substring(1);
		productDetailsPojo.setTotalPrice(finalPrice);
		return productDetailsPojo;*/
		return capatureFinalPrice(productDetailsPojo, shoppingSummaryPageProperties.getValue("capatureFinalPrice"), true);
	}
}
