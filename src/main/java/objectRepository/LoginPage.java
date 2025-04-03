package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
  
	// Contructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameTextfiled;
	
	@FindAll({@FindBy(name="user_password"),@FindBy(xpath="//input[@type='password']")})
	WebElement passwordTextfiled;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;

	public WebElement getUsernameTextfiled() {
		return usernameTextfiled;
	}

	public WebElement getPasswordTextfiled() {
		return passwordTextfiled;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
}
