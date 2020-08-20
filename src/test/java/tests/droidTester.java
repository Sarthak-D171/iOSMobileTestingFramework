package tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import droid_helpers.*;
import ios_helpers.*;


public class droidTester extends BaseDriver{
	
	
	/*
	 * ANDROID CHROME NOTES: http://appium.io/docs/en/writing-running-appium/web/chromedriver/
	 * npm install appium --chromedriver_version="VERSION_NUM"
	 * WEHN STARTING SERVER: appium --chromedriver-executable /path/to/my/chromedriver
	 * 
	 */
	/*
	
	@Test
	public void androidSettingsTest() throws InterruptedException, IOException {
		driver = openAndroidApp("Settings");
		Droid_Settings_Helper y = new Droid_Settings_Helper();
		y.toggleBluetoothSamsung(driver, outputLog);
	}
	
	@Test
	public void androidSpotiy() throws InterruptedException, IOException {
		driver = openAndroidApp("Spotify");
		Droid_Spotify_Helper y = new Droid_Spotify_Helper();
		y.playSong("telegraph ave childish",driver);	
	}
	
	@Test
	public void androidPlayStore() throws InterruptedException, IOException {
		driver = openAndroidApp("PlayStore");
		Droid_Playstore_Helper y = new Droid_Playstore_Helper();
		y.downloadApp("archero",driver, outputLog);
		
	}
	
	@Test
	public void androidYoutube() throws InterruptedException, IOException {
		driver = openAndroidApp("YouTube");
		Droid_Youtube_Helper y = new Droid_Youtube_Helper();
		y.openVid("telegraph ave childish",driver);	
	}
	
	@Test
	public void androidDexcomTest() throws InterruptedException, IOException {
		driver = openAndroidApp("Dexcom");
		Droid_DexcomG6_Helper y = new Droid_DexcomG6_Helper();
		Thread.sleep(3000);
		System.out.println(driver.getPageSource());
		y.startSensorSession("7171", driver);
		//System.out.println(y.getEGVVal(driver));
		//y.navigateHome(driver);
	}

	@Test
	public void androidChromeTest() throws InterruptedException, IOException {
		driver = openAndroidApp("Chrome");
		Droid_Chrome_Helper y = new Droid_Chrome_Helper();
		y.gotoURL("http://google.com",driver, outputLog);
		
	}
	
	@Test
	public void androidFacebook() throws InterruptedException, IOException {
		driver = openAndroidApp("Facebook");
		//Droid_Spotify_Helper y = new Droid_Spotify_Helper();
		//y.playSong("telegraph ave childish",driver);	
	}
	*/
	
	@Test
	public void androidLogs() throws InterruptedException, IOException {
		driver = openAndroidApp("Settings");
		Droid_Settings_Helper y = new Droid_Settings_Helper();
		//y.enableHCILOGS(driver);
		y.bluetoothOnSamsung(driver, outputLog);
		y.toggleBluetoothSamsung(driver,outputLog);
		//Process device_process = Runtime.getRuntime().exec("adb bugreport anewbugreportfolder");
	}
	
}
