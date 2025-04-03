package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage {
 
	public CreateContactsPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextfiled;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath ="//input[@name='account_name']/following-sibling::img")
	private WebElement childwindow;
	
	public WebElement getChildwindow() {
		return childwindow;
	}

	public WebElement getLastnameTextfiled() {
		return lastnameTextfiled;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	
}
