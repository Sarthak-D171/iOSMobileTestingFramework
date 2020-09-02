package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import tests.BaseDriver;

public class IOS_Snapchat_Helper extends BaseDriver {
	public void sendSnap(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try {
			Thread.sleep(10000);
			while(driver.findElements(By.name("take_a_snap")).size()>0)
				driver.findElement(By.name("take_a_snap")).click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='send_to_button']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeCell[@name='select_recipients_friends_0']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@name='send_snap']").click();
			Thread.sleep(1000);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sent Snap");
			outputLog.newLine();
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	public void openSnap(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='chat']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeButton[@name='chat']").click();
			Thread.sleep(1000);
			List<MobileElement> ele = driver.findElementsByXPath("//XCUIElementTypeStaticText");
			for(MobileElement e : ele) {
				if(e.getAttribute("name").contains("New Snap")){
					e.click();
					Thread.sleep(3000);
					driver.findElementByXPath("//XCUIElementTypeOther").click();
					Thread.sleep(1000);
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();
					outputLog.write(dtf.format(now)+" Opened Snap");
					outputLog.newLine();
					return;
				}
			}
				
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");	
		}
	}

}
