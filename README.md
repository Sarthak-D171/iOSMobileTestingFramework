# MobileTestingFramework
Use Appium, Selenium and TestNg to create a mobile testing framework.

Allows for blackbox testing for the dexcom app and to test the stability 
of bluetooth connectivity between the phone and transmitter.

### Hardware/Software Setup:
#### IOS Setup:
Follow Instructions located: Z:\eng6340\SoftwareTesting\TransmitterVnV_MAC_IOSSetUp. 

Install TestNG on Eclipse. 

#### Android Setup:
Follow Instructions located: Z:\eng6340\SoftwareTesting\Android-Mac_Test_Env_Setup.docx 

Additionally download chromedriver: http://appium.io/docs/en/writing-running-appium/web/chromedriver/ 

Add Path environment variable to ecplise run configurations under droidTester. 

Install TestNG on Eclipse. 

#### Hardware Setup:
Connect device to mac via usb-c. 

Ensure device is unlocked and trust is established between computer
and device.   


### Usage:
config.txt: This File contains all the important device information appium will need to connect to your device. The file reads in line by line. 

Line 1: Operating System (IOS/Android) 

Line 2: Device Name (Can be found under about device in phone settings)

Line 3: Version Number (Can be found under about device in phone settings)

Line 4: Device UDID (For IOS run instruments -s devices in terminal) (For ANDROID run abd devices)

###IOS Test Building:
ios_test_settings.txt: This File is a method for building automated tests for IOS. Very useful for generating and running long and short tests without writing additional code.

#####Syntax (Capitalization doesn't matter): 
Line 1: Always how long you want the test to run in minutes. EX. Minutes: 10

Line 2: Always whether you are setting up a new transmitter or not. If yes provide transmitter id and sensor id. Else doesn't matter. EX. Setup Transmitter: Yes 8H9FGH 7171

Line 3-End: Your Test applications that you want to open and run for how many minutes and with what arguments. 

Syntax Summary/Examples:  


<pre>
args[0]          args[1]             args[2]             args[3]      

_______________________________________________________________________  
                                                                       
appstore        download             appname             repeat/norepeat    
 
_______________________________________________________________________ 

snapchat     send/open/send_open     minutes(num)       repeat/norepeat     

_______________________________________________________________________ 

youtube         minutes(num)        repeat/norepeat         
                
_______________________________________________________________________ 

bundleid     full_bundle_id          minutes(num)       repeat/norepeat 

_______________________________________________________________________ 

native       native_app_name         minutes(num)       repeat/norepeat 

_______________________________________________________________________ 

dexcom           EGV                 minutes(num)       repeat/norepeat 

_______________________________________________________________________ 

facetime      number_to_call         minutes(num)       repeat/norepeat 

_______________________________________________________________________ 

gmail         email_address_send    repeat/norepeat
_______________________________________________________________________ 

phone         number_to_call         minutes(num)       repeat/norepeat 

_______________________________________________________________________ 

messages      number_to_msg         repeat/norepeat 

_______________________________________________________________________ 

spotify       playlist/song          minutes(num)       repeat/norepeat 

_______________________________________________________________________ 

safari        search/url           searchquery/url      repeat/norepeat
</pre>

###Android Test Building:

Unfortunately haven't built an android_test_settings interpreter yet. :(
If you want to build an android test I would suggest looking at the IOS generate_test method and write up a similar test building method.

Until then, take a look at the droidTester and that should give u a general idea how to build these tests.


# General Structure:
## BaseDriver: 

#####General Purpose: 
Construct the driver and outputLog which will be used throughout the program.  

Establishes driver and phone info by reading in information given from config file. 

Stores general use methods that will be needed by the tester methods. 

Setup method will be the first method to run before every test. 

##### Important Variables: 

1. driver (main driver that is constructed by reading in config.txt) 

2. outputLog (variable that we write to in order to store our phone logs) 

##### IOS Specific Methods: 

1. openNativeApp(String appname) Will launch any default IOS App. Make sure appname is all lower case no spaces. 

2. openBundleId(String bundleId) Will launch the application with given bundleId.  Given link is good way to get the bundleId of any app. 
https://stackoverflow.com/questions/27509838/how-to-get-bundle-id-of-ios-app-either-using-ipa-file-or-app-installed-on-iph 

3. iosHomeButton() Call anytime you want to close an app and launch a new one. 

4. iosScroll(String dir) Scroll in direction "up" or "down". 

##### Android Specific Methods: 

1. openAndroidApp(String appname) Will launch some predefined apps that are given in its dictionary. 

Because of appactivity this only can open a couple android apps. Generally suggest as you move forward and expand the capability of Android with this framework that you add every app you want to open into this methods dictionary. Can get appactivity and app bundle using Apk Info app from app store.

2. androidHomeButton() Call anytime you want to close an app and launch a new one. 



## HelperMethods:
Split into Droid Helpers and IOS Helpers. 

These classes contain methods that interact with the appropriate app in their name. 

They read in the app page as an XML file and then use appropriate locators to find which elements to interact with using selenium. 

If anything breaks, or the phone doesn't act as it should, double check the selenium selectors inside these helper methods. 

For any app that you want to automate, add a helper class and use selenium locators to do appropriate automation.

All HelperMethods actual methods should take in both the driver and the outputLog as parameters in the methods so that you can write what the phone state is in to the outputLog and use the driver to control the phone. 


## MainTester:
This is the method you use to write your tests.

Construct the driver by calling an open method defined in base driver. 

Construct a new Helper Method object for any app you want to automate.

If you just want to open and close an app then just call the appropriate open Method, followed by homeButton. 

Check the helper methods to fill in appropriate parameters, and make sure to pass in the driver and outputLog.
 
You only need to construct a Helper method if you plan on interacting with the actual application.

Use IOS_Generate test for easy to construct IOS tests. 

One IOS main tester and one droid main tester. Make sure you run the appropriate one for your application.
