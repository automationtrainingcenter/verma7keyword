package com.ebanking.keyworddriven;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Driver {
	public static void main(String[] args) {
		// testcases sheet
		ExcelHelper tcExcel = new ExcelHelper();
		tcExcel.openExcel("resources", "testcases.xlsx", "testcases");
		
		// test steps sheet
		ExcelHelper tsExcel  = new ExcelHelper();
		tsExcel.openExcel("resources", "testcases.xlsx", "teststeps");
		
		// create an object of Keywords class
		Keywords keywordsObj = new Keywords();
		// retrieve all the methods inside the Keywords class
//		Keywords.class.getMethods()
		Method[] methods = keywordsObj.getClass().getMethods();
		
		for(int i = 1; i <= tcExcel.getRows(); i++) {
			String tcd_tcname = tcExcel.readData(i, 0);
			String runMode = tcExcel.readData(i, 1);
			if(runMode.equalsIgnoreCase("yes")) {
				// start executing the test steps
				for(int j = 1; j <= tsExcel.getRows(); j++) {
					String tsd_tcname = tsExcel.readData(j, 0);
					if(tcd_tcname.equals(tsd_tcname)) {
						String tsname = tsExcel.readData(j, 1);
						String locType = tsExcel.readData(j, 2);
						String locValue = tsExcel.readData(j, 3);
						String keyword = tsExcel.readData(j, 4);
						String testData = tsExcel.readData(j, 5);
						System.out.println(tsname+"\t"+locType+"\t"+locValue+"\t"+keyword+"\t"+testData);
						// call a method from the keywords class based on the keyword from excel
						for(Method method : methods) {
							if(method.getName().equals(keyword)) {
								try {
									method.invoke(keywordsObj, locType, locValue, testData);
									break; // break methods array
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}
					}
				}
			}
		}
		
		// close excel docs
		tcExcel.closeExcel();
		tsExcel.closeExcel();
	}
}
