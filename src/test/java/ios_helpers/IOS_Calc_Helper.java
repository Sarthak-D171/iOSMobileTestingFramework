package ios_helpers;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Calc_Helper extends BaseDriver{
	public void typeNum(String num, AppiumDriver<MobileElement> driver) {
		try {
			boolean isMinus = false;
			for(int i=0;i<num.length();i++) {
				if(num.charAt(i) == '.') {
					driver.findElement(By.name("decimal")).click();
				} else if(num.charAt(i) == '-'){
					isMinus = true;
				}else {
					driver.findElement(By.name(String.valueOf(num.charAt(i)))).click();
				}
			}
			if(isMinus) driver.findElement(By.name("plus, minus")).click();
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	//operator: divide,multiply,subtract,add
	public void basicOperation(String operator, String n1, String n2, AppiumDriver<MobileElement> driver) {
		try {
			driver.getPageSource();
			typeNum(n1,driver);
			driver.findElement(By.name(operator)).click();
			typeNum(n2,driver);
			driver.findElement(By.name("equals")).click();
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public void clear(AppiumDriver<MobileElement> driver) {
		try {
			boolean b = driver.findElements(By.name("all clear")).size() > 0;
			if(b) {
				driver.findElement(By.name("all clear")).click();
			} else {
				driver.findElement(By.name("clear")).click();
			}
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
}
