package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import droid_helpers.*;
import ios_helpers.*;


public class mainTester extends BaseDriver{
	
	
	/*
	 * ANDROID CHROME NOTES: http://appium.io/docs/en/writing-running-appium/web/chromedriver/
	 * npm install appium --chromedriver_version="VERSION_NUM"
	 * WEHN STARTING SERVER: appium --chromedriver-executable /path/to/my/chromedriver
	 * 
	 */
	
	@Test
	public void androidDexcomTest() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Dexcom");
		Droid_DexcomG6_Helper y = new Droid_DexcomG6_Helper();
		Thread.sleep(3000);
		System.out.println(driver.getPageSource());
		y.stopSensor(driver);
		//System.out.println(y.getEGVVal(driver));
		//y.navigateHome(driver);
		
	}
	/*
	@Test
	public void androidChromeTest() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Chrome");
		Droid_Chrome_Helper y = new Droid_Chrome_Helper();
		y.gotoURL("http://google.com",driver);
		
	}
	*/
	/*
	@Test
	public void androidPlayStore() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("PlayStore");
		Droid_Playstore_Helper y = new Droid_Playstore_Helper();
		y.downloadApp("archero",driver);
		
	}
	*/
	/*
	
	
	@Test
	public void androidYoutube() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("YouTube");
		Droid_Youtube_Helper y = new Droid_Youtube_Helper();
		y.openVid("telegraph ave childish",driver);	
	}
	@Test
	public void androidSpotiy() throws MalformedURLException, InterruptedException {
		driver = openAndroidApp("Spotify");
		Droid_Spotify_Helper y = new Droid_Spotify_Helper();
		y.playSong("telegraph ave childish",driver);	
	}
	
	@Test
	public void gmailTest() throws MalformedURLException, InterruptedException {
		IOS_Gmail_Helper g = new IOS_Gmail_Helper();
		driver = openBundleID("com.google.Gmail");
		g.composeEmail("sm@gmail.com","Dabc","Hi",driver);
		Thread.sleep(3000);
	}
	@Test
	public void webTest1() throws MalformedURLException, InterruptedException{
		IOS_Safari_Helper s = new IOS_Safari_Helper();
		driver = openNativeApp("Safari");
		System.out.println("whoo");
		s.googleSearch("hello",driver);
		//Thread.sleep(3000);
		//s.yahooSearch("hello", driver);
		Thread.sleep(3000);
	}
	@Test
	public void phoneTest1() throws MalformedURLException, InterruptedException {
		IOS_Phone_Helper p = new IOS_Phone_Helper();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver);
		iosHomeButton();
	}
	
	@Test
	public void dexcomBluetoothProblems() throws InterruptedException, MalformedURLException {
		driver = openBundleID("com.dexcominc.G6");
		//driver = openBundleID("com.dexcom.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		IOS_Settings_Helper s = new IOS_Settings_Helper();
		d.getEGV_N_Mins(1, driver);
		iosHomeButton();
		driver = openNativeApp("Settings");
		s.toggleBluetooth(driver);
		driver = openBundleID("com.dexcominc.G6");
		d.getEGV_N_Mins(1, driver);
		Thread.sleep(3000);
		driver = openNativeApp("Settings");
		Thread.sleep(3000);
		s.toggleBluetooth(driver);
		driver = openBundleID("com.dexcominc.G6");
	}


	@Test
	public void calcTest1() throws MalformedURLException {
		IOS_Calc_Helper c = new IOS_Calc_Helper();
		driver = openNativeApp("Calculator");
		System.out.println(driver.getPageSource());
		//c.typeNum("-3.0", driver);
		c.basicOperation("multiply","-30.6","2.5",driver);
		c.basicOperation("add", "40", "20", driver);
		c.clear(driver);
	}
	
	
	
	
		
	@Test
	public void multiTest() throws MalformedURLException, InterruptedException {
		IOS_Phone_Helper p = new IOS_Phone_Helper();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver);
		iosHomeButton();
		driver = openNativeApp("Calculator");
		IOS_Calc_Helper c = new IOS_Calc_Helper();
		c.basicOperation("add", "40", "20", driver);
		//p.callContact("Temp Temp",driver);
	}
	
	@Test
	public void settings1() throws InterruptedException, MalformedURLException {
		driver = openNativeApp("Settings");
		IOS_Settings_Helper s = new IOS_Settings_Helper();
		s.toggleBluetooth(driver);
	}
	
	@Test
	public void dexcomTest1() throws InterruptedException, MalformedURLException {
		driver = openBundleID("com.dexcominc.G6");
		//driver = openBundleID("com.dexcom.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		//d.connectnewTransmitter("8HM0X9", driver);
		System.out.println(driver.getPageSource());
		//System.out.println(d.sessionEnded(driver));
		//System.out.println(d.sessionActive(driver));
	}
	
	@Test
	public void dexcomDownloadTest() throws InterruptedException, MalformedURLException {
		driver = openNativeApp("App Store");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		IOS_AppStore_Helper a = new IOS_AppStore_Helper();
		
		a.downloadApp("vainglory", driver);
		
		driver = openBundleID("com.dexcominc.G6");
		iosHomeButton();
		d.getEGV_N_Mins(5, driver);
	}
	
	@Test
	public void appStoreTest() throws InterruptedException, MalformedURLException {
		driver = openNativeApp("App Store");
		//driver = openBundleID("com.dexcom.G6");
		IOS_AppStore_Helper a = new IOS_AppStore_Helper();
		a.downloadApp("vainglory", driver);
		//System.out.println(driver.getPageSource());
	}
	
	
	@Test
	public void dexcomStartSensor() throws MalformedURLException, InterruptedException {
		driver = openBundleID("com.dexcominc.G6");
		//driver = openBundleID("com.dexcom.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		//d.logIn(driver);
		d.startSensorSession("7171", driver);
		Thread.sleep(10000);
		while(!d.alertHandler(driver) && d.warmingUp(driver)) {
			Thread.sleep(30000); //30 sec
		}
		d.getEGV_N_Mins(10, driver);
		//d.alertHandler(driver);
		//d.endSensorSession(driver);
		//d.navigateHome(driver);
		//System.out.println(driver.getPageSource());
		//System.out.println(d.sessionError(driver));
		//System.out.println(d.sessionActive(driver));
	}
	@Test
	public void dexcomCall() throws MalformedURLException, InterruptedException {
		driver = openBundleID("com.dexcominc.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		d.getEGV_N_Mins(1, driver);
		IOS_Phone_Helper p = new IOS_Phone_Helper();
		iosHomeButton();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver);
		iosHomeButton();
		driver = openBundleID("com.dexcominc.G6");
		d.getEGV_N_Mins(10, driver);
	
	}
	@Test
	public void dexcomStress() throws MalformedURLException, InterruptedException {
		driver = openBundleID("com.dexcominc.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		d.getEGV_N_Mins(1, driver);
		Thread.sleep(10000);
		iosHomeButton();
		driver = openNativeApp("Phone");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openNativeApp("Settings");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openNativeApp("Calculator");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openNativeApp("FaceTime");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openBundleID("com.spotify.client");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openBundleID("com.google.ios.youtube");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openNativeApp("Messages");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openNativeApp("Mail");
		Thread.sleep(10000);
		iosHomeButton();
		Thread.sleep(3000);
		driver = openBundleID("com.dexcominc.G6");
		Thread.sleep(10000);
		d.getEGV_N_Mins(10, driver);
	
	}

	@Test
	public void messageTest() throws MalformedURLException {
		IOS_Messages_Helper m = new IOS_Messages_Helper();
		driver = openNativeApp("Messages");
		m.sendMsg("8582166975", "Hello", driver);
	}
	
	@Test
	public void spotifyTest() throws MalformedURLException {
		IOS_Spotify_Helper s = new IOS_Spotify_Helper();
		driver = openBundleID("com.spotify.client");
		//System.out.println(driver.getPageSource());
		s.playPlaylist("Creed Soundtrack",driver);
		//s.playSong("The Real Folk Blues",driver);
	}

	@Test
	public void snapTest() throws MalformedURLException {
		IOS_Snapchat_Helper s = new IOS_Snapchat_Helper();
		driver = openBundleID("com.toyopagroup.picaboo");
		s.sendSnap(driver);
		//System.out.println(driver.getPageSource());
	}

	
	@Test
	public void facetimeTest() throws MalformedURLException {
		IOS_FaceTime_Helper f = new IOS_FaceTime_Helper();
		driver = openNativeApp("FaceTime");
		System.out.println(driver.getPageSource());
		f.videoCall("4155830356",driver);
		
	}

	
	@Test
	public void testYoutube() throws MalformedURLException, InterruptedException {
		IOS_Youtube_Helper y = new IOS_Youtube_Helper();
		driver = openBundleID("com.google.ios.youtube");
		System.out.println(driver.getPageSource());
		y.openVid(driver);
	}
	*/
	
}
