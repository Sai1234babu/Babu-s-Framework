package Practice;

import java.io.FileInputStream;
import java.io.IOException;

//import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromExcel {

	public static void main(String[] args) throws  IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testDataM8.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		//call method
		String lastname = wb.getSheet("contact").getRow(1).getCell(2).toString();
		System.out.println(lastname);
		

	}

}
