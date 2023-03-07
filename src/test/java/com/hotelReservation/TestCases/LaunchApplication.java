package com.hotelReservation.TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LaunchApplication {

	static WebDriver driver;


	@Before
	public static void LaunchURL() throws Exception
	{
		String chromePath = ".\\Chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		driver.get("https://in.hotels.com/");
		Thread.sleep(1500);
		System.out.println("Site Launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	}
	
	@Test
	public static void main(String[] args) throws Exception {
		LaunchURL();
		
	}

}
