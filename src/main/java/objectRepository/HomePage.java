package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	// Constructor
    public HomePage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    }
    
    @FindBy(linkText="Contacts")
     private WebElement contactslinks;
    
    
    @FindBy(linkText="Organizations")
    private WebElement organizationslinks;
    
    public WebElement getOrganizationslinks() {
		return organizationslinks;
	}

	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
     private WebElement logoutlink;   
    
    @FindBy(linkText="Sign Out")
    WebElement signoutlink;

	public WebElement getContactslinks() {
		return contactslinks;
	}

	public WebElement getLogoutlink() {
		return logoutlink;
	}

	public WebElement getSignoutlink() {
		return signoutlink;
	}
    
    
		
}

