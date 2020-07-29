package ios_helpers;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Settings_Helper extends BaseDriver{
	public void toggleBluetooth(AppiumDriver<MobileElement> driver) {
		try {
			//System.out.println(driver.getPageSource());
			if(driver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@label='Settings']").click();
			}
			driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
			
			//driver.findElementByXPath("//XCUIElementTypeCell[@label='Bluetooth']").click();
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
}
