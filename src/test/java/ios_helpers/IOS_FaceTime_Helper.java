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
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Video']").click();
			driver.findElementByXPath("//XCUIElementTypeSearchField").click();
			driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(num);
			driver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[@name='FaceTime']").click();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Facetime Call");
			outputLog.newLine();
			Thread.sleep(mins*60*1000);
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
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Add']").click();
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeTextField").sendKeys(num);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Mail.addContactButton']").click();
			Thread.sleep(500);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Cancel']").click();
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Video']").click();
			System.out.println(driver.getPageSource());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Facetime Call");
			outputLog.newLine();
			Thread.sleep(mins*60*1000);
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
