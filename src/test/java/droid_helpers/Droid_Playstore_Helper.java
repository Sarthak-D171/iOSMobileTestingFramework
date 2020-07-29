package droid_helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Droid_Playstore_Helper {
	public void downloadApp(String search, AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			Thread.sleep(5000);
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//android.widget.TextView[@text='Search for apps & games']").click();
			driver.findElementByXPath("//android.widget.EditText").sendKeys(search);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			driver.findElementByXPath("//android.widget.Button[@text='Install']").click();
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}


}
