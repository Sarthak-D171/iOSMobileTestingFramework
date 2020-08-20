package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Calc_Helper extends BaseDriver{
	public void typeNum(String num, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
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
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	//operator: divide,multiply,subtract,add
	public void basicOperation(String operator, String n1, String n2, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		try {
			driver.getPageSource();
			typeNum(n1,driver, outputLog);
			driver.findElement(By.name(operator)).click();
			typeNum(n2,driver, outputLog);
			driver.findElement(By.name("equals")).click();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Perform basic calculator action");
			outputLog.newLine();
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public void clear(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		try {
			boolean b = driver.findElements(By.name("all clear")).size() > 0;
			if(b) {
				driver.findElement(By.name("all clear")).click();
			} else {
				driver.findElement(By.name("clear")).click();
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Clear Calculator Screen");
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
