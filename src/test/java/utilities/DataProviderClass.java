package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name = "excelCredentials")
	public Object[][] readExcelData() throws IOException {
		String currentDir = System.getProperty("user.dir");
		String filePath = currentDir + "//src//test//resources//Credentials.xlsx";
		final String sheetName = "Sheet1";
		return ExcelReader.readExcelData(filePath, sheetName);
	}
}
