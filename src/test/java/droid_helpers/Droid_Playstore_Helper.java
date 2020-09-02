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
			Thread.sleep(10000);
	
			driver.findElementByXPath("//android.widget.TextView[@text='Search for apps & games']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//android.widget.EditText").sendKeys(search);
			Thread.sleep(1000);
			driver.getKeyboard().sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElementByXPath("//android.widget.Button[@text='Install']").click();
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Installed App: "+ search);
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
