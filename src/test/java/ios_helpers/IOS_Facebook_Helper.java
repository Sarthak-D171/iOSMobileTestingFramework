package ios_helpers;

import java.util.HashMap;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IOS_Facebook_Helper {
	public void scroll(AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			driver.executeScript("mobile:scroll", scrollObject);
			Thread.sleep(1000);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
