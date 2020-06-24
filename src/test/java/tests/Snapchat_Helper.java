package tests;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Snapchat_Helper extends BaseDriver {
	public void sendSnap(AppiumDriver<MobileElement> driver) {
		driver.findElement(By.name("take_a_snap")).click();
		System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeButton[@name='send_to_button']").click();
		System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeCell[@name='select_recipients_friends_0']").click();
		System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeButton[@name='send_snap']").click();
		System.out.println(driver.getPageSource());
	}

}
