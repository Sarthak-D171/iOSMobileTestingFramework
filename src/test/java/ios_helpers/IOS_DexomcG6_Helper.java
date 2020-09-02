package ios_helpers;

import java.util.HashMap;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;    

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class IOS_DexomcG6_Helper {
	
	// INCOMPLETE
	// FUNCTION THAT AUTOMATES LOG IN PROCESS
	public void logIn(AppiumDriver<MobileElement> driver) {
		//System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Log in Later']").click();
	}
	
	// PRINT EGV VALUE FOR N MINS AND PRINT ANY ALERTS
	public void getEGV_N_Mins(int mins,AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			alertHandler(driver,outputLog);
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
	
	// UNTESTED
	// Connects to a new Transmitter. Should only be possible if session inactive?
	public void connectNewTransmitter(String transcode, String sensorcode, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			alertHandler(driver,outputLog);
			navigateHome(driver);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Settings']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@label='Transmitter']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@label='Pair New']").click();
			Thread.sleep(1000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Manually']").click();
			Thread.sleep(1000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeTextField").sendKeys(transcode);
			Thread.sleep(1000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Save']").click();
			Thread.sleep(1000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Confirm']").click();
			Thread.sleep(10000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Code']").click();
			Thread.sleep(1000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Manually']").click();
			Thread.sleep(1000);
			System.out.println(driver.getPageSource());
			for(int i =0;i<sensorcode.length();i++) {
				driver.findElement(By.name(String.valueOf(sensorcode.charAt(i)))).click();
			}
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Save']").click();
			Thread.sleep(1000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Confirm']").click();
			Thread.sleep(10000);
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Next']").click();
			System.out.println(driver.getPageSource());
			alertHandler(driver,outputLog);
			while(connectingTransmitter(driver)) {
				Thread.sleep(10000);
			}
			alertHandler(driver,outputLog);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Start Sensor']").click();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Connected New Transmitter");
			outputLog.newLine();
		} catch(NoSuchElementException e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Invalid Locator, Double check the Seleneium Selectors");
			outputLog.newLine();
			System.out.println("Invalid Locator, Double check the Seleneium Selectors");
		}
		
	}
	
	// Starts a new Sensor Session if sensor is currently inactive. 
	public void startSensorSession(String code, AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws InterruptedException, IOException {
		try {
			alertHandler(driver,outputLog);
			navigateHome(driver);
			
			if(sessionInactive(driver)) {
				driver.findElementByXPath("//XCUIElementTypeButton[@label='New Sensor']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Code']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Manually']").click();
				Thread.sleep(1000);
				System.out.println(driver.getPageSource());
				for(int i =0;i<code.length();i++) {
					Thread.sleep(1000);
					driver.findElement(By.name(String.valueOf(code.charAt(i)))).click();
				}
				Thread.sleep(1000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Save']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Confirm']").click();
				Thread.sleep(10000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Next']").click();
				Thread.sleep(10000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Start Sensor']").click();
				
				
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				outputLog.write(dtf.format(now)+" Started New Sensor Session");
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
	
	// Gets an EGV Value one time. IF we have an error obstructing getting an EGV Value, prints that error
	public String getEGVVal(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		alertHandler(driver,outputLog);
		navigateHome(driver);
		if(sessionActive(driver)) {
			if(driver.findElementsByXPath("//XCUIElementTypeOther[@name='EGVCell']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther").size()>0) {
				WebElement egv = driver.findElementByXPath("//XCUIElementTypeOther[@name='EGVCell']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
				String s = egv.getAttribute("name");
				String val = s.split("\\s+")[0];
				return val;
			}
		}
		identifyError(driver, outputLog);
		return "Failed";
	}
	
	
	// Ends a Sensor Session. Requires that a session is not inactive.
	public void endSensorSession(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException, InterruptedException {
		try {
			alertHandler(driver,outputLog);
			navigateHome(driver);
			if(!sessionInactive(driver)) {
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Settings']").click();
				Thread.sleep(1000);
				RemoteWebElement sens = driver.findElementByXPath("//XCUIElementTypeTable");
				String parentID = sens.getId();
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", parentID);
				//scrollObject.put("direction", "down");
				scrollObject.put("predicateString", "label == 'Stop Sensor'");
				driver.executeScript("mobile:scroll", scrollObject);
				Thread.sleep(1000);
				driver.findElementByXPath("//XCUIElementTypeStaticText[@label='Stop Sensor']").click();
				Thread.sleep(1000);
				driver.findElementByXPath("//XCUIElementTypeButton[@label='Stop Sensor']").click();
				System.out.println(driver.getPageSource());
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				outputLog.write(dtf.format(now)+" Ended Sensor Session");
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
	
	
	// Function that checks for all errors and prints out what error 
	// we got if there is an error. Should check every loop.
	public boolean identifyError(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		alertHandler(driver,outputLog);
		if(sessionActive(driver)) {
			System.out.println("No Error");
			return false;
		}
		else if(sensorError(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Sensor Error");
			outputLog.newLine();
			System.out.println("Sensor Error");
		}
		else if(bluetoothError(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Bluetooth Error");
			outputLog.newLine();
			System.out.println("Bluetooth Error");
		}
		else if(signalLoss(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Signal Loss");
			outputLog.newLine();
			System.out.println("Signal Loss");
		}
		else if(sessionEnded(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Session Ended");
			outputLog.newLine();
			System.out.println("Session Ended");
		}
		return true;
	}
	
	/*
	 * The Following are all boolean methods to determine what state the App is in.
	 * 
	 * We can use these boolean methods to make appropriate decisions on what are test should do next.
	 * In addition, this will let us know if we have somehow encountered an error.
	 */
	
	// COMPLETE
	public boolean sensorError(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Sensor Error']").size()>0;
	}
	public boolean signalLoss(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Signal Loss']").size()>0;
	}
	// COMPLETE
	public boolean sessionActive(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return !sessionInactive(driver) && !warmingUp(driver) && !sensorError(driver) && !bluetoothError(driver) && !signalLoss(driver) && !sessionEnded(driver) && !connectingTransmitter(driver);
	}
	// COMPLETE
	public boolean sessionEnded(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Session Ended']").size()>0;
	}
	// COMPLETE
	public boolean sessionInactive(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='New Sensor']").size()>0;
	}
	//COMPLETE
	public boolean bluetoothError(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Bluetooth Off']").size()>0;
	}
	public boolean connectingTransmitter(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		List<MobileElement> m = driver.findElementsByXPath("//XCUIElementTypeStaticText");
		for(MobileElement ele : m) {
			String s = ele.getAttribute("label");
			if(s!= null && s.contains("Connecting with")) {
				return true;
			}
		}
		return false;
	}
	
	// COMPLETE
	public boolean warmingUp(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Sensor Warmup']").size()>0
				&& driver.findElementsByXPath("//XCUIElementTypeOther[@name='sensor_warmup_circle']").size()>0;
	}
	

	
	/*
	 * Everything from this point down has to do with alerts. 
	 * 
	 * We have an alert handler that prints the type of alert on screen
	 * or if no alert does nothing. This will be the main method we use 
	 * to identify alerts during tests and handle them.
	 * 
	 * Each method below the alert handler is a boolean function
	 * that returns True if the alert is visible, or False otherwise.
	 * 
	 * For Future, updates. Add a boolean method to check if alert is on screen.
	 * Once you confirm that the method to check for alert works. Add it into the 
	 * alert handler. 
	 * 	
	 */
	
	// COMPLETE (add alerts as necessary)
	public boolean alertHandler(AppiumDriver<MobileElement> driver, BufferedWriter outputLog) throws IOException {
		boolean validAlert = false;
		if(warmupAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Got Warmup Alert");
			outputLog.newLine();
			System.out.println("Got Warmup Alert");
			validAlert = true;
		}
		if(lowAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Got Low Alert");
			outputLog.newLine();
			System.out.println("Got Low Alert");
			validAlert = true;
		}
		if(highAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Got High Alert");
			outputLog.newLine();
			System.out.println("Got High Alert");
			validAlert = true;
		}
		if(noReadingAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Got No Reading Alert");
			outputLog.newLine();
			System.out.println("Got No Reading Alert");
			validAlert = true;
		}
		if(bluetoothPairingAlert(driver)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			outputLog.write(dtf.format(now)+" Got Bluetooth Pairing Alert");
			outputLog.newLine();
			System.out.println("Bluetooth Pairing");
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Pair']").click();
			return true;
		}
		if(driver.findElementsByXPath("//XCUIElementTypeButton[@label='OK']").size()>0) {
			if(!validAlert) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				outputLog.write(dtf.format(now)+" Got Unidentified Alert");
				outputLog.newLine();
				System.out.println("Unidentified Alert");
			}
			driver.findElementByXPath("//XCUIElementTypeButton[@label='OK']").click();
			return true;
		}
		return false;
		
	}
	
	//COMPLETE
	public boolean bluetoothPairingAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='Pair']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeStaticText[@name='Bluetooth Pairing Request']").size()>0;
	}
	// COMPLETE
	public boolean warmupAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='OK']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeStaticText[@name='Sensor Warmup']").size()>0;
	}
	// COMPLETE
	public boolean lowAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='OK']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeStaticText[@name='Urgent Low Soon Alert']").size()>0;
	}
	// COMPLETE
	public boolean highAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='OK']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeStaticText[@name='High Glucose Alert']").size()>0;
	}
	// COMPLETE
	public boolean noReadingAlert(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='OK']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeStaticText[@name='No Readings Alert']").size()>0;
	}
	
	/*
	 * 
	 * Collection of methods that always brings us back to the home Screen. 
	 * 
	 * Important since all information and methods are written to be directed from the home screen.
	 * 
	 * Haven't found a case where navigateHome breaks. But if you do pls update accordingly.
	 */
	//COMPLETE
	public boolean isHome(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='Share']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeButton[@label='Events']").size()>0
				&& driver.findElementsByXPath("//XCUIElementTypeButton[@label='Settings']").size()>0;
	}
	// COMPLETE
	public void navigateHome(AppiumDriver<MobileElement> driver) {
		//System.out.println(isHome(driver));
		if(!isHome(driver)) {
			System.out.println("BREAKKKKK");
			System.out.println(driver.getPageSource());
		}
		while(!isHome(driver)) {
			if(driver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton").size()>0)
				driver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton").click();
			else
				return;
		}
	}
	
}
