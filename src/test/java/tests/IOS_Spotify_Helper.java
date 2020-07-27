package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IOS_Spotify_Helper extends BaseDriver{
	public void playSong(String search, AppiumDriver<MobileElement> driver) {
		try {
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Home']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			System.out.println(driver.getPageSource());
			driver.findElement(By.name("searchBar")).clear();
			driver.findElement(By.name("searchBar")).sendKeys(search);
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCell/XCUIElementTypeButton").click();
			System.out.println(driver.getPageSource());
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public void playPlaylist(String search, AppiumDriver<MobileElement> driver) {
		try {
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Home']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click(); 
			System.out.println(driver.getPageSource());
			//driver.findElementByXPath("//XCUIElementTypeButton[@label='Cancel']").click();
			driver.findElement(By.name("searchBar")).clear();
			driver.findElement(By.name("searchBar")).sendKeys(search);
			driver.findElement(By.name("Return")).click();
			System.out.println(driver.getPageSource());
			driver.findElement(By.name("See all playlists")).click();
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton").click();
			driver.findElementByXPath("//XCUIElementTypeButton[@label='SHUFFLE PLAY']").click();
		} catch(NoSuchElementException e) {
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
		
}
