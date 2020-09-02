package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Youtube_Helper extends BaseDriver {
	//unsure if this works
	public void openplayVid(int mins, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			Thread.sleep(10000);
			if(driver.findElementsByXPath("//XCUIElementTypeButton[@label='Play']").size()>0){
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Play']").click();
			}
			else {
				System.out.println(driver.getPageSource());
				driver.findElementByXPath("//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton/XCUIElementTypeButton").click();
				Thread.sleep(1000);
				
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Started Video");
			outputLog.newLine();
			
			long finish = System.currentTimeMillis() + mins*60*1000;
			while(System.currentTimeMillis() < finish) {
				driver.findElementsByXPath("//XCUIElementTypeOther");
				Thread.sleep(4000);
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
