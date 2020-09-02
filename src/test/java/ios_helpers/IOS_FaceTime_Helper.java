package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_FaceTime_Helper extends BaseDriver{
	public void videoCallIOS11(String num, AppiumDriver<MobileElement> driver, BufferedWriter outputLog, int mins) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Video']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeSearchField").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(num);
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[@name='FaceTime']").click();
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Facetime Call");
			outputLog.newLine();
			
			long finish = System.currentTimeMillis() + mins*60*1000;
			while(System.currentTimeMillis() < finish) {
				driver.findElementsByXPath("//XCUIElementTypeOther");
				Thread.sleep(4000);
			}
			disconnectCall(driver, outputLog);
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public void videoCallIOS13(String num, AppiumDriver<MobileElement> driver, BufferedWriter outputLog, int mins) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Add']").click();
			Thread.sleep(1000);
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeTextField").sendKeys(num);
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Mail.addContactButton']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Cancel']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Video']").click();
			Thread.sleep(1000);
			System.out.println(driver.getPageSource());
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Facetime Call");
			outputLog.newLine();
			
			long finish = System.currentTimeMillis() + mins*60*1000;
			while(System.currentTimeMillis() < finish) {
				driver.findElementsByXPath("//XCUIElementTypeOther");
				Thread.sleep(4000);
			}
			disconnectCall(driver, outputLog);
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public boolean disconnectCall(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		try {
			boolean isCalling = false;
			List<MobileElement> ele = driver.findElementsByXPath("//XCUIElementTypeButton");
			for(int i =0; i<ele.size();i++) {
				if(ele.get(i).getAttribute("name") != null && ele.get(i).getAttribute("name").equals("End call")) {
					ele.get(i).click();
					isCalling = true;
					break;
				}
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Ended Facetime Call");
			outputLog.newLine();
			
			return isCalling;
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
			return false;
		}
	}
}
