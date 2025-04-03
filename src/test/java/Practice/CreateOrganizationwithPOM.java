package Practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.webDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationPage;

public class CreateOrganizationwithPOM {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		webDriverUtility wutil = new webDriverUtility();

		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// Read data from Excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("organization", 1, 2);

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
		hp.getOrganizationslinks().click();

		// Step 4 :- Click on create Organization look image
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateorganizationpagepulse().click();

		// Step 5 :- File in the dtails
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getorgname().sendKeys(LASTNAME);
		// step 6 :- Save and verify
		cop.getSaveButton().click();
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String name = oip.getOutputorginf().getText();
		if (name.contains(LASTNAME)) {
			System.out.println(name + "---Passed");
		} else {
			System.out.println(name + "---Failed");
		}

		// Step 7 :- Logout
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getSignoutlink().click();

		// Step 8 :- Close
		driver.quit();

	}

}
