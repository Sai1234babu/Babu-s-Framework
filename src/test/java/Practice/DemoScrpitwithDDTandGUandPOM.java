package Practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.webDriverUtility;

import objectRepository.ContactsInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactsPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class DemoScrpitwithDDTandGUandPOM {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		webDriverUtility wutil = new webDriverUtility();
	    
		// Read data from propertyfile
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		// Read data from excelfile
		String LASTNAME = eutil.toReadDataFromExcelFile("contact", 1, 2);
		
		// step 1 :- Launch Browswer
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.tomaximize(driver);
		wutil.towaitForElement(driver);
		
		// step 2 :- Login to application with valid credential
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfiled().sendKeys(USERNAME);
		lp.getPasswordTextfiled().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		// Step 3 :- click on contact link
		HomePage hp = new HomePage(driver);
		hp.getContactslinks().click();
		
		// Step 4 :- Click on create contact look image
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactIcon().click();
		
		// Step 5 :- File in the dtails
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ccp.getLastnameTextfiled().sendKeys(LASTNAME);
		
		// sTEP 6 :- Save and verify
		ccp.getSaveButton().click();
		ContactsInformationPage cip = new ContactsInformationPage(driver);
		String name = cip.getContactsHeader().getText();
		if(name.contains(LASTNAME)) {
			System.out.println(name + "---Passed");
		}else {
			System.out.println(name + "---Failed");
		}
		
		// Step 7 :- Logout
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getSignoutlink().click();
		
		// Step 8 :- Close
		driver.quit();
		
	}

}
