package droid_helpers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import tests.BaseDriver;

public class Droid_Youtube_Helper extends BaseDriver{
	public void openVid(String search, AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//android.widget.ImageView[@content-desc='Search']").click();
			Thread.sleep(1000);
			MobileElement s = driver.findElementByXPath("//android.widget.EditText");
			s.sendKeys(search);
			
			Thread.sleep(1000);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElementByXPath("//android.view.ViewGroup[@index='0']").click();
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Playing Video");
			outputLog.newLine();
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

}
