package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataAccountValidate {
	
	@Test
	public static  String[][] dataAccountCreate(String fileName, String sheetName) throws IOException {
			String filepath=".\\resources\\TestData\\"+fileName;
			File file=new File(filepath);
			FileInputStream inputstream=new FileInputStream(file);
			Workbook wb=new XSSFWorkbook(inputstream);
			Sheet sheet=wb.getSheet(sheetName);
			int totalRows=sheet.getLastRowNum();
			int totalcolumn=sheet.getRow(0).getLastCellNum();
			String[][]data=new String[totalRows][totalcolumn];
			for(int rows=1;rows<=totalRows;rows++) {
				for(int col=0;col<totalcolumn;col++) {
					Cell cell=sheet.getRow(rows).getCell(col);
					if(cell.getCellType()==CellType.NUMERIC)
						data[rows-1][col]=String.valueOf((int)cell.getNumericCellValue());
					else if (cell.getCellType()==CellType.BOOLEAN)
						data[rows-1][col]=String.valueOf(cell.getBooleanCellValue());
					else if (cell.getCellType()==CellType.STRING)
						data[rows-1][col]=cell.getStringCellValue();
					else if (cell.getCellType()==CellType.BLANK)
						data[rows-1][col]="";
					System.out.println(data[rows-1][col]);

				}
				
			}
			//System.out.println(data);
			return data;

	}
	
	
}
