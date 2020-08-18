package droid_helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Droid_DexcomG6_Helper {
	
	public String getEGVVal(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		if(sessionActive(driver)) {
			//WebElement egv = driver.findElement(By.id("com.dexcom.g6:id/textViewEGV"));
			WebElement egv = driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.TextView[@index='1']");
			String ret = egv.getAttribute("text");
			return ret;
		} else {
			identifyError(driver);
			return "Failed";
		}
	}

	public void stopSensor(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		if(!sessionInactive(driver)) {
			driver.findElementByXPath("//android.widget.TextView[@text='SETTINGS']").click();
			((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+"Stop Sensor"+"\").instance(0))").click();
			driver.findElementByXPath("//android.widget.Button[@text='STOP SENSOR']").click();
			if(driver.findElementsByXPath("//android.widget.Button[@text='OK']").size() >0) {
				driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			}
			
		}
	}
	public void startSensorSession(String code, AppiumDriver<MobileElement> driver) throws InterruptedException {
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
			
		}
	}
	
	
	public boolean identifyError(AppiumDriver<MobileElement> driver) throws InterruptedException {
		if(bluetoothError(driver)) {
			System.out.println("Bluetooth Error");
			return true;
		}
		else if(signalLoss(driver)) {
			System.out.println("Signal Loss");
			return true;
		}
		else if(sensorError(driver)) {
			System.out.println("Sensor Error");
			return true;
		}
		else if(sessionEnded(driver)) {
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
		return !sessionInactive(driver) && !warmingUp(driver) && !sensorError(driver) && !bluetoothError(driver) && !signalLoss(driver) && !sessionEnded(driver);
	}
	public boolean warmingUp(AppiumDriver<MobileElement> driver) throws InterruptedException {
		navigateHome(driver);
		return driver.findElementsByXPath("//android.widget.TextView[@text='Sensor Warmup']").size() >0;
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
