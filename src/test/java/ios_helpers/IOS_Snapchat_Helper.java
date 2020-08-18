package ios_helpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Snapchat_Helper extends BaseDriver {
	public void sendSnap(AppiumDriver<MobileElement> driver) {
		try {
			driver.findElement(By.name("take_a_snap")).click();
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeButton[@name='send_to_button']").click();
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeCell[@name='select_recipients_friends_0']").click();
			System.out.println(driver.getPageSource());
			driver.findElementByXPath("//XCUIElementTypeButton[@name='send_snap']").click();
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	public void openSnap(AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='chat']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeButton[@name='chat']").click();
			System.out.println(driver.getPageSource());
			List<MobileElement> ele = driver.findElementsByXPath("//XCUIElementTypeStaticText");
			for(MobileElement e : ele) {
				if(e.getAttribute("name").contains("New Snap")){
					e.click();
					Thread.sleep(3000);
					driver.findElementByXPath("//XCUIElementTypeOther").click();
					System.out.println(driver.getPageSource());
					return;
				}
			}
				
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
			
		}
	}

}
