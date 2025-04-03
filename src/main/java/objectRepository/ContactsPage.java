package objectRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	// Contructor
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(xpath="//img[@title='Create Contact...']")
		private WebElement CreateContactIcon;

		public WebElement getCreateContactIcon() {
			return CreateContactIcon;
		}

		}
		
		
		
		
	

