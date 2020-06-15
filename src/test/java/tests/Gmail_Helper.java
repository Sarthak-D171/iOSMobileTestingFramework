package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Gmail_Helper extends BaseDriver {
	public void composeEmail(String to, String subject, String body, AppiumDriver<MobileElement> driver) throws InterruptedException {
		//System.out.println(driver.getPageSource());
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
	}
}
