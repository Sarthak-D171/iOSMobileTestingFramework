package tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppStore_Helper {
	public void downloadApp(String appName, AppiumDriver<MobileElement> driver) throws InterruptedException {
		driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
		driver.findElementByXPath("//XCUIElementTypeSearchField").sendKeys(appName);
		driver.findElementByXPath("//XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[@label='Search']").click();
		Thread.sleep(3000);
		System.out.println(driver.getPageSource());
		if(driver.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='Redownload']").size()>0)
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='Redownload']").click();
		else if(driver.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET']").size()>0) {
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET']").click();
			System.out.println(driver.getPageSource());
			Thread.sleep(3000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
		}
		else {
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeButton[@name='GET, In-App Purchases']").click();
			System.out.println(driver.getPageSource());
			Thread.sleep(3000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Install']").click();
		}
		//driver.findElementByXPath("//XCUIElementTypeTextField[@label='To:']").sendKeys(number);
		//System.out.println(driver.getPageSource());
	}
}
