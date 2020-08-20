package droid_helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Droid_Spotify_Helper {
	public void playSong(String search, AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			Thread.sleep(3000);
			System.out.println(driver.getPageSource());
			if(driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Home']").size()>0) {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Home']").click();
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Search']").click();
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Search']").click();
			}
			else {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ImageView[@content-desc='Home']").click();
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ImageView[@content-desc='Search']").click();
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ImageView[@content-desc='Search']").click();	
			}
			driver.findElementByXPath("//android.widget.EditText").sendKeys(search);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			driver.findElementByXPath("//android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='0']").click();
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
