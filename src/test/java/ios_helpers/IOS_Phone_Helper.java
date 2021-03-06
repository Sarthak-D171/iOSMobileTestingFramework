package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSBy;
import tests.BaseDriver;

public class IOS_Phone_Helper extends BaseDriver{
	public void callNumber(String num, int mins, AppiumDriver<MobileElement> driver,BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Keypad']").click();
			Thread.sleep(1000);
			for(int i =0;i<num.length();i++) {
				driver.findElement(By.name(String.valueOf(num.charAt(i)))).click();
			}
			//System.out.println(driver.getPageSource());
			driver.findElement(By.name("Call")).click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Call");
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
		
		//driver.findElement(By.id("End Call")).click();
	}
	public void callContact(String name,AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try { 
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Contacts']").click();
			Thread.sleep(1000);
			driver.findElement(By.name(name)).click();
			boolean cell = driver.findElements(By.name("cell, Call")).size() > 0;
			if(cell) driver.findElement(By.name("cell, Call")).click();
			boolean home = driver.findElements(By.name("home, Call")).size() > 0;
			if(home) driver.findElement(By.name("home, Call")).click();
			boolean work = driver.findElements(By.name("work, Call")).size() > 0;
			if(work) driver.findElement(By.name("work, Call")).click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Call");
			outputLog.newLine();
			
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
			outputLog.write(dtf.format(now)+" Disconnected Call");
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
