package ios_helpers;

import java.net.MalformedURLException;

import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Messages_Helper extends BaseDriver{
	public void sendMsgIOS11(String number, String body, AppiumDriver<MobileElement> driver) throws InterruptedException, MalformedURLException {
		try {
			if(isTexting(driver)) {
				driver.findElementByXPath("//XCUIElementTypeTextField[@label='Message']").sendKeys(body);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Send']").click();
			}
			else {
				Thread.sleep(3000);
				System.out.println(driver.getPageSource());
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Compose']").click();
				System.out.println(driver.getPageSource());
				driver.findElementByXPath("//XCUIElementTypeTextField[@label='To:']").sendKeys(number);
				driver.findElementByXPath("//XCUIElementTypeTextField[@label='Message']").sendKeys(body);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Send']").click();
				System.out.println(driver.getPageSource());
			}
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public boolean isTexting(AppiumDriver<MobileElement> driver) {
		driver.getPageSource();
		if(driver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton").size()>0 && driver.findElementsByXPath("//XCUIElementTypeButton[@label='Compose']").size()==0) {
			return true;
		}
		return false;
	}
}
