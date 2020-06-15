package tests;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Safari_Helper extends BaseDriver{
	public void googleSearch(String search, AppiumDriver<MobileElement> driver) throws InterruptedException {
		goToURL("https://google.com",driver);
		driver.getContextHandles();
		String currentContext = driver.getContext();
		driver.context("NATIVE_APP");
		driver.findElement(By.name("Search")).click();
		//System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeOther[@label='Search']").sendKeys(search);
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Search']").click();
		//vals.submit();
		//driver.findElement(By.name("q")).submit();
		//System.out.println(driver.getPageSource());
	}
	public void yahooSearch(String search, AppiumDriver<MobileElement> driver) {
		goToURL("https://yahoo.com",driver);
		driver.getContextHandles();
		String currentContext = driver.getContext();
		driver.context("NATIVE_APP");
		System.out.println(driver.getPageSource());
	}
	public void goToURL(String url, AppiumDriver<MobileElement> driver) {
		driver.get(url);
	}

}
