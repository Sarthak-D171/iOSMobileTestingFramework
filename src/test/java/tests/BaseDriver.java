package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseDriver {
	
	AppiumDriver<MobileElement> driver;
	DesiredCapabilities caps;
	String type;
	String deviceName;
	String versionName;
	String udidName;
	protected BufferedWriter outputLog;
	
	/*
	*
	* Edit DeviceName and type if you want to use a different device. 
	* Can Get Device Name from phone going to General->About OR
	* Running instruments -s devices (IOS) OR adb devices (ANDROID) in terminal
	* 
	* Function runs before every test and sets the base desired
	* capabilities that the driver will use. Make sure that the device
	* name string is accurate as well as the type String as those will
	* be used to appropriately get the rest of the device capabilities.
	*
	* TODO: Make type and device name program arguments
	*/
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		ArrayList<String> phone_info = new ArrayList<String>();
		try {
	      File myObj = new File("src/test/java/tests/config.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        phone_info.add(myReader.nextLine());
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		type = phone_info.get(0); //EDIT TO MAKE SURE THIS IS CORRECT FOR YOUR DEVICE
		String deviceName = phone_info.get(1); //EDIT TO MAKE SURE THIS IS CORRECT FOR YOUR DEVICE
		String versionName = phone_info.get(2);
		String udidName = phone_info.get(3);
		outputLog = new BufferedWriter(new FileWriter("Test_OutPut_Log"));
		if(type.equals("IOS")) {
			/*
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
				System.out.println(deviceName);
				System.out.println(versionName);
				System.out.println(udidName);
			}
				*/
			caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, type);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, versionName);
			caps.setCapability(MobileCapabilityType.UDID, udidName);
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1800);
		} else {
			deviceName = "Samsung S8";
			udidName = "988c1d474344434f56";
			caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, type);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung S8");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
			caps.setCapability(MobileCapabilityType.UDID, "988c1d474344434f56");
			caps.setCapability(MobileCapabilityType.NO_RESET, true);
			caps.setCapability(MobileCapabilityType.FULL_RESET, false);
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1800);
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		outputLog.write(dtf.format(now)+ " Connected to: "+deviceName+" "+udidName);
		outputLog.newLine();
		
	}
	
	/*
	 * ONLY USABLE WITH DEFAULT IOS APPS. 
	 * If you are having issues then double check the app_bundle dictionary and see if your app is included.
	 * If your app is not included then use openBundleId or add the app to app_bundle dictionary. 
	 * 
	 * 
	 * Constructs and returns the final usable driver. 
	 * This usable driver will be used in conjunction with the Helper methods.
	 * 
	 * 
	 */
	public IOSDriver openNativeApp(String key) throws IOException {
		HashMap<String,String> app_bundle = new HashMap<String,String>();
		app_bundle.put("activity", "com.apple.Fitness");
		app_bundle.put("appstore", "com.apple.AppStore");
		app_bundle.put("books", "com.apple.iBooks");
		app_bundle.put("calculator", "com.apple.calculator");
		app_bundle.put("calendar", "com.apple.mobilecal");
		app_bundle.put("camera", "com.apple.camera");
		app_bundle.put("clips", "com.apple.clips");
		app_bundle.put("clock", "com.apple.mobiletimer");
		app_bundle.put("compass", "com.apple.compass");
		app_bundle.put("contacts", "com.apple.MobileAddressBook");
		app_bundle.put("facetime", "com.apple.facetime");
		app_bundle.put("files", "com.apple.DocumentsApp");
		app_bundle.put("findfriends", "com.apple.mobileme.fmf1");
		app_bundle.put("fineiphone", "com.apple.mobileme.fmip1");
		app_bundle.put("garageband", "com.apple.mobilegarageband");
		app_bundle.put("health", "com.apple.Health");
		app_bundle.put("home", "com.apple.Home");
		app_bundle.put("iclouddrive", "com.apple.iCloudDriveApp");
		app_bundle.put("imovie", "com.apple.iMovie");
		app_bundle.put("itunesstore", "com.apple.MobileStore");
		app_bundle.put("itunesu", "com.apple.itunesu");
		app_bundle.put("mail", "com.apple.mobilemail");
		app_bundle.put("maps", "com.apple.Maps");
		app_bundle.put("messages", "com.apple.MobileSMS");
		app_bundle.put("measure", "com.apple.measure");
		app_bundle.put("music", "com.apple.Music");
		app_bundle.put("news", "com.apple.news");
		app_bundle.put("notes", "com.apple.mobilenotes");
		app_bundle.put("phone", "com.apple.mobilephone");
		app_bundle.put("photos", "com.apple.mobileslideshow");
		app_bundle.put("photobooth", "com.apple.Photo-Booth");
		app_bundle.put("podcasts", "com.apple.podcasts");
		app_bundle.put("reminders", "com.apple.reminders");
		app_bundle.put("safari", "com.apple.mobilesafari");
		app_bundle.put("settings", "com.apple.Preferences");
		app_bundle.put("shortcuts", "is.workflow.my.app");
		app_bundle.put("stocks", "com.apple.stocks");
		app_bundle.put("tips", "com.apple.tips");
		app_bundle.put("tv", "com.apple.tv");
		app_bundle.put("videos", "com.apple.videos");
		app_bundle.put("voicememos", "com.apple.VoiceMemos");
		app_bundle.put("wallet", "com.apple.Passbook");
		app_bundle.put("watch", "com.apple.Bridge");
		app_bundle.put("weather", "com.apple.weather");
		if(!key.equals("safari")){
			caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, app_bundle.get(key));
		} else {
			caps.setCapability(CapabilityType.BROWSER_NAME,"safari");
		}
		HashMap<String, Object> args = new HashMap<String,Object>();

        args.put("bundleId", app_bundle.get(key));
        
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver(url,caps); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		outputLog.write(dtf.format(now)+" Launched: "+key);
		outputLog.newLine();
		return (IOSDriver) driver;

        //driver.executeScript("mobile: launchApp", args);
	}
	
	/*
	 * ONLY USABLE FOR IOS.
	 * 
	 * Opens an app given the correct bundleID. 
	 * 
	 * The following link can be used to get the bundleID of any IOS APP installed on the test device.
	 * https://stackoverflow.com/questions/27509838/how-to-get-bundle-id-of-ios-app-either-using-ipa-file-or-app-installed-on-iph
	 */
	public IOSDriver openBundleID(String key) throws IOException {
		HashMap<String, Object> args = new HashMap<String,Object>();

        args.put("bundleId", key);
        
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver(url,caps); 
		driver.executeScript("mobile: launchApp", args);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		outputLog.write(dtf.format(now)+" Launched: "+ key);
		outputLog.newLine();
		return (IOSDriver) driver;

	}
	
	public AndroidDriver openAndroidApp(String name) throws IOException {
		HashMap<String,String[]> app_bundle = new HashMap<String,String[]>();
		app_bundle.put("Dexcom",new String[]{"com.dexcom.g6", "com.dexcom.cgm.activities.AppCompatabilityActivity"});
		app_bundle.put("Spotify",new String[]{"com.spotify.music", "com.spotify.music.MainActivity"});
		app_bundle.put("PlayStore",new String[]{"com.android.vending", "com.google.android.finsky.activities.MainActivity"});
		app_bundle.put("YouTube",new String[]{"com.google.android.youtube", "com.google.android.youtube.HomeActivity"});
		app_bundle.put("SnapChat",new String[]{"com.snapchat.android", "com.snap.mushroom.MainActivity"});
		app_bundle.put("Settings",new String[]{"com.android.settings", "com.android.settings.Settings"});
		app_bundle.put("Facebook", new String[]{"com.facebook.katana", "com.facebook.katana.LoginActivity"});
		
		
		/*
		 * 
		 * CHROME NOTES: http://appium.io/docs/en/writing-running-appium/web/chromedriver/
		 * npm install appium --chromedriver_version="VERSION_NUM"
		 * WEHN STARTING SERVER: appium --chromedriver-executable /path/to/my/chromedriver
		 */
		if(name.equals("Chrome")) {
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		} else {
			caps.setCapability("appPackage", app_bundle.get(name)[0]);
			caps.setCapability("appActivity",app_bundle.get(name)[1]);
		}
		//caps.setCapability(capabilityName, value);
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(url,caps);
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		outputLog.write(dtf.format(now)+" Opened: "+ name);
		outputLog.newLine();
		
		return (AndroidDriver) driver;
		
	}
	
	/*
	 *  SIMULATES CLICKING HOME BUTTON
	 *  
	 *  Call during tests so that u exit and put an app in the background
	 *  before opening a new app. 
	 */
	public void iosScroll(String dir) {
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", dir);
		driver.executeScript("mobile:scroll", scrollObject);
	}
	public void iosHomeButton() throws IOException {
		driver.executeScript("mobile: pressButton", ImmutableMap.of("name","home"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		outputLog.write(dtf.format(now)+" Pressed Home Button");
		outputLog.newLine();
	}
	
	public void androidHomeButton() {
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
	}
	
	/*
	 * RUNS AFTER EVERY TEST
	 *
	 */
	@AfterTest
	public void teardown() throws IOException {
		if(type.equals("iOS")) iosHomeButton();
		else if (type.equals("android")) androidHomeButton();
		outputLog.close();
		//driver.closeApp();
		//driver.quit();
	}
	
}
