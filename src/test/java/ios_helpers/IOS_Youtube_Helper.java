package ios_helpers;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Youtube_Helper extends BaseDriver {
	//unsure if this works
	public void openVid(AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton/XCUIElementTypeButton").click();
			Thread.sleep(1000);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
}
