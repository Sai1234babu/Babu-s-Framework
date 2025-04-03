package ToCreateOrganization;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.webDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationPage;

public class ToCreateOrganizationChemicalIndustryDropDownTest extends BaseClass{
	
	@Test(groups = "Regression")
	public void ToCreateOrganizationChemicalIndustryDropDown_003() throws EncryptedDocumentException, IOException {
	HomePage hp = new HomePage(driver);
	hp.getOrganizationslinks().click();
	OrganizationPage op = new OrganizationPage(driver);
	op.getCreateorganizationpagepulse().click();
	CreateOrganizationPage cop = new CreateOrganizationPage(driver);
	ExcelFileUtility eutil = new ExcelFileUtility();
	String LASTNAME = eutil.toReadDataFromExcelFile("organization", 1, 2);
	Random r = new Random();
	int random = r.nextInt(1000);
	cop.getorgname().sendKeys(LASTNAME + random);
	webDriverUtility  wutil = new webDriverUtility();
	wutil.toHandleDropdown("Chemicals",cop.getSelectclass());
	cop.getSaveButton().click();
	OrganizationInformationPage oip = new OrganizationInformationPage(driver);
	String name = oip.getOutputorginf().getText();
	if (name.contains(LASTNAME)) {
		System.out.println(name + "--Passed");
	} else {
		System.out.println(name + "---Failed");
	}
	}

}
