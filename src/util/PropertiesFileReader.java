package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	private Properties prop;
	
	public PropertiesFileReader(String filePath){
		File file=new File(filePath);
		try {
			FileInputStream inputStream=new FileInputStream(file);
			prop=new Properties();
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public String getValue(String key) {
		return prop.getProperty(key);
	}
}
