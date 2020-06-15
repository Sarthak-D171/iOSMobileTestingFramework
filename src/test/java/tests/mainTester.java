package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class mainTester extends BaseDriver{
	/*
	@Test
	public void gmailTest() throws MalformedURLException, InterruptedException {
		Gmail_Helper g = new Gmail_Helper();
		driver = openApp("com.google.Gmail");
		g.composeEmail("sm@gmail.com","Dabc","Hi",driver);
		Thread.sleep(3000);
	}
	*/
	
	@Test
	public void webTest1() throws MalformedURLException, InterruptedException{
		Safari_Helper s = new Safari_Helper();
		driver = openNativeApp("Safari");
		System.out.println("whoo");
		s.googleSearch("hello",driver);
		s.yahooSearch("hello", driver);
		Thread.sleep(3000);
	}
	
	/*
	@Test
	public void calcTest1() throws MalformedURLException {
		Calc_Helper c = new Calc_Helper();
		driver = openNativeApp("Calculator");
		System.out.println(driver.getPageSource());
		//c.typeNum("-3.0", driver);
		c.basicOperation("multiply","-30.6","2.5",driver);
		c.clear(driver);
		homeButton();
	}
	*/
	
	/*
	@Test
	public void phoneTest1() throws MalformedURLException, InterruptedException {
		Phone_Helper p = new Phone_Helper();
		driver = openNativeApp("Phone");
		System.out.println("hello");
		p.callNumber("8887383646",driver);
		//p.callContact("Temp Temp",driver);
	}
	*/
	/*
	@Test
	public void dexcomTest1() throws MalformedURLException {
		driver = openApp("com.dexcom.G6");
		DexomcG6_Helper d = new DexomcG6_Helper();
		d.logIn(driver);
		d.newSensor("1015", driver);
		System.out.println("Hello");
	}
	*/
	
}
