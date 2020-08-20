package tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

import ios_helpers.*;

public class iosTester extends BaseDriver{
	@Test
	public void settings1() throws InterruptedException, IOException {
		driver = openNativeApp("Settings");
		IOS_Settings_Helper s = new IOS_Settings_Helper();
		s.bluetoothOn(driver,outputLog);
	}
	
	@Test
	public void testFacebook() throws InterruptedException, IOException {
		driver = openBundleID("com.facebook.Facebook");
		System.out.println(driver.getPageSource());
		iosScroll();
		Thread.sleep(10000);
	}
	@Test
	public void testWeather() throws InterruptedException, IOException {
		driver = openNativeApp("Weather");
		System.out.println(driver.getPageSource());
		iosScroll();
		Thread.sleep(10000);
	}
	
	
	@Test
	public void webTest1() throws InterruptedException, IOException{
		IOS_Safari_Helper s = new IOS_Safari_Helper();
		driver = openNativeApp("Safari");
		System.out.println("whoo");
		s.googleSearch("hello",driver, outputLog);
		//Thread.sleep(3000);
		//s.yahooSearch("hello", driver);
		Thread.sleep(3000);
	}
	
	
	@Test
	public void calcTest1() throws IOException {
		IOS_Calc_Helper c = new IOS_Calc_Helper();
		driver = openNativeApp("Calculator");
		System.out.println(driver.getPageSource());
		//c.typeNum("-3.0", driver);
		c.basicOperation("multiply","-30.6","2.5",driver, outputLog);
		c.basicOperation("add", "40", "20", driver, outputLog);
		c.clear(driver, outputLog);
	}
	
	@Test
	public void appStoreTest() throws InterruptedException, IOException {
		driver = openNativeApp("App Store");
		//driver = openBundleID("com.dexcom.G6");
		IOS_AppStore_Helper a = new IOS_AppStore_Helper();
		a.downloadApp("Stealth Master", driver, outputLog);
		//System.out.println(driver.getPageSource());
	}
	
	@Test
	public void gmailTest() throws InterruptedException, IOException {
		IOS_Gmail_Helper g = new IOS_Gmail_Helper();
		driver = openBundleID("com.google.Gmail");
		g.composeEmail("sm0520@dexcom.com","Dabc","Hi",driver, outputLog);
		Thread.sleep(3000);
	}
	
	@Test
	public void facetimeTest() throws InterruptedException, IOException {
		IOS_FaceTime_Helper f = new IOS_FaceTime_Helper();
		driver = openNativeApp("FaceTime");
		System.out.println(driver.getPageSource());
		f.videoCallIOS13("4155830356",driver,outputLog, 1);
	}
	
	@Test
	public void messageTest() throws InterruptedException, IOException {
		IOS_Messages_Helper m = new IOS_Messages_Helper();
		driver = openNativeApp("Messages");
		Thread.sleep(3000);
		System.out.println(driver.getPageSource());
		m.sendMsg("8582166975", "Hello", driver, outputLog);
	}
	
