package com.ebanking.keyworddriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	Workbook book;
	Sheet sheet;
	
	private static String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName;
	}
	
	// open excel file
	public void openExcel(String folderName, String fileName, String sheetName) {
		fileName = fileName.toLowerCase();
		FileInputStream fis;
		try {
			fis = new FileInputStream(getFilePath(folderName, fileName));
			if(fileName.endsWith("xls")) {
				book = new HSSFWorkbook(fis);
			}else if(fileName.endsWith("xlsx")){
				book = new XSSFWorkbook(fis);
			}else {
				throw new RuntimeException("file extension must be either xls or xlsx");
			}
			sheet = book.getSheet(sheetName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// number of rows
	public int getRows() {
		return sheet.getLastRowNum();
	}
	
	
	// number of columns
	public int getColumns() {
		return sheet.getRow(0).getLastCellNum();
	}
	
	
	// read the data from a cell
	public String readData(int rnum, int cnum) {
		Cell cell = sheet.getRow(rnum).getCell(cnum);
		String data = "";
		try {
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case STRING:
				data = cell.getStringCellValue();
				break;
			case NUMERIC:
				double cellvalue = cell.getNumericCellValue();
				int intvalue = (int) cellvalue;
				data = Integer.toString(intvalue);
				break;
			case BLANK:
			case _NONE:
			default:
				data = "";
				break;
			}
		} catch (NullPointerException e) {
			
		}
		return data;
	}
	
	
	// close excel file
	public void closeExcel() {
		try {
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
//	public static void main(String[] args) {
//		ExcelHelper obj = new ExcelHelper();
//		obj.openExcel("resources", "testcases.xlsx", "testcases");
//		int nor = obj.getRows();
//		int noc = obj.getColumns();
//		System.out.println("nor = "+nor+" and noc = "+noc);
//		for(int i = 1; i<=nor; i++) {
//			for(int c =0; c < noc; c++) {
//				System.out.print(obj.readData(i, c)+"\t");
//			}
//			System.out.println();
//		}
//		
//		obj.closeExcel();
//	}

}
