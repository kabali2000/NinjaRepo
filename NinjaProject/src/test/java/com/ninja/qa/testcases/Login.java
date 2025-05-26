package com.ninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {

	@Test(priority = 1)
	public void verifyLoginWithValidCreds() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("gajan_r@hotmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test1234");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Edit Your Account Information option is not displayed");

		driver.quit();

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidPassword() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("gajan_r@hotmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMsg = "Warning: No match for E-Mail Address and/or Password";
		System.out.println(actualMsg);
		Assert.assertTrue(actualMsg.contains(expectedMsg), "Expected Warning message is not displayed");

		driver.quit();
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidUsername() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("gajan_rr@yahoo.ca");
		driver.findElement(By.id("input-password")).sendKeys("test1234");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMsg = "Warning: No match for E-Mail Address and/or Password";
		System.out.println(actualMsg);		
		Assert.assertTrue(actualMsg.contains(expectedMsg), "Expected Warning message is not displayed");
		driver.quit();

	
	}

}
