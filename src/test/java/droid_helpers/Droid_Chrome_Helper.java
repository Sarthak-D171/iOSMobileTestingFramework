package droid_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	public void gotoURL(String url, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		try {
			driver.get(url);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Went to"+url);
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
