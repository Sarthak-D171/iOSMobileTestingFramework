package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class mainTester extends BaseDriver{
	@Test
	public void gmailTest() throws MalformedURLException, InterruptedException {
		Gmail_Helper g = new Gmail_Helper();
		driver = openApp("com.google.Gmail");
		WebElement m = driver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell");
		g.composeEmail("sm@gmail.com","Dabc","Hi",driver);
		Thread.sleep(3000);
	}
	/*
	@Test
	public void webTest1() throws MalformedURLException, InterruptedException{
		Safari_Helper s = new Safari_Helper();
		driver = openNativeApp("Safari");
		System.out.println("whoo");
		s.googleSearch("Hello",driver);
		Thread.sleep(3000);
	}
	*/
	/*
	@Test
	public void calcTest1() throws MalformedURLException {
		Calc_Helper c = new Calc_Helper();
		driver = openNativeApp("Calculator");
		System.out.println(driver.getPageSource());
		c.basicOperation("multiply","30.6","2.5",driver);
		c.clear(driver);
		homeButton();
	}
	*/
	/*
	@Test
	public void phoneTest1() throws MalformedURLException {
		Phone_Helper p = new Phone_Helper();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		//callNumber("8887383646");
		p.callContact("Temp Temp",driver);
	}*/

}
