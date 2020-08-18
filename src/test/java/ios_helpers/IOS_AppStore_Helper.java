package ios_helpers;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IOS_AppStore_Helper {
	public void downloadApp(String appName, AppiumDriver<MobileElement> driver) throws InterruptedException {
		try {
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(appName);
			if(driver.findElementsByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='Search']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='Search']").click();
			else
				driver.findElementByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='search']").click();
			Thread.sleep(3000);
			System.out.println(driver.getPageSource());
			if(driver.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='Redownload']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='Redownload']").click();
			else if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='redownload']").size()>0)
				driver.findElementByXPath("//XCUIElementTypeButton[@name='redownload']").click();
			else if(driver.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET']").click();
				System.out.println(driver.getPageSource());
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			else if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='get']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeButton[@name='get']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			else if(driver.findElementsByXPath("//XCUIElementTypeButton[@name='get, in-app purchases']").size()>0) {
				driver.findElementByXPath("//XCUIElementTypeButton[@name='get, in-app purchases']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
			else {
				driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET, In-App Purchases']").click();
				System.out.println(driver.getPageSource());
				Thread.sleep(3000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
			}
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
		//driver.findElementByXPath("//XCUIElementTypeTextField[@label='To:']").sendKeys(number);
		//System.out.println(driver.getPageSource());
	}
}
