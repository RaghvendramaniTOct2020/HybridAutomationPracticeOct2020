package pages;
import java.util.List;
import org.openqa.selenium.WebElement;
import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class ProductCategoryPage extends PreDefinedAction {
	private PropertiesFileReader productCategoryPagePageProperties;
	private static ProductCategoryPage productCategoryPage;
	
	private ProductCategoryPage () {
		productCategoryPagePageProperties=new PropertiesFileReader(ConfigFilePath.PRODUCT_CATEGORY_PAGE_PROPERTIES);
	}
	
	public static ProductCategoryPage getInstance() {
		if(productCategoryPage==null)
			productCategoryPage=new ProductCategoryPage();
		return productCategoryPage;
	}
	
	
	public List<WebElement> getProductList() {
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> productList = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("#center_column ul[class^='product_list'] h5 a")));
		return productList;*/
		return getProductList(productCategoryPagePageProperties.getValue("listProductCategory"), true);
	}

	public ProductDetailsPage selectFirstAvailableProduct(List<WebElement> productList) {
		/*if (productList.size() > 0) {
			productList.get(0).click();
		} else {
			throw new ProductNotFoundException("Product Not displayed");
		}
		System.out.println("STEP - First product selected");
		return new ProductDetailsPage();*/
			return selectFirstAvailableProductInList(productList);
	}
}
