package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {

		public CreateOrganizationPage(WebDriver driver){
			PageFactory.initElements(driver, this);
		}
		@FindBy(name="accountname")
		private WebElement orgname;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveButton;
		
		@FindBy(name ="industry")
		private WebElement selectclass;
		
		@FindBy(name ="accounttype")
		private WebElement selectdropdown;
		
		
		public WebElement getOrgname() {
			return orgname;
		}

		public WebElement getSelectclass() {
			return selectclass;
		}

		public WebElement getSelectdropdown() {
			return selectdropdown;
		}

		public WebElement getorgname() {
			return orgname;
		}

		public WebElement getSaveButton() {
			return saveButton;
		}
	
}
