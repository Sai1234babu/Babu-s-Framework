package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	webDriverUtility wutil = new webDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver;//Listeners
	@BeforeSuite
	public void beforeSuiteConfiguration() {
		Reporter.log("---DataBase connection Estabhlished");
	}
//	@Parameters("browser")
//	@BeforeTest
	@BeforeClass(groups = {"somke", "Regression"})
	public void beforeClassConfiguration(/*String BROWSER*/) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver=driver;//listeners
		wutil.tomaximize(driver);
		wutil.towaitForElement(driver);
		driver.get(URL);
		}
	@BeforeMethod(groups = {"somke","Regression"})
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfiled().sendKeys(USERNAME);
		lp.getPasswordTextfiled().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("LOGGED IN SUCCESSFULLY",false);
	}
	
	@AfterMethod(groups = {"somke","Regression"})
	public void afterMethodCofiguation() {
		HomePage hp = new HomePage(driver);
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getSignoutlink().click();
		Reporter.log("Logged out Succwssfully");
	}
	
	@AfterClass(groups = {"somke","Regression"})
	public void afterClassConfiguration() {
		Reporter.log("Browser got closed Successfully",true);
		driver.quit();
	}
	
	@AfterSuite(groups = {"somke","Regression"})
	public void afterSuiteConfiguration() {
		Reporter.log("DataBase Connection Disconnected",true);
	}
	
}
