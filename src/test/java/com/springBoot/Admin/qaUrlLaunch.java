package com.springBoot.Admin;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.springBoot.Utils.ExtentManager;
import com.springBoot.Utils.FileReader;
import com.springBoot.Utils.PropertyFileReader;


public class qaUrlLaunch {
	public ExtentReports rep = ExtentManager.getInstance();
	// ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	String sourceFile = "Beta_AddPatientTestData.xlsx";
	@BeforeTest
	public void BeforeClass() {
		PropertyFileReader ChromedriverPath = new PropertyFileReader();
		System.setProperty("webdriver.chrome.driver", ChromedriverPath.driverPath());
		driver = new ChromeDriver();

	}

	@Test
	public void QALogin() {

		try {
			logger = rep.startTest("qaUrlLaunch");
			String appUrl = "https://dev.gsihealth.net";
			driver.get(appUrl);
			driver.manage().window().maximize();
			logger.log(LogStatus.PASS, "Launch browser and load an URL for QA environment");

			String xpathLoginTextfiled = "//input[@name='UserID']";
			try {
				driver.findElement(By.xpath(xpathLoginTextfiled));
				System.out.println("Element is Present");
				logger.log(LogStatus.PASS, "QA Url is loaded");
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "QA Url is loaded");
			}

	/* Code to move the file from one folder to another folder */
			FileReader.copy(sourceFile);
			System.out.println("File moved to Destination folder");
			logger.log(LogStatus.PASS, "File moved to Destination folder");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			/* To Verify the Moved file is still exist on the incomming folder */
			File f = new File("S:\\PL_Uploads\\GSI COMMUNITY 102\\incoming\\Beta_AddPatientTestData.xlsx");
			Boolean isFound = false;
			while (isFound != true) {
				System.out.println("LootStatementTrue");
				if (f.exists()) {
					isFound = false;
				} else {
					System.out.println("File Is Moved Out from Incomming Folder");
					logger.log(LogStatus.INFO, "File Is Moved Out from Incomming Folder");
					break;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e);
		}

	}

	@AfterTest
	public void closeTest() {
		driver.close();
		rep.endTest(logger);
		rep.flush();
	}
}
