package com.ninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Register {
	
	@Test
	public void verifyRegisterationOnlyWithMandatoryFields() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//*[contains(text(),'My Account') and @class='hidden-xs hidden-sm hidden-md']")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Register')]")).click();
		Thread.sleep(10000);
		
	}
	

}
