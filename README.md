# IOSMobileTestingFramework
Use Appium, Selenium and TestNg to create an IOS testing framework

### How to use:   
Follow instructions located at: Z:\eng6340\SoftwareTesting\TransmitterVnV_MAC_IOSSetUp.   
This will setup appium, and java accordingly. Be sure to get the correct versions that match the device you are using.    

Follow instructures located here: https://www.edureka.co/community/34006/how-to-install-testng-in-eclipse. 
This will setup TestNG which is the framework that this code uses.   

Finally, edit DeviceName in BaseDriver Setup method to the device name under General->About.   

# General Structure:
## BaseDriver: 
Base Class that constructs the driver that the rest of the framework uses.  
Uses Open Methods to construct what app the driver is using before each Test. 
Runs Setup method before each Test and Teardown method after each Test.   
OpenBundleId should be used for 3rd party apps, while openNativeApp should be used for preinstalled apps.  
Edit DeviceName in Setup Method in order to use your connected device.  


## HelperMethods:
Any class that ends in Helper is a helper class that interacts with the approiate app.  
These methods use selenium to get page objects and interact with them. 
They read in the app page as an XML file and then use appropriate locators to find which elements to interact with.  
Framework currently supports quite a few apps and doing some important tasks on these apps.  
For any app that you want to automate, add a helper class and use selenium locators to do appropriate automation.  
Each Helper method will take in a driver as its final parameter.  
If anything breaks, these are the methods to look at and change.  


## MainTester:
This is the method you use to write your tests.
Construct the driver by calling an open method defined in base driver. 
Construct a new Helper Method object for any app you want to automate.
If you just want to open and close an app then just call the appropriate open Method, followed by homeButton. 
Check the helper methods to fill in appririate parameters, and make sure to pass in the driver as the final paramter.  
You only need to construct a Helper method if you plan on interacting with the actual application.
