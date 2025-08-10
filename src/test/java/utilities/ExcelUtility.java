
// Package declaration
package utilities;
 
// Import necessary classes
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class ExcelUtility {

  public ExcelUtility() {
  }
 
  static Workbook workbook;

	public static void putData(List<String> list, int column, String sheetName, List<String> headers) throws IOException {
      String filePath = System.getProperty("user.dir") + "\\testData\\" +"data.xlsx";

      // Load existing workbook or create a new one
      Workbook workbook;
      try {
          workbook = WorkbookFactory.create(new FileInputStream(filePath));
      } catch (FileNotFoundException e) {
          workbook = new XSSFWorkbook();
      }

      // Create a new sheet or load existing sheet
      Sheet sheet = workbook.getSheet(sheetName);  // Sheet name set to "Data"
	    if (sheet == null) {
	        sheet = workbook.createSheet(sheetName);
	    }

	    // Create a row for headers if it doesn't exist
	    Row headerRow = sheet.getRow(0);
	    if (headerRow == null) {
	        headerRow = sheet.createRow(0);
	    }

	    // Set the header for the current column
	    Cell headerCell = headerRow.createCell(column);
	    headerCell.setCellValue(headers.get(column));

	    // Iterate through each row in the list
	    for (int i = 0; i < list.size(); i++) {
	        // Create a new row in the sheet
	        Row row = sheet.getRow(i + 1);  // Offset by 1 for the header row
	        if (row == null) {
	            row = sheet.createRow(i + 1);  // Offset by 1 for the header row
	        }

	        // Create a cell in the current row at the specified column
	        Cell cell = row.createCell(column);

	        // Write data to the cell
	        cell.setCellValue(list.get(i));
	    }

      // Save the workbook
      try (FileOutputStream file = new FileOutputStream(filePath)) {
          workbook.write(file);
          workbook.close();
  		file.close();
      }
  }
}
