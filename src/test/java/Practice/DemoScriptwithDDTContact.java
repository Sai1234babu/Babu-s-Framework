package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptwithDDTContact {

	public static void main(String[] args) throws IOException   {
		// TO READ DATA FROM PRORPERTYFILE
		FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties prop = new Properties();
        prop.load(pfis);
        String BROWSER = prop.getProperty("browser");
        String URL = prop.getProperty("url");
        String USERNAME = prop.getProperty("username");
        String PASSWORD = prop.getProperty("password");
        
        // To Read Data From Excelfile
        FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\testDataM8.xlsx");
        Workbook wb = WorkbookFactory.create(efis);
        String LASTNAME = wb.getSheet("contact").getRow(1).getCell(2).toString();
        
        // step 1 :- Launch Browswer
        WebDriver driver = null;
        if(BROWSER.equals("chrome")) {
        	driver = new ChromeDriver();
        }else if(BROWSER.equals("edge")) {
        	driver = new EdgeDriver();
        }else if(BROWSER.equals("firefox")) {
        	driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        
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
				
		// step 6 :-  save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(name.contains(LASTNAME)) {
		System.out.println(name + "---Passed");
		}else {
		System.out.println(name + "---Failed");
				 }
				 
	    // step 7 :- Logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();
				 
		// step 8 :- close the browser
		driver.quit();

		
	}

}
