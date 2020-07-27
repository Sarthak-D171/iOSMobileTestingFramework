package tests;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSBy;

public class IOS_Phone_Helper extends BaseDriver{
	public void callNumber(String num, AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Keypad']").click();
			//System.out.println(driver.getPageSource());
			for(int i =0;i<num.length();i++) {
				driver.findElement(By.name(String.valueOf(num.charAt(i)))).click();
			}
			//System.out.println(driver.getPageSource());
			driver.findElement(By.name("Call")).click();
			disconnectCall(driver);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
		
		//driver.findElement(By.id("End Call")).click();
	}
	public void callContact(String name,AppiumDriver<MobileElement> driver) {
		try { 
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Contacts']").click();
			driver.findElement(By.name(name)).click();
			boolean cell = driver.findElements(By.name("cell, Call")).size() > 0;
			if(cell) driver.findElement(By.name("cell, Call")).click();
			boolean home = driver.findElements(By.name("home, Call")).size() > 0;
			if(home) driver.findElement(By.name("home, Call")).click();
			boolean work = driver.findElements(By.name("work, Call")).size() > 0;
			if(work) driver.findElement(By.name("work, Call")).click();
			disconnectCall(driver);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
		
	}
	public boolean disconnectCall(AppiumDriver<MobileElement> driver) {
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
			return isCalling;
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
			return false;
		}
	}
	
}
