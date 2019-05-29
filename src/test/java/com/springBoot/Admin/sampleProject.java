package com.springBoot.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sampleProject {
	

@Test
public void gmailLogin() {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ndhandapani\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();


       String appUrl = "https://gmail.com";
              driver.get(appUrl);
              driver.manage().window().maximize();
System.out.println("Launch browser and load an URL");
// close the web browser
              driver.close();
}
} 
