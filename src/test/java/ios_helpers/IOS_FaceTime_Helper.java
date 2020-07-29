package ios_helpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_FaceTime_Helper extends BaseDriver{
	public void videoCall(String num, AppiumDriver<MobileElement> driver) {
		try {
			driver.findElementByXPath("//XCUIElementTypeButton[@name='Video']").click();
			driver.findElementByXPath("//XCUIElementTypeSearchField").click();
			driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(num);
			driver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[@name='FaceTime']").click();
			disconnectCall(driver);
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public boolean disconnectCall(AppiumDriver<MobileElement> driver) {
		try {
			boolean isCalling = false;
			List<MobileElement> ele = driver.findElementsByXPath("//XCUIElementTypeButton");
			for(int i =0; i<ele.size();i++) {
				if(ele.get(i).getAttribute("name") != null && ele.get(i).getAttribute("name").equals("End call")) {
					ele.get(i).click();
					isCalling = true;
					break;
				}
			}
			return isCalling;
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
			return false;
		}
	}
}
