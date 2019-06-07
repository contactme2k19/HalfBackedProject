package com.springBoot.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.springBoot.Utils.ExtentManager;
import com.springBoot.Utils.PropertyFileReader;

public class qaUrlLaunch {
	public ExtentReports rep = ExtentManager.getInstance();
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;

	@Test
	public void QALogin() {
		PropertyFileReader ChromedriverPath = new PropertyFileReader();


		try {
			logger = rep.startTest("qaUrlLaunch");
			System.setProperty("webdriver.chrome.driver", ChromedriverPath.driverPath());
			driver = new ChromeDriver();
			String appUrl = "https://qa.gsihealth.net";
			driver.get(appUrl);
			driver.manage().window().maximize();

			logger.log(LogStatus.PASS, "Launch browser and load an URL for QA environment");
// close the web browser
			driver.close();

			rep.endTest(logger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rep.flush();

	}
}
