package pages;

import base.PreDefinedAction;
import constantPath.ConfigFilePath;
import util.PropertiesFileReader;

public class OrderHistoryPage extends PreDefinedAction {
	private PropertiesFileReader orderHistoryPageProperties;
	private static OrderHistoryPage orderHistoryPage;
	
	private OrderHistoryPage () {
		orderHistoryPageProperties=new PropertiesFileReader(ConfigFilePath.ORDER_HISTORY_PAGE_PROPERTIES);
	}
	
	public static OrderHistoryPage getInstance() {
		if(orderHistoryPage==null)
			orderHistoryPage=new OrderHistoryPage();
		return orderHistoryPage;
	}
	public String getPageTitle() {
		return getPageTitleOrderHistory();
	}
}
