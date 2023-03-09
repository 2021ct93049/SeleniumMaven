package com.hotelReservation.TestCases;



import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LaunchApplicationTest {

	WebDriver driver;
	
  @BeforeMethod
  public void LaunchURLTest() throws Exception {
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
  }

  @AfterMethod
public void tearDown() {
		driver.close();
	}

	@Test
	public void SignIn_Appilcation() throws Exception
	{

		WebElement clickSignInButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
		clickSignInButton.click();
		System.out.println("Clicked on SignIn Button");
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

		WebElement signInLink = driver.findElement(By.xpath("//div[@class='actions']/a[text()='Sign in']"));
		signInLink.click();
		System.out.println("Clicked on SignIn Link");
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

		WebElement emailAdd = driver.findElement(By.name("email"));
		emailAdd.sendKeys("priti.deshmukh@gmail.com");

		WebElement passwd = driver.findElement(By.name("password"));
		passwd.sendKeys("BarclaysInd@2022");

		WebElement SignInSubmitBtn = driver.findElement(By.id("loginFormSubmitButton"));
		SignInSubmitBtn.click();
		System.out.println("Clicked on SignIn Submit Button");
		Thread.sleep(7000);

		System.out.println("Entered in another Test");

		//String actualText ="Where to?";
		WebElement title = driver.findElement(By.xpath("//h1[text()='Where to?']"));
		if(title.isDisplayed())
		{
			System.out.println("Title displayed");
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement goingTo = driver.findElement(By.xpath("//span[text()='Going to']/parent::button"));
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(goingTo)).click();
		driver.findElement(By.id("destination_form_field")).sendKeys("Bangalore");
		driver.findElement(By.id("destination_form_field")).click();
		WebElement destination = driver.findElement(By.xpath("//*[@id='lodging_search_form']//button[@aria-label='Going to']"));
		WebDriverWait wait_dest = new WebDriverWait(driver, 15);
		wait_dest.until(ExpectedConditions.elementToBeClickable(destination)).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		WebElement selectDate = driver.findElement(By.xpath("//button[@id='date_form_field-btn']"));
		selectDate.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement fromDate = driver.findElement(By.xpath("//button[@aria-label='21 Sep 2023']"));
		fromDate.click();
		WebElement toDate = driver.findElement(By.xpath("//button[@aria-label='23 Sep 2023']"));
		toDate.click();
		WebElement doneBtn = driver.findElement(By.xpath("//span[contains(text(),'Save changes ')]/parent::button"));
		//"//*[@id='app-layer-search_form_date_picker_dialog']/div[2]/div/div/div[3]/button"));
		doneBtn.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//to click on Travellers 
		driver.findElement(By.xpath("//span[text()='Travellers']/parent::button")).click();
		//to add adults number
		driver.findElement(By.xpath("(//div[@class='uitk-layout-flex uitk-layout-flex-item uitk-step-input-controls']/button)[2]")).click();
		//To add children -
		driver.findElement(By.xpath("(//div[@class='uitk-layout-flex uitk-layout-flex-item uitk-step-input-controls']/button)[4]")).click();
		//to select age of child
		Select objSelect =new Select(driver.findElement(By.id("age-traveler_selector_children_age_selector-0-0")));
		objSelect.selectByValue("5");

		// to click on done button
		driver.findElement(By.id("traveler_selector_done_button")).click();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		// to click on search button
		driver.findElement(By.id("submit_button")).click();
		Thread.sleep(1000);

		// to sort the search criteria by price and choices-
		Select sortBy = new Select(driver.findElement(By.xpath("//select[@name='sort']")));
		sortBy.selectByValue("PRICE_RELEVANT");
		Thread.sleep(3500);
		//Select sortByDistance = new Select(driver.findElement(By.name("sort")));
		sortBy.selectByVisibleText("Distance from Whitefield");
		Thread.sleep(4000);

		// click and check for hotel -
		driver.findElement(By.xpath("(//button[@class='uitk-image-link'])[1]")).click();
		Thread.sleep(2500);

		//To navigate to another window and close the browser
		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				System.out.println("Switched to child window:" +driver.switchTo().window(child_window).getTitle());
				// to scroll down page
				Thread.sleep(100);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,1500)");
				// to reserve a hotel click on Reserve button
				driver.findElement(By.xpath("(//span[text()='Reserve']/parent::button)[2]")).click();
				driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
				//Assert Payment option is displayed
				String paymentOption = driver.findElement(By.xpath("//h2[@class='uitk-toolbar-title-content']")).getText();
				Assert.assertEquals(paymentOption, "Your payment options");
				// assert payment button is enabled
				driver.findElement(By.xpath("//span[text()='Pay now']/parent::button")).isEnabled();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//span[text()='Pay at property']/parent::button")).isEnabled();
				// close child window
				driver.close();
			}

		}
		//switch to the parent window
		driver.switchTo().window(parent);
		Thread.sleep(1000);
		// to select Air Conditioned and Free WiFi check box
		driver.findElement(By.xpath("(//input[@name='popularFilter'])[2]")).click();
		Thread.sleep(1000);
		/*driver.findElement(By.xpath("(//input[@name='popularFilter'])[3]")).click();
			Thread.sleep(1000);*/

		//to logout from an application -
		driver.findElement(By.xpath("//div[@id='gc-custom-header-nav-bar-acct-menu']/button")).click();
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)");
		// click on sign out
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		Thread.sleep(3000);

	}
}
