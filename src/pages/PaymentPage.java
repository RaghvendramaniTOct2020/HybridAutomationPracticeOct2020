package pages;

import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class PaymentPage {
	private PropertiesFileReader paymentPageProperties;
	private static PaymentPage paymentPage;
	
	private PaymentPage () {
		paymentPageProperties=new PropertiesFileReader(ConfigFilePath.PAYMENT_PAGE_PROPERTIES);
	}
	
	public static PaymentPage getInstance() {
		if(paymentPage==null)
			paymentPage=new PaymentPage();
		return paymentPage;
	}
}
