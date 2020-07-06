package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseDriver {
	
	AppiumDriver<MobileElement> driver;
	DesiredCapabilities caps;

	//Edit DeviceName if you want to use a different device. 
	//Can Get Device Name from phone going to General->About
	//Creates desired capabilities for the driver
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		String deviceName = "misha’s iPhone";
		String versionName = "";
		String udidName = "";
		Process device_process = Runtime.getRuntime().exec("instruments -s devices");
		StringBuilder device_output = new StringBuilder();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(device_process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			device_output.append(line + "\n");
		}

		int exitVal = device_process.waitFor();
		if (exitVal == 0) {
			String devices = device_output.toString();
			int dIndex = devices.indexOf(deviceName);
			if(dIndex == -1) {
				System.out.println("Invalid device");
				System.exit(0);
			}
			int vIndex = dIndex+deviceName.length();
			while(devices.charAt(vIndex) != '(') {
				vIndex+=1;
			}
			vIndex +=1;
			int vCindex = vIndex;
			while(devices.charAt(vCindex) != ')') {
				vCindex+=1;
			}
			versionName = devices.substring(vIndex,vCindex);
			int uidIndex =  dIndex+deviceName.length();
			while(devices.charAt(uidIndex) != '[') {
				uidIndex+=1;
			}
			uidIndex+=1;
			int uidCIndex = uidIndex;
			while(devices.charAt(uidCIndex) != ']') {
				uidCIndex+=1;
			}
			udidName = devices.substring(uidIndex,uidCIndex);
		}
		System.out.println(deviceName);
		System.out.println(versionName);
		System.out.println(udidName);
		
		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, versionName);
		caps.setCapability(MobileCapabilityType.UDID, udidName);
		//caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
	}
	
	//Opens standard native IOS apps given app name and constructs a new driver
	public IOSDriver openNativeApp(String key) throws MalformedURLException {
		HashMap<String,String> app_bundle = new HashMap<String,String>();
		app_bundle.put("Activity", "com.apple.Fitness");
		app_bundle.put("App Store", "com.apple.AppStore");
		app_bundle.put("Books", "com.apple.iBooks");
		app_bundle.put("Calculator", "com.apple.calculator");
		app_bundle.put("Calendar", "com.apple.mobilecal");
		app_bundle.put("Camera", "com.apple.camera");
		app_bundle.put("Clips", "com.apple.clips");
		app_bundle.put("Clock", "com.apple.mobiletimer");
		app_bundle.put("Compass", "com.apple.compass");
		app_bundle.put("Contacts", "com.apple.MobileAddressBook");
		app_bundle.put("FaceTime", "com.apple.facetime");
		app_bundle.put("Files", "com.apple.DocumentsApp");
		app_bundle.put("Find Friends", "com.apple.mobileme.fmf1");
		app_bundle.put("Fine iPhone", "com.apple.mobileme.fmip1");
		app_bundle.put("GarageBand", "com.apple.mobilegarageband");
		app_bundle.put("Health", "com.apple.Health");
		app_bundle.put("Home", "com.apple.Home");
		app_bundle.put("iCloud Drive", "com.apple.iCloudDriveApp");
		app_bundle.put("iMovie", "com.apple.iMovie");
		app_bundle.put("iTunes Store", "com.apple.MobileStore");
		app_bundle.put("iTunes U", "com.apple.itunesu");
		app_bundle.put("Mail", "com.apple.mobilemail");
		app_bundle.put("Maps", "com.apple.Maps");
		app_bundle.put("Messages", "com.apple.MobileSMS");
		app_bundle.put("Measure", "com.apple.measure");
		app_bundle.put("Music", "com.apple.Music");
		app_bundle.put("News", "com.apple.news");
		app_bundle.put("Notes", "com.apple.mobilenotes");
		app_bundle.put("Phone", "com.apple.mobilephone");
		app_bundle.put("Photos", "com.apple.mobileslideshow");
		app_bundle.put("Photo Booth", "com.apple.Photo-Booth");
		app_bundle.put("Podcasts", "com.apple.podcasts");
		app_bundle.put("Reminders", "com.apple.reminders");
		app_bundle.put("Safari", "com.apple.mobilesafari");
		app_bundle.put("Settings", "com.apple.Preferences");
		app_bundle.put("Shortcuts", "is.workflow.my.app");
		app_bundle.put("Stocks", "com.apple.stocks");
		app_bundle.put("Tips", "com.apple.tips");
		app_bundle.put("TV", "com.apple.tv");
		app_bundle.put("Videos", "com.apple.videos");
		app_bundle.put("Voice Memos", "com.apple.VoiceMemos");
		app_bundle.put("Wallet", "com.apple.Passbook");
		app_bundle.put("Watch", "com.apple.Bridge");
		app_bundle.put("Weather", "com.apple.weather");
		if(!key.equals("Safari")){
			caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, app_bundle.get(key));
		} else {
			caps.setCapability(CapabilityType.BROWSER_NAME,"safari");
		}
		HashMap<String, Object> args = new HashMap<String,Object>();

        args.put("bundleId", app_bundle.get(key));
        
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver(url,caps); 
		return (IOSDriver) driver;

        //driver.executeScript("mobile: launchApp", args);
	}
	
	//Opens apps given a bundleId and constructs a new driver. Use for 3rd party apps
	public IOSDriver openBundleID(String key) throws MalformedURLException {
		HashMap<String, Object> args = new HashMap<String,Object>();

        args.put("bundleId", key);
        
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver(url,caps); 
		driver.executeScript("mobile: launchApp", args);
		return (IOSDriver) driver;

	}
	//Simulates clicking homeButton
	public void homeButton() {
		driver.executeScript("mobile: pressButton", ImmutableMap.of("name","home"));
	}
	public void terminateApp(String bundleID) {
		driver.terminateApp(bundleID);
	}
	//Modify Later?
	@AfterTest
	public void teardown() {
		homeButton();
		//driver.closeApp();
		//driver.quit();
	}
	
}
