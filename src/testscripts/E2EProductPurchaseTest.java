package testscripts;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthenticationPage;
import pages.CheckoutAddressPage;
import pages.CheckoutPaymentPage;
import pages.CheckoutShippingPage;
import pages.HomePage;
import pages.MyProfilePage;
import pages.OrderConfirmationPage;
import pages.OrderHistoryPage;
import pages.OrderSummaryPage;
import pages.ProductCategoryPage;
import pages.ProductDetailsPage;
import pages.ShoppingSummaryPage;
import pojo.ProductDetailsPojo;

public class E2EProductPurchaseTest extends TestBase{
	
	@Test
	public void e2eProductPurchase() throws IOException, InterruptedException {
	HomePage homePage =HomePage.getInstance();
	System.out.println("STEP-Click on Sign in");
	AuthenticationPage authenticationPage = homePage.clickOnSignIn();
	File file=new File(".\\resources\\TestData\\loginData.properties");
	FileInputStream inputStream=new FileInputStream(file); 
	Properties prop=new Properties();
	System.out.println("Load Prprty file in memory");
	prop.load(inputStream);
	System.out.println("STEP-Engter email and Password");
	MyProfilePage myProfilePage = authenticationPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
	System.out.println("STEP-Select Product Category");
	ProductCategoryPage productCategoryPage = myProfilePage.selectSection(prop.getProperty("productCategory"));
	List<WebElement> productList = productCategoryPage.getProductList();
	System.out.println("STEP-Verify number of products displayed");
	SoftAssert softAssert = new SoftAssert();
	softAssert.assertTrue(productList.size() >= 1);
	ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
	System.out.println("STEP-Select First Product");
	ProductDetailsPage productDetailsPage = productCategoryPage.selectFirstAvailableProduct(productList);
	System.out.println("STEP-Set Product details");
	productDetailsPage.setQuantity("10");
	productDetailsPage.setSize("L");
	productDetailsPage.selectColour("Blue");
	productDetailsPojo = productDetailsPage.capatureProductDetails(productDetailsPojo);
	System.out.println("STEP-Click on Add to Cart");
	productDetailsPage.clickOnAddToCart();
	System.out.println("STEP-Verify Product details");
	Assert.assertEquals(productDetailsPojo.getProductName(), productDetailsPage.verifyProductName());
	Assert.assertEquals(productDetailsPojo.getQuantity(), productDetailsPage.verifyQuantity());
	Assert.assertEquals(productDetailsPojo.getColor() + ", " + productDetailsPojo.getSize(),
			productDetailsPage.verifySizeAndColor());

	String qun = productDetailsPojo.getQuantity();
	String price = productDetailsPojo.getUnitRatePrice();

	double totalPrice = Double.parseDouble(price) * Integer.parseInt(qun);
	productDetailsPojo.setTotalproductPrice(String.format("%.2f", totalPrice));

	Assert.assertEquals(productDetailsPojo.getTotalproductPrice(), productDetailsPage.verifyTotalPrice(),
			"In cprrect value in quantity & price on Product details page");
	System.out.println("STEP-Click on ProceedToCheckout");
	ShoppingSummaryPage shoppingSummaryPage = productDetailsPage.clickOnProceedToCheckout();

	System.out.println("STEP-Verify details on Shopping summary page");
	Assert.assertEquals(productDetailsPojo.getProductName(), shoppingSummaryPage.getProductName(),
			"Product Name mismatch on summary Page");
	Assert.assertEquals(productDetailsPojo.getUnitRatePrice(), shoppingSummaryPage.getProductPrice(),
			"Product unit rate mismatch on summary Page");
	Assert.assertEquals(productDetailsPojo.getTotalproductPrice(), shoppingSummaryPage.getTotalPrice(),
			"Product price mismatch on summary Page");

	productDetailsPojo = shoppingSummaryPage.capatureShippingCharges(productDetailsPojo);
	productDetailsPojo = shoppingSummaryPage.capatureFinalPrice(productDetailsPojo);

	System.out.println("STEP-Navigate to checkout Address page");
	CheckoutAddressPage checkoutAddressPage = shoppingSummaryPage.clickOnProceedToCheckout();
	List<String> billAdddress = checkoutAddressPage.getBilingAddress();
	List<String> deliveryAdddress = checkoutAddressPage.getDeliveryAddress();
	System.out.println("STEP-Verify Billing Address and Delivery Address");
	Assert.assertEquals(billAdddress, deliveryAdddress, "Billing Address & Delivery Address is not matching");

	System.out.println("STEP-Navigate to checkout Shiping page");
	CheckoutShippingPage checkoutShippingPage = checkoutAddressPage.clickOnProcessToCheckout();
	System.out.println("STEP-Verify Shipping charges");
	Assert.assertEquals(productDetailsPojo.getTotalShipping(), checkoutShippingPage.capatureShippingCharges());
	checkoutShippingPage.clickOnTermAndCondition();

	System.out.println("STEP-Navigate to checkout Payment page");
	CheckoutPaymentPage checkoutPaymentPage = checkoutShippingPage.clickOnProcessToCheckout();

	double totalPriceWithShipping = Double.parseDouble(productDetailsPojo.getTotalPrice())
			+ Double.parseDouble(productDetailsPojo.getTotalShipping());

	productDetailsPojo.setTotalproductPrice(String.format("%.2f", totalPriceWithShipping));
	System.out.println("STEP-Verify Total price on Checkout Payment page");
	Assert.assertEquals(productDetailsPojo.getTotalPrice(), checkoutPaymentPage.getToalPrice());

	System.out.println("STEP-Select Payment By option");
	OrderSummaryPage orderSummaryPage = checkoutPaymentPage.paymentBy("Check");
	
	System.out.println("STEP-Verify Total Price");
	Assert.assertEquals(productDetailsPojo.getTotalPrice(), orderSummaryPage.captureAmount());

	System.out.println("STEP-Click on confirm my order");
	OrderConfirmationPage orderConfirmationPage = orderSummaryPage.clickOnConfirmOrder();
	
	System.out.println("STEP-Verify Total Price");
	Assert.assertEquals(productDetailsPojo.getTotalPrice(), orderConfirmationPage.capatureTotalAmount());

	System.out.println("STEP-Go back to Orders page");
	OrderHistoryPage orderHistoryPage = orderConfirmationPage.clickOnBackToOrder();
	Assert.assertEquals("Order history - My Store", orderHistoryPage.getPageTitle());
	}
}
