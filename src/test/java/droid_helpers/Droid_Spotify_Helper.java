package droid_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class Droid_Spotify_Helper extends BaseDriver {
	public void playSong(String search, AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			System.out.println(driver.getPageSource());
			if(driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Home']").size()>0) {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Home']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Search']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@content-desc='Search']").click();
			}
			else {
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ImageView[@content-desc='Home']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ImageView[@content-desc='Search']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ImageView[@content-desc='Search']").click();	
			}
			Thread.sleep(1000);
			driver.findElementByXPath("//android.widget.EditText").sendKeys(search);
			Thread.sleep(1000);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElementByXPath("//android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='0']").click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Playing Song");
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
