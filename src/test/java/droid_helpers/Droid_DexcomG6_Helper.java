package droid_helpers;

import org.openqa.selenium.By;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;    
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Droid_DexcomG6_Helper {
	
	public void getEGVValNMins(int mins, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			identifyError(driver, outputLog);
			long finish = System.currentTimeMillis() + mins*60*1000; // end time
			while (sessionActive(driver) && System.currentTimeMillis() < finish) {
				alertHandler(driver, outputLog);
				String val = getEGVVal(driver, outputLog);
				System.out.println(val);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				outputLog.write(dtf.format(now)+" Reading EGV Value as: "+ val);
				outputLog.newLine();
				Thread.sleep(30000); //30 sec
			}
		}
		catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	public String getEGVVal(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		navigateHome(driver);
		if(sessionActive(driver)) {
			//WebElement egv = driver.findElement(By.id("com.dexcom.g6:id/textViewEGV"));
			WebElement egv = driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.TextView[@index='1']");
			String ret = egv.getAttribute("text");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			System.out.println(dtf.format(now)); 
			return ret;
		} else {
			identifyError(driver,outputLog);
			return "Failed";
		}
	}
	
	public void connectNewTransmitter(String transcode, String sensorcode, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			navigateHome(driver);
			
			driver.findElementByXPath("//android.widget.TextView[@text='SETTINGS']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.TextView[@text='Transmitter']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.TextView[@text='Pair New']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Button[@text='ENTER MANUALLY']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.EditText").sendKeys(transcode);
			driver.findElementByXPath("//android.widget.TextView[@text='SAVE']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Button[@text='CONFIRM']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Button[@text='ENTER CODE']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Button[@text='ENTER MANUALLY']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.EditText").sendKeys(sensorcode);
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.TextView[@text='SAVE']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Button[@text='CONFIRM']").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.Button[@text='NEXT']").click();
			Thread.sleep(3000);
			
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Connected New Transmitter");
			outputLog.newLine();
			
			while(connectingTransmitter(driver)) {
				Thread.sleep(10000);
			}
			
			driver.findElementByXPath("//android.widget.TextView[@text='Start Sensor']").click();
		}	catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}

	public void stopSensor(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			navigateHome(driver);
			if(!sessionInactive(driver)) {
				driver.findElementByXPath("//android.widget.TextView[@text='SETTINGS']").click();
				((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+"Stop Sensor"+"\").instance(0))").click();
				driver.findElementByXPath("//android.widget.Button[@text='STOP SENSOR']").click();
				if(driver.findElementsByXPath("//android.widget.Button[@text='OK']").size() >0) {
					driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
				}
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				outputLog.write(dtf.format(now)+" Stopped Sensor");
				outputLog.newLine();
			}
			
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	public void startSensorSession(String code, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			navigateHome(driver);
			if(sessionInactive(driver)) {
				driver.findElementByXPath("//android.widget.TextView[@text='New Sensor']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.Button[@text='ENTER CODE']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.Button[@text='ENTER MANUALLY']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.EditText").sendKeys(code);
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.TextView[@text='SAVE']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.Button[@text='CONFIRM']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.Button[@text='NEXT']").click();
				Thread.sleep(3000);
				driver.findElementByXPath("//android.widget.TextView[@text='Start Sensor']").click();
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				outputLog.write(dtf.format(now)+" Started Sensor Session");
				outputLog.newLine();
			}
		}	catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
	}
	
	
	public boolean identifyError(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		if(bluetoothError(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" BlueTooth Error");
			outputLog.newLine();
			System.out.println("Bluetooth Error");
			return true;
		}
		else if(signalLoss(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Signal Loss");
			outputLog.newLine();
			System.out.println("Signal Loss");
			return true;
		}
		else if(sensorError(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sensor Error");
			outputLog.newLine();
			System.out.println("Sensor Error");
			return true;
		}
		else if(sessionEnded(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Session Ended");
			outputLog.newLine();
			System.out.println("Session Ended");
			return true;
		}
		return false;
	}
	
	public boolean bluetoothError(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Bluetooth Off']").size() >0;
	}
	public boolean signalLoss(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Signal Loss']").size() >0; 
	}
	public boolean sensorError(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Sensor Error']").size() >0; 
	}
	public boolean sessionEnded(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Session Ended']").size() >0; 
	}
	public boolean sessionInactive(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='New Sensor']").size() >0; 
	}
	public boolean sessionActive(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return !sessionInactive(driver) && !warmingUp(driver) && !sensorError(driver) && !bluetoothError(driver) && !signalLoss(driver) && !sessionEnded(driver) && !connectingTransmitter(driver);
	}
	public boolean warmingUp(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Sensor Warmup']").size() >0;
	}
	public boolean connectingTransmitter(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Connecting with Transmitter']").size() >0; 
	}
	
	
	
	
	//resource-id="android:id/alertTitle" potential way to find alerts ??
	public boolean alertHandler(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		if(sensorWarmupAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sensor Warmup Alert");
			outputLog.newLine();
			System.out.println("Sensor Warmup");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(urgentLowAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sensor Urgent Low Alert");
			outputLog.newLine();
			System.out.println("Urgent Low");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(urgentLowSoonAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Urgent Low Soon Alert");
			outputLog.newLine();
			System.out.println("Urgent Low Soon");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(lowAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sensor Low Alert");
			outputLog.newLine();
			System.out.println("Low");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(highAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sensor High Alert");
			outputLog.newLine();
			System.out.println("High");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(driver.findElements(By.id("android:id/alertTitle")).size() >0) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Unspecified Alert");
			outputLog.newLine();
			System.out.println("Unspecififed");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		return false;
	}
	
	public boolean sensorWarmupAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//android.widget.TextView[@text='Sensor warmup successful and setup complete. Sensor glucose readings now available.']").size() >0;
	}
	
	public boolean urgentLowSoonAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//android.widget.TextView[@text='Urgent Low Soon Alert']").size() >0;
	}
	public boolean urgentLowAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//android.widget.TextView[@text='Urgent Low Glucose Alarm']").size() >0;
	}
	
	public boolean highAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//android.widget.TextView[@text='High Glucose Alert']").size() >0;
	}
	public boolean lowAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//android.widget.TextView[@text='Low Glucose Alert']").size() >0;
	}
		
	public boolean isHome(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='SETTINGS']").size() >0 
				&& driver.findElementsByXPath("//android.widget.TextView[@text='SHARE']").size() >0 
				&& driver.findElementsByXPath("//android.widget.TextView[@text='Events']").size() >0 ) {
			return true;
			
		}
		return false;
	}
	
	public void navigateHome(AppiumDriver<MobileElement> driver) throws InterruptedException {
		while(!isHome(driver)) {
			driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']").click();
			Thread.sleep(1000);
		}
	}

}
