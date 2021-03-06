package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import tests.BaseDriver;

public class IOS_Gmail_Helper extends BaseDriver {
	public void composeEmail(String to, String subject, String body, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		//System.out.println(driver.getPageSource());
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Compose']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@label='To']").sendKeys(to);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='return']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTextField[@label='Subject']").sendKeys(subject);
			Thread.sleep(2000);
			driver.findElementByXPath("//XCUIElementTypeOther[@label='Message']").sendKeys(body);
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Send']").click();
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sent Email");
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
