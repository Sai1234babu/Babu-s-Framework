package ToCreateContact;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.webDriverUtility;

import objectRepository.ContactsInformationPage;
import objectRepository.ContactsPage;

import objectRepository.CreateContactsPage;
import objectRepository.HomePage;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactOrganizationToOrganizationTest extends BaseClass {

	@Test(groups = "somke")
	public void ToCreateContactOrganizationToOrganization_05() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactslinks().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactIcon().click();
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("contact", 1, 2);
		ccp.getLastnameTextfiled().sendKeys(LASTNAME);
		ccp.getChildwindow().click();
		webDriverUtility wutil = new webDriverUtility();
		wutil.toSwitchWindow(driver, "Accounts");
		driver.findElement(By.xpath("//a[text()='vtiger']")).click();
		wutil.toSwitchWindow(driver, "Contacts");
		ccp.getSaveButton().click();
		//Fail
	    //Assert.fail();
		ContactsInformationPage cip = new ContactsInformationPage(driver);
		String name = cip.getContactsHeader().getText();
		if (name.contains(LASTNAME)) {
			System.out.println(name + "----Passed");
		} else {
			System.out.println(name + "--Failed");
		}
	}
}
