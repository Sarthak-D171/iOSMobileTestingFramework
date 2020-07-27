package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IOS_Gmail_Helper extends BaseDriver {
	public void composeEmail(String to, String subject, String body, AppiumDriver<MobileElement> driver) throws InterruptedException {
		//System.out.println(driver.getPageSource());
		try {
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Compose']").click();
			System.out.println(driver.getPageSource());
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@label='To']").sendKeys(to);
			//System.out.println(driver.getPageSource());
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTextField[@label='Subject']").sendKeys(subject);
			Thread.sleep(2000);
			driver.findElementByXPath("//XCUIElementTypeOther[@label='Message']").sendKeys(body);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Send']").click();
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
}
