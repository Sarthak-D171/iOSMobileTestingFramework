package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Gmail_Helper extends BaseDriver {
	public void composeEmail(String to, String subject, String body, AppiumDriver<MobileElement> driver) {
		driver.findElement(By.name("ComposeButton")).click();
		System.out.println(driver.getPageSource());
		driver.findElement(By.name("To")).sendKeys(to);
		System.out.println(driver.getPageSource());
		driver.findElement(By.name("Email subject")).sendKeys(subject);
		driver.findElement(By.name("Message")).sendKeys(body);
	}
}
