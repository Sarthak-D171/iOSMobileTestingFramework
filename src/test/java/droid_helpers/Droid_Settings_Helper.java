package droid_helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Droid_Settings_Helper {
	public void toggleBluetooth(AppiumDriver<MobileElement> driver) {
		try {
			((AndroidDriver) driver).startActivity(new Activity("com.android.settings","com.android.settings.Settings$BluetoothSettingsActivity"));
			System.out.println(driver.getPageSource());
			if(driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='On']").size()>0) {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='On']").click();
				System.out.println("Hello");
			} else if(driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='Off']").size()>0) {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='Off']").click();
				System.out.println("Hello");
			}
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
