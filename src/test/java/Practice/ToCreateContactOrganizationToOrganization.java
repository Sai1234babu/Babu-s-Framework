package Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContactOrganizationToOrganization {

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
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4 :- Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		// Step 5 :- Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Saibabu");
		
		//STEP NO 6:-Select the Organization from Organization look up image
		driver.findElement(By.xpath("//img[@title='Select']")).click();
	    String parentid = driver.getWindowHandle();
	    System.out.println(parentid);
	    driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
	    Set<String> Childwindow = driver.getWindowHandles();
	    for (String handle : Childwindow) {
	    	driver.switchTo().window(handle);	
	    }
        System.out.println(Childwindow);
        driver.close();
     
	    driver.switchTo().window(parentid);
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        String dropdownEle = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(dropdownEle.contains("Saibabu")) {
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
