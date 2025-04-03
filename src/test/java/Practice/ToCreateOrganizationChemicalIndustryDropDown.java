package Practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToCreateOrganizationChemicalIndustryDropDown {

	public static void main(String[] args) {
	
        //Step no 1:- lanuch the Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
					
		// step 2 :- Login to application with valid
		driver.get("http://localhost:8888/");	
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("123");
		driver.findElement(By.id("submitButton")).click();
		
		// Step 3 :- Navigate to contacts link
		driver.findElement(By.linkText("Organizations")).click();

	    //Step no 4:- Click on Create Organization look up Image
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

	    //Step no 5:- Create Organization with Mandatory fields
	    Random r = new Random();
        int random = r.nextInt(1000);
	    driver.findElement(By.name("accountname")).sendKeys("qspiders"+random);
	    WebElement industryEle = driver.findElement(By.name("industry"));
	    Select dropdown = new Select(industryEle);
	    dropdown.selectByValue("Chemicals");
	    
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
         String dropdownEle = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(dropdownEle.contains("spiders"+random)) {
            System.out.println(dropdownEle+"------- Passed ");
        }else {
            System.out.println(dropdownEle+"------- Failed");
        }

        //Step no 7:- Logout from an application
        WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		// step 8 Close the window
		driver.quit();
		
		




	}

}
