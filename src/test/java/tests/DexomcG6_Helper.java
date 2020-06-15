package tests;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DexomcG6_Helper {
	public void logIn(AppiumDriver<MobileElement> driver) {
		//System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Log in Later']").click();
	}
	public void newSensor(String code, AppiumDriver<MobileElement> driver) {
		driver.findElementByXPath("//XCUIElementTypeButton[@label='New Sensor']").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Code']").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Manually']").click();
		System.out.println(driver.getPageSource());
		for(int i =0;i<code.length();i++) {
			driver.findElement(By.name(String.valueOf(code.charAt(i)))).click();
		}
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Save']").click();
	}
}
