package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;


public class IOS_Safari_Helper extends BaseDriver{
	public void googleSearch(String search, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			goToURL("https://google.com",driver, outputLog);
			driver.getContextHandles();
			String currentContext = driver.getContext();
			driver.context("NATIVE_APP");
			driver.findElement(By.name("Search")).click();
			//System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeOther[@label='Search']").sendKeys(search);
			if(driver.findElementsByXPath("//XCUIElementTypeButton[@label='Search']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Search']").click();
			else
				driver.findElementByXPath("//XCUIElementTypeButton[@label='search']").click();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Completed Google Search");
			outputLog.newLine();
			//vals.submit();
			//driver.findElement(By.name("q")).submit();
			//System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	/* not quite sure if this works
	public void yahooSearch(String search, AppiumDriver<MobileElement> driver) throws InterruptedException {
		goToURL("https://yahoo.com",driver);
		driver.getContextHandles();
		String currentContext = driver.getContext();
		driver.context("NATIVE_APP");
		//System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Search']").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Search']").sendKeys(search);
		//System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='Search']").click();
		driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton").click();
		
		//System.out.println(driver.getPageSource());
	}
	*/
	public void goToURL(String url, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			driver.get(url);
			Thread.sleep(4000);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Went to URL");
			outputLog.newLine();
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
