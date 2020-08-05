package droid_helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Droid_DexcomG6_Helper {
	
	public String getEGVVal(AppiumDriver<MobileElement> driver) {
		//WebElement egv = driver.findElement(By.id("com.dexcom.g6:id/textViewEGV"));
		WebElement egv = driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.TextView[@index='1']");
		String ret = egv.getAttribute("text");
		return ret;
	}
	
	//resource-id="android:id/alertTitle" potential way to find alerts ??
	public boolean alertHandler(AppiumDriver<MobileElement> driver) {
		if(sensorWarmupAlert(driver)) {
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(urgentLowAlert(driver)) {
			System.out.println("Urgent Low");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(urgentLowSoonAlert(driver)) {
			System.out.println("Urgent Low Soon");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(lowAlert(driver)) {
			System.out.println("Low");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(highAlert(driver)) {
			System.out.println("High");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		if(driver.findElements(By.id("android:id/alertTitle")).size() >0) {
			System.out.println("Unspecififed");
			driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			return true;
		}
		return false;
	}
	
	public boolean identifyError(AppiumDriver<MobileElement> driver) {
		if(bluetoothError(driver)) {
			System.out.println("Bluetooth Error");
			return true;
		}
		else if(signalLossError(driver)) {
			System.out.println("Signal Loss");
			return true;
		}
		return false;
	}
	
	public boolean sensorWarmupAlert(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Sensor warmup successful and setup complete. Sensor glucose readings now available.']").size() >0) {
			return true;
		}
		return false;
	}
	
	public boolean urgentLowSoonAlert(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Urgent Low Soon Alert']").size() >0) {
			return true;
		}
		return false;
	}
	public boolean urgentLowAlert(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Urgent Low Glucose Alarm']").size() >0) {
			return true;
		}
		return false;
	}
	
	public boolean highAlert(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='High Glucose Alert']").size() >0) {
			return true;
		}
		return false;
	}
	public boolean lowAlert(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Low Glucose Alert']").size() >0) {
			return true;
		}
		return false;
	}
	
	public boolean isWarmingUp(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Sensor Warmup']").size() >0) {
			return true;
		}
		return false;
	}
	public void stopSensor(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		driver.findElementByXPath("//android.widget.TextView[@text='SETTINGS']").click();
		((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+"Stop Sensor"+"\").instance(0))").click();
		driver.findElementByXPath("//android.widget.Button[@text='STOP SENSOR']").click();
	}
	public boolean signalLossError(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Signal Loss']").size() >0) {
			return true;
		}
		return false;
	}
	
	public boolean bluetoothError(AppiumDriver<MobileElement> driver) {
		if(driver.findElementsByXPath("//android.widget.TextView[@text='Bluetooth Off']").size() >0) {
			return true;
		}
		return false;
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
