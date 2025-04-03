package Practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.webDriverUtility;

public class DemoScriptwithDDTandGU {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		webDriverUtility wutil = new webDriverUtility();

		// Read data from propertyfile
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// Read data from Excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contact", 1, 2);

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

		// Step 2 :- Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step 3 :- Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// step 4 :- click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 5 :- create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// step 6 :- save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains(LASTNAME)) {
			System.out.println(name + "---Passed");
		} else {
			System.out.println(name + "---Failed");

		}

		// step 7 :- Logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutEle);
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8 :- close the browser
		driver.quit();

	}

}
