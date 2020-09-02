package ios_helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import tests.BaseDriver;

public class IOS_Spotify_Helper extends BaseDriver{
	public void playSong(String search, int mins, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Home']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			
			driver.findElement(By.name("searchBar")).clear();
			Thread.sleep(1000);
			driver.findElement(By.name("searchBar")).sendKeys(search);
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCell/XCUIElementTypeButton").click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Playing Song");
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
	public void playPlaylist(String search, int mins, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try {
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Home']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@label='Search']").click(); 
			Thread.sleep(1000);
			
			driver.findElement(By.name("searchBar")).clear();
			Thread.sleep(1000);
			driver.findElement(By.name("searchBar")).sendKeys(search);
			Thread.sleep(1000);
			driver.findElement(By.name("Return")).click();
			Thread.sleep(1000);

			driver.findElement(By.name("See all playlists")).click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='SHUFFLE PLAY']").click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Playing Playlist");
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
