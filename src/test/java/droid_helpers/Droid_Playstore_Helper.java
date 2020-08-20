package droid_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Droid_Playstore_Helper {
	public void downloadApp(String search, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			Thread.sleep(5000);
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//android.widget.TextView[@text='Search for apps & games']").click();
			driver.findElementByXPath("//android.widget.EditText").sendKeys(search);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			driver.findElementByXPath("//android.widget.Button[@text='Install']").click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Installed App: "+ search);
			outputLog.newLine();
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}


}
