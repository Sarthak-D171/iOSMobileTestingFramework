package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Safari_Helper extends BaseDriver{
	public void googleSearch(String search, AppiumDriver<MobileElement> driver) throws InterruptedException {
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys(search);
		driver.findElement(By.name("q")).sendKeys(Keys.SPACE);
		driver.findElement(By.name("q")).sendKeys(search);
		System.out.println(driver.getPageSource());
	}

}
