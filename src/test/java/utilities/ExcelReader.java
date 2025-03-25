package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReader {
	public static String[][] readExcelData(String filePath, String sheetName) throws IOException {
		System.out.println("ExcelReader::readExcelData() - File path = " + filePath +", Sheet = " + sheetName);
		FileInputStream fis; // Reads the content of a file as a stream of bytes
		fis = new FileInputStream(filePath); // Constructor accepts file path so as to read it
		Workbook workbook = new XSSFWorkbook(fis); // High level representation of a Excel Workbook
		Sheet sheet = workbook.getSheet(sheetName); // High level representation of a Excel Worksheet; One xls can have
													// as many sheets

		// Counts only rows with content and not empty; Assume all rows have similar
		// content count
		// Returns actual row count and not zero based
		int rowCount = sheet.getPhysicalNumberOfRows();
		// Cells having values, in first row (Assume all rows have same number of
		// columns
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		// 'RowCount-1' because it is not zero based row count but actual
		// Not needed for column count as the loop uses '<' (less than)
		String[][] data = new String[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) { 		// 'i=1' to skip the header row (The titles 'Username' and 'Password')
			Row row = sheet.getRow(i); 				// Reads the row by index (0, 1, 2 etc.)
			for (int j = 0; j < colCount; j++) { 	// Reds the column in the current row
				Cell cell = row.getCell(j); 		// Cell is the row-column data. It reds as (0,0), (0,1) etc. i.e., here
													// user1, pass1
				data[i - 1][j] = cell.toString();	// 'i-1' because we read the index from 1, but the array is of zero based index
			}
		}
		workbook.close();
		fis.close();
		return data;
	}

}
