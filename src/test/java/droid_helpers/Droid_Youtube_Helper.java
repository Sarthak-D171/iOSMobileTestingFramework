package droid_helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;

public class Droid_Youtube_Helper {
	public void openVid(String search, AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			driver.findElementByXPath("//android.widget.ImageView[@content-desc='Search']").click();
			MobileElement s = driver.findElementByXPath("//android.widget.EditText");
			s.sendKeys(search);
			Thread.sleep(1000);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			driver.findElementByXPath("//android.view.ViewGroup[@index='0']").click();
			System.out.println(driver.getPageSource());
			Thread.sleep(1000);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
