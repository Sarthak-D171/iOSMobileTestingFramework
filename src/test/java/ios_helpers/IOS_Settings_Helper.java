package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Settings_Helper extends BaseDriver{
	public void toggleBluetooth(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try {
			Thread.sleep(10000);
			if(driver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").click();
			}
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Toggled BlueTooth");
			outputLog.newLine();
		
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public boolean bluetoothOn(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try {
			Thread.sleep(10000);
			if(driver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").click();
			}
			driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			if(driver.findElementsByXPath("//XCUIElementTypeSwitch[@value='1']").size()>0) {
				outputLog.write(dtf.format(now)+" BlueTooth On");
				outputLog.newLine();
				return true;
			}
			outputLog.write(dtf.format(now)+" BlueTooth Off");
			outputLog.newLine();
			
			return false;
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
