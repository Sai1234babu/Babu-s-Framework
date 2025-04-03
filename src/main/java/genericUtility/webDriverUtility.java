package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class webDriverUtility {
	/**
	 * This method is used to maximize the browser provided driver
	 * 
	 * @param driver
	 */
	public void tomaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 *  This method is used to minimize
	 *  
	 *  @param driver
	 */
	public void tominimize(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This  method  will utill the element loaded in webpage (implicit wait)
	 * 
	 * @param driver
	 */
	public void towaitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	/**
	 * This method will wait util element is clickable provided driver and element
	 * @param driver
	 * @param element
	 */
	public void towaitUtilElementClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will wait util element is visible provided driver and element
	 * @param driver
	 * @param element
	 */
	public void towaitUtilVisblityofElement(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used to handle dropdown using index
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method is used to handle dropdown using value
	 * @param element 
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method is used to handle dropdown using text
	 * @param text
	 * @param element
	 */
	
	public void toHandleDropdown(String text,WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	/**
	 * This method is used to perform move to element provided driver and element
	 * @param driver
	 * @param element
	 */
	public void  toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * This method is used to perform to right click provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	/**
	 * This method is used to perform to double click provided driver and element
	 * @param driver
	 * @param elemenet
	 */
	public void toDoubleClick(WebDriver driver, WebElement elemenet) {
		Actions action = new Actions(driver);
		action.doubleClick(elemenet).perform();
	}
	/**
	 * This method is used to perform drag and drop provided driver and wedelement
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}
	/**
	 * This method is used  to handle frame using webelement
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to handle frame using id or name
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}
	/**
	 * This method is used to handle frame using webelement 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * This method is used toswitch the driver control back to parent window
	 * @param driver
	 */
	public void toSwitchDriverControlBack(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to handle to alert popup by accepting it
	 * @param driver
	 */
	public void toSwitchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * This is method used to handle to alert popup by dismissing it
	 * @param driver
	 */
	public void toSwitchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This is method used to capture text in alertpopup and accept
	 * @param driver
	 * @return
	 */
	public String toSwitchToAlertAndCaptureMessage(WebDriver driver) {
		Alert alertpopup = driver.switchTo().alert();
		String message = alertpopup.getText();
		alertpopup.accept();
		return message;
	}
	/**
	 *  This method is used to take screenshot provided driver and screenshot name
	 * @param driver
	 * @return
	 */
	public String toTakeScreenShot(WebDriver driver, String screenshotname) throws IOException{
		  TakesScreenshot ts = (TakesScreenshot)driver;
		  File temp = ts.getScreenshotAs(OutputType.FILE);
		  File src = new File("./errorShots/" + screenshotname + ".png");
		  FileHandler.copy(temp, src); 
		  return src.getAbsolutePath();
	}
	/**
	 * This methoid is used to switch the driver control to window
	 * 
	 * @param driver
	 * @param partialTiltle
	 */
	public void toSwitchWindow(WebDriver driver, String partialTiltle) {
		
		Set<String> allIds = driver.getWindowHandles();
		for(String id : allIds) {
			String title = driver.switchTo().window(id).getTitle();
			if(title.contains(partialTiltle)) {
				break;
			}
		}
	
				
	}
}
