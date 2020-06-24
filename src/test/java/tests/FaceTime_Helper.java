package tests;

import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class FaceTime_Helper extends BaseDriver{
	public void videoCall(String num, AppiumDriver<MobileElement> driver) {
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Video']").click();
		driver.findElementByXPath("//XCUIElementTypeSearchField").click();
		driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(num);
		driver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[@name='FaceTime']").click();
		disconnectCall(driver);
	}
	public boolean disconnectCall(AppiumDriver<MobileElement> driver) {
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
	}
}
