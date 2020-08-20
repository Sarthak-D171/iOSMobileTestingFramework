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
	public void androidSpotiy() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Spotify");
		Droid_Spotify_Helper y = new Droid_Spotify_Helper();
		y.playSong("telegraph ave childish",driver);	
	}
	
	@Test
	public void androidPlayStore() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("PlayStore");
		Droid_Playstore_Helper y = new Droid_Playstore_Helper();
		y.downloadApp("archero",driver);
		
	}
	
	@Test
	public void androidSettingsTest() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Settings");
		Droid_Settings_Helper y = new Droid_Settings_Helper();
		y.toggleBluetoothSamsung(driver);
		
	}
	@Test
	public void androidYoutube() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("YouTube");
		Droid_Youtube_Helper y = new Droid_Youtube_Helper();
		y.openVid("telegraph ave childish",driver);	
	}
	
	@Test
	public void androidDexcomTest() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Dexcom");
		Droid_DexcomG6_Helper y = new Droid_DexcomG6_Helper();
		Thread.sleep(3000);
		System.out.println(driver.getPageSource());
		y.startSensorSession("7171", driver);
		//System.out.println(y.getEGVVal(driver));
		//y.navigateHome(driver);
	}

	@Test
	public void androidChromeTest() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Chrome");
		Droid_Chrome_Helper y = new Droid_Chrome_Helper();
		y.gotoURL("http://google.com",driver);
		
	}
	
	@Test
	public void androidFacebook() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Facebook");
		//Droid_Spotify_Helper y = new Droid_Spotify_Helper();
		//y.playSong("telegraph ave childish",driver);	
	}
	*/
	
	@Test
	public void androidLogs() throws InterruptedException, IOException {
		driver = openAndroidApp("Settings");
		Droid_Settings_Helper y = new Droid_Settings_Helper();
		y.enableHCILOGS(driver);
		y.toggleBluetoothSamsung(driver);
		y.toggleBluetoothSamsung(driver);
		Process device_process = Runtime.getRuntime().exec("adb bugreport anewbugreportfolder");
	}
	/*
	@Test
	public void androidSettingsTest() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Settings");
		Droid_Settings_Helper y = new Droid_Settings_Helper();
		y.enableHCILOGS(driver);
		
	}*/
	
}
