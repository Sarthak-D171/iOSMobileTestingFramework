package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Calc_Helper extends BaseDriver{
	public void typeNum(String num, AppiumDriver<MobileElement> driver) {
		for(int i=0;i<num.length();i++) {
			if(num.charAt(i) == '.') {
				driver.findElement(By.name("decimal")).click();
			} else {
				driver.findElement(By.name(String.valueOf(num.charAt(i)))).click();
			}
		}
	}
	//operator: divide,multiply,subtract,add
	public void basicOperation(String operator, String n1, String n2, AppiumDriver<MobileElement> driver) {
		typeNum(n1,driver);
		driver.findElement(By.name(operator)).click();
		typeNum(n2,driver);
		driver.findElement(By.name("equals")).click();
	}
	public void clear(AppiumDriver<MobileElement> driver) {
		boolean b = driver.findElements(By.name("all clear")).size() > 0;
		if(b) {
			driver.findElement(By.name("all clear")).click();
		} else {
			driver.findElement(By.name("clear")).click();
		}
	}
}
