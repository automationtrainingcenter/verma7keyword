package com.ebanking.keyworddriven;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Keywords {
	WebDriver driver;
	
//	openBrowser
	public void openBrowser(String locType, String locValue, String testData) {
		if(testData.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}else if(testData.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}else {
			throw new RuntimeException("Browser name must be either chrome or firefox");
		}
		driver.manage().window().maximize();
	}

//	navigate
	public void navigate(String locType, String locValue, String testData) {
		driver.get(testData);
	}

//	fillTextBox
	public void fillTextBox(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).sendKeys(testData);
	}

//	click
	public void click(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).click();
	}

//	selectByText
	public void selectByText(String locType, String locValue, String testData) {
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		Select selectEle = new Select(ele);
		selectEle.selectByVisibleText(testData);
	}

//	acceptAlert
	public void acceptAlert(String locType, String locValue, String testData) {
		driver.switchTo().alert().accept();
	}

//	closeBrowser
	public void closeBrowser(String locType, String locValue, String testData) {
		if(driver.getWindowHandles().size()>1) {
			driver.quit();
		}else {
			driver.close();
		}
	}
	
//	public static void main(String[] args) {
//		Keywords obj = new Keywords();
//		Method[] methods = obj.getClass().getMethods();
//		for(Method method : methods) {
////			System.out.println(method.getName());
//			if(method.getName().equals("openBrowser")) {
//				try {
//					method.invoke(obj, "","","chrome");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
