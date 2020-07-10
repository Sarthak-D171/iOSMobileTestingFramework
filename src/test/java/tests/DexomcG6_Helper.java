package tests;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class DexomcG6_Helper {
	// INCOMPLETE
	public void logIn(AppiumDriver<MobileElement> driver) {
		//System.out.println(driver.getPageSource());
		driver.findElementByXPath("//XCUIElementTypeButton[@label='Log in Later']").click();
	}
	// COMPLETE
	public void getEGV_N_Mins(int mins,AppiumDriver<MobileElement> driver) throws InterruptedException {
		identifyError(driver);
		long finish = System.currentTimeMillis() + mins*60*1000; // end time
		while (sessionActive(driver) && System.currentTimeMillis() < finish) {
			alertHandler(driver);
			int val = getEGVVal(driver);
			System.out.println(val);
			Thread.sleep(30000); //30 sec
		}	
	}
	//COMPLETE
	public void startSensorSession(String code, AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		if(!sessionActive(driver)) {
			driver.findElementByXPath("//XCUIElementTypeButton[@label='New Sensor']").click();
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Code']").click();
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Enter Manually']").click();
			System.out.println(driver.getPageSource());
			for(int i =0;i<code.length();i++) {
				driver.findElement(By.name(String.valueOf(code.charAt(i)))).click();
			}
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Save']").click();
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Confirm']").click();
			System.out.println(driver.getPageSource());
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Next']").click();
			Thread.sleep(10000);
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Start Sensor']").click();

		}
	}
	// COMPLETE
	public int getEGVVal(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		if(sessionActive(driver)) {
			if(driver.findElementsByXPath("//XCUIElementTypeOther[@name='EGVCell']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther").size()>0) {
				WebElement egv = driver.findElementByXPath("//XCUIElementTypeOther[@name='EGVCell']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
				String s = egv.getAttribute("name");
				String val = s.split("\\s+")[0];
				return Integer.valueOf(val);
			}
		}
		identifyError(driver);
		return -1;
	}
	// COMPLETE
	public void endSensorSession(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		if(!sessionInactive(driver)) {
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Settings']").click();
			RemoteWebElement sens = driver.findElementByXPath("//XCUIElementTypeTable");
			String parentID = sens.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", parentID);
			//scrollObject.put("direction", "down");
			scrollObject.put("predicateString", "label == 'Stop Sensor'");
			driver.executeScript("mobile:scroll", scrollObject);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@label='Stop Sensor']").click();
			driver.findElementByXPath("//XCUIElementTypeButton[@label='Stop Sensor']").click();
			System.out.println(driver.getPageSource());
		}
	}
	public boolean identifyError(AppiumDriver<MobileElement> driver) {
		if(sessionActive(driver)) {
			System.out.println("No Error");
			return false;
		}
		else if(sensorError(driver)) {
			System.out.println("Sensor Error");
		}
		else if(bluetoothError(driver)) {
			System.out.println("Bluetooth Error");
		}
		else if(signalLoss(driver)) {
			System.out.println("Signal Loss");
		}
		return true;
	}
	
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
		return !sessionInactive(driver) && !warmingUp(driver) && !sensorError(driver) && !bluetoothError(driver) && !signalLoss(driver);
	}
	// COMPLETE
	public boolean sessionInactive(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='New Sensor']").size()>0;
	}
	//COMPLETE
	public boolean bluetoothError(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Bluetooth Off']").size()>0;
	}
	// COMPLETE
	public boolean warmingUp(AppiumDriver<MobileElement> driver) {
		navigateHome(driver);
		return driver.findElementsByXPath("//XCUIElementTypeStaticText[@label='Sensor Warmup']").size()>0
				&& driver.findElementsByXPath("//XCUIElementTypeOther[@name='sensor_warmup_circle']").size()>0;
	}
	// COMPLETE (add alerts as necessary)
	public boolean alertHandler(AppiumDriver<MobileElement> driver) {
		boolean validAlert = false;
		if(warmupAlert(driver)) {
			System.out.println("Got Warmup Alert");
			validAlert = true;
		}
		if(lowAlert(driver)) {
			System.out.println("Got Low Alert");
			validAlert = true;
		}
		if(highAlert(driver)) {
			System.out.println("Got High Alert");
			validAlert = true;
		}
		if(noReadingAlert(driver)) {
			System.out.println("Got No Reading Alert");
			validAlert = true;
		}
		if(driver.findElementsByXPath("//XCUIElementTypeButton[@label='OK']").size()>0) {
			if(!validAlert) {
				System.out.println("Unidentified Alert");
			}
			driver.findElementByXPath("//XCUIElementTypeButton[@label='OK']").click();
			return true;
		}
		return false;
		
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
	
	// COMPLETE
	public boolean isHome(AppiumDriver<MobileElement> driver) {
		return driver.findElementsByXPath("//XCUIElementTypeButton[@label='Share']").size()>0 
				&& driver.findElementsByXPath("//XCUIElementTypeButton[@label='Events']").size()>0
				&& driver.findElementsByXPath("//XCUIElementTypeButton[@label='Settings']").size()>0;
	}
	// COMPLETE
	public void navigateHome(AppiumDriver<MobileElement> driver) {
		//System.out.println(isHome(driver));
		while(!isHome(driver)) {
			driver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton").click();
		}
	}
}
