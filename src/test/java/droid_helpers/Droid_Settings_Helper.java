package droid_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Droid_Settings_Helper {
	
	public void toggleBluetoothOnePlus(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		try {
			((AndroidDriver) driver).startActivity(new Activity("com.android.settings","com.android.settings.Settings$BluetoothSettingsActivity"));
			System.out.println(driver.getPageSource());
			if(driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='On']").size()>0) {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='On']").click();
				System.out.println("Hello");
			} else if(driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='Off']").size()>0) {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='Off']").click();
				System.out.println("Hello");
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Toggled Bluetooth");
			outputLog.newLine();

		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	public void toggleBluetoothSamsung(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			driver.findElementByXPath("//android.widget.TextView[@text='Connections']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Switch[@content-desc='Bluetooth']").click();
			System.out.println(driver.getPageSource());
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Toggled Bluetooth");
			outputLog.newLine();
			
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	public boolean bluetoothOnSamsung(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			driver.findElementByXPath("//android.widget.TextView[@text='Connections']").click();
			Thread.sleep(3000);
			MobileElement m = driver.findElementByXPath("//android.widget.Switch[@content-desc='Bluetooth']");
			if(m.getAttribute("text").equals("Off")) {
				return false;
			}
			return true;
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
			return false;
		}
	}
	
	public void enableHCILOGS(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+"Developer options"+"\").instance(0))").click();
			Thread.sleep(3000);
			if(driver.findElementsByXPath("//android.widget.LinearLayout[@index='4']/android.widget.LinearLayout/android.widget.Switch[@text='On']").size()>0) {
				return;
			} else {
				driver.findElementByXPath("//android.widget.LinearLayout[@index='4']/android.widget.LinearLayout/android.widget.Switch").click();
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" HCI Logging Enabled");
			outputLog.newLine();
			
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
