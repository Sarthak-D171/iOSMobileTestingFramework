package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Settings_Helper extends BaseDriver{
	public void toggleBluetooth(AppiumDriver<MobileElement> driver) {
		//System.out.println(driver.getPageSource());
		if(driver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").size()>0) {
			driver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").click();
		}
		driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
		System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
		
		//driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
		System.out.println(driver.getPageSource());
	}
}