	@Test
	public void phoneTest1() throws InterruptedException, IOException {
		IOS_Phone_Helper p = new IOS_Phone_Helper();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver, outputLog);
		iosHomeButton();
	}
	
	@Test
	public void snapTest() throws InterruptedException, IOException {
		IOS_Snapchat_Helper s = new IOS_Snapchat_Helper();
		driver = openBundleID("com.toyopagroup.picaboo");
		s.sendSnap(driver, outputLog);
		s.openSnap(driver, outputLog);
		//System.out.println(driver.getPageSource());
	}
	
	@Test
	public void spotifyTest() throws IOException {
		IOS_Spotify_Helper s = new IOS_Spotify_Helper();
		driver = openBundleID("com.spotify.client");
		//System.out.println(driver.getPageSource());
		s.playPlaylist("Creed Soundtrack",driver, outputLog);
		//s.playSong("The Real Folk Blues",driver);
	}
	
	@Test
	public void testYoutube() throws InterruptedException, IOException {
		IOS_Youtube_Helper y = new IOS_Youtube_Helper();
		driver = openBundleID("com.google.ios.youtube");
		System.out.println(driver.getPageSource());
		y.openVid(driver, outputLog);
		Thread.sleep(10000);
	}
	
	@Test
	public void dexcomBluetoothProblems() throws InterruptedException, IOException {
		driver = openBundleID("com.dexcominc.G6");
		//driver = openBundleID("com.dexcom.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		IOS_Settings_Helper s = new IOS_Settings_Helper();
		d.getEGV_N_Mins(1, driver, outputLog);
		iosHomeButton();
		driver = openNativeApp("Settings");
		s.toggleBluetooth(driver, outputLog);
		driver = openBundleID("com.dexcominc.G6");
		d.getEGV_N_Mins(1, driver, outputLog);
		Thread.sleep(3000);
		driver = openNativeApp("Settings");
		Thread.sleep(3000);
		s.toggleBluetooth(driver, outputLog);
		driver = openBundleID("com.dexcominc.G6");
	}
		
	@Test
	public void multiTest() throws InterruptedException, IOException {
		IOS_Phone_Helper p = new IOS_Phone_Helper();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver, outputLog);
		iosHomeButton();
		driver = openNativeApp("Calculator");
		IOS_Calc_Helper c = new IOS_Calc_Helper();
		c.basicOperation("add", "40", "20", driver, outputLog);
		//p.callContact("Temp Temp",driver);
	}
	
	@Test
	public void dexcomTest1() throws InterruptedException, IOException {
		driver = openBundleID("com.dexcominc.G6");
		//driver = openBundleID("com.dexcom.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		//d.connectnewTransmitter("8HM0X9", driver);
		System.out.println(driver.getPageSource());
		//System.out.println(d.sessionEnded(driver));
		//System.out.println(d.sessionActive(driver));
	}
	
	@Test
	public void dexcomDownloadTest() throws InterruptedException, IOException {
		driver = openNativeApp("App Store");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		IOS_AppStore_Helper a = new IOS_AppStore_Helper();
		
		a.downloadApp("vainglory", driver, outputLog);
		
		driver = openBundleID("com.dexcominc.G6");
		iosHomeButton();
		d.getEGV_N_Mins(5, driver, outputLog);
	}
	
	
	@Test
	public void dexcomStartSensor() throws InterruptedException, IOException {
		driver = openBundleID("com.dexcominc.G6");
		//driver = openBundleID("com.dexcom.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		//d.logIn(driver);
		d.startSensorSession("7171", driver, outputLog);
		Thread.sleep(10000);
		while(!d.alertHandler(driver, outputLog) && d.warmingUp(driver)) {
			Thread.sleep(30000); //30 sec
		}
		d.getEGV_N_Mins(10, driver, outputLog);
		//d.alertHandler(driver);
		//d.endSensorSession(driver);
		//d.navigateHome(driver);
		//System.out.println(driver.getPageSource());
		//System.out.println(d.sessionError(driver));
		//System.out.println(d.sessionActive(driver));
	}
	@Test
	public void dexcomCall() throws InterruptedException, IOException {
		driver = openBundleID("com.dexcominc.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		d.getEGV_N_Mins(1, driver, outputLog);
		IOS_Phone_Helper p = new IOS_Phone_Helper();
		iosHomeButton();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver, outputLog);
		iosHomeButton();
		driver = openBundleID("com.dexcominc.G6");
		d.getEGV_N_Mins(10, driver, outputLog);
	
	}
	@Test
	public void dexcomStress() throws InterruptedException, IOException {
		driver = openBundleID("com.dexcominc.G6");
		IOS_DexomcG6_Helper d = new IOS_DexomcG6_Helper();
		d.getEGV_N_Mins(1, driver, outputLog);
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
		d.getEGV_N_Mins(10, driver, outputLog);
	
	}
	
}
