package ios_helpers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IOS_AppStore_Helper {
	public void downloadApp(String appName, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(appName);
			Thread.sleep(1000);
			if(driver.findElementsByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='Search']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='Search']").click();
			else
				driver.findElementByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='search']").click();
			Thread.sleep(3000);
			
			if(driver.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='Redownload']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='Redownload']").click();
			else if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='redownload']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeButton[@name='redownload']").click();
			else if(driver.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			else if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='get']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeButton[@name='get']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			else if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='get, in-app purchases']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeButton[@name='get, in-app purchases']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			else {
				driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET, In-App Purchases']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Downloaded: "+appName);
			outputLog.newLine();
			
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
		//driver.findElementByXPath("//XCUIElementTypeTextField[@label='To:']").sendKeys(number);
		//System.out.println(driver.getPageSource());
	}
}
