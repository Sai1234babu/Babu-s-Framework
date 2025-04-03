package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class consist of method related to ExcelFile
 * 
 * 
 */
public class ExcelFileUtility {
	
	/**
	 * This method is used to read the Data form excelFile provide sheetname row cell value
	 * @param sheetnanme
	 * @param row
	 * @param cell
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */

	public String toReadDataFromExcelFile(String sheetnanme,int row,int cell) throws EncryptedDocumentException, IOException  {
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testDataM8.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	String value = wb.getSheet(sheetnanme).getRow(row).getCell(cell).toString();
	return value;
}
}