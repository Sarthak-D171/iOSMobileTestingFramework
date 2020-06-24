package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Messages_Helper {
	public void sendMsg(String number, String body, AppiumDriver<MobileElement> driver) {
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Compose']").click();
		System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeTextField[@label='To:']").sendKeys(number);
		driver.findElementByXPath("//XCUIElementTypeTextField[@label='Message']").sendKeys(body);
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Send']").click();
		System.out.println(driver.getPageSource());
	}
}
