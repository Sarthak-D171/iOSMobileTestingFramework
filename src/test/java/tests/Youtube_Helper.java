package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Youtube_Helper extends BaseDriver {
	public void openVid(AppiumDriver<MobileElement> driver) throws InterruptedException {
		System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton/XCUIElementTypeButton").click();
		Thread.sleep(1000);
	}
}
