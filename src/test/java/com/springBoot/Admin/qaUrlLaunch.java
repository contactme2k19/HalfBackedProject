package com.springBoot.Admin;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.springBoot.Utils.ExtentManager;

public class qaUrlLaunch {
	public ExtentReports rep = ExtentManager.getInstance();
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	
	@Test
	public void QALogin() {
		logger=rep.startTest("qaUrlLaunch");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ndhandapani\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		 driver = new ChromeDriver();
	       String appUrl = "https://qa.gsihealth.net";
	              driver.get(appUrl);
	              driver.manage().window().maximize();
	             
	logger.log(LogStatus.PASS, "Launch browser and load an URL for QA environment");
	// close the web browser
	              driver.close();
	
	              rep.endTest(logger);
	              rep.flush();

	}
}
