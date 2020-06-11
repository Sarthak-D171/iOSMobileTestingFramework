package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class Phone_Helper extends BaseDriver{
	public void callNumber(String num, AppiumDriver<MobileElement> driver) {
		driver.findElement(By.name("Keypad")).click();
		for(int i =0;i<num.length();i++) {
			driver.findElement(By.name(String.valueOf(num.charAt(i)))).click();
		}
		driver.findElement(By.name("Call")).click();
	}
	public void callContact(String name,AppiumDriver<MobileElement> driver) {
		driver.findElement(By.name("Contacts")).click();
		driver.findElement(By.name(name)).click();
		boolean cell = driver.findElements(By.name("cell, Call")).size() > 0;
		if(cell) driver.findElement(By.name("cell, Call")).click();
		boolean home = driver.findElements(By.name("home, Call")).size() > 0;
		if(home) driver.findElement(By.name("home, Call")).click();
		boolean work = driver.findElements(By.name("work, Call")).size() > 0;
		if(work) driver.findElement(By.name("work, Call")).click();
	}
	
}
