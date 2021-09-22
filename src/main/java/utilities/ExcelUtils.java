package utilities;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) throws IOException {

		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(sheetName);

	}

	public static int getRowCount() throws IOException {

		int rowcount = 0;
		rowcount = sheet.getPhysicalNumberOfRows();

		return rowcount;
	}

	public static int getColCount() throws IOException {

		int colcount = 0;
		colcount = sheet.getRow(0).getPhysicalNumberOfCells();

		return colcount;
	}

	public static String getCellDataString(int rowNum, int colNum) throws IOException {

		String celldata = null;
		celldata = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

		return celldata;

	}

	public static double getCellDataNumeric(int rowNum, int colNum) throws IOException {

		double celldata = 0;

		celldata = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();

		return celldata;

	}

}
