package com.ninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		String browser = "edge";
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if( browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge") ) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCreds() {
		driver.findElement(By.id("input-email")).sendKeys("gajan_r@hotmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test1234");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Edit Your Account Information option is not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys("gajan_r_" + generateTimeStamp() + "@hotmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMsg = "Warning: No match for E-Mail Address and/or Password";
		System.out.println(actualMsg);
		Assert.assertTrue(actualMsg.contains(expectedMsg), "Expected Warning message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidUsername() {
		driver.findElement(By.id("input-email")).sendKeys("gajan_r_" + generateTimeStamp() + "@hotmail.com" );
		driver.findElement(By.id("input-password")).sendKeys("test1234");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMsg = "Warning: No match for E-Mail Address and/or Password";
		System.out.println(actualMsg);		
		Assert.assertTrue(actualMsg.contains(expectedMsg), "Expected Warning message is not displayed");
	}
	
	@Test (priority=4)
	public void verifyLoginWithoutAnyCreds(){
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualMsg= driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedMsg = "Warning: No match for E-Mail Address and/or Password";
		//System.out.println("Test 4 message " + actualMsg);
		Assert.assertTrue(actualMsg.contains(expectedMsg),"Expected Warning message is not displayed");
		
	}
	
	public String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
		

}
