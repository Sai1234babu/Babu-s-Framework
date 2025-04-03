package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ToReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		
		// Create obj of file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
		//Create obj of property file
		Properties prop = new Properties();
		
		//call methods
		prop.load(fis);
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");
		
		System.out.println("url");
		System.out.println("username");
		System.out.println("password");
		System.out.println("browser");
		
		

	}

}
