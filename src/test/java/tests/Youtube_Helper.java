package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Youtube_Helper extends BaseDriver {
	public void openVid(String search, AppiumDriver<MobileElement> driver) {
		System.out.println(driver.getPageSource());
	}
}
