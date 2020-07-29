package droid_helpers;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;


/*
 * CHROME NOTES: http://appium.io/docs/en/writing-running-appium/web/chromedriver/
 * npm install appium --chromedriver_version="VERSION_NUM"
 * WEHN STARTING SERVER: appium --chromedriver-executable /path/to/my/chromedriver
 * 
 */

public class Droid_Chrome_Helper {
	public void gotoURL(String url, AppiumDriver<MobileElement> driver) {
		try {
			driver.get(url);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
