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
	String sourceFile = "852_ESCROW3_SmokeTest_REAdd.xlsx";
	PropertyFileReader ChromedriverPath;
	boolean STATUS=false;

	@BeforeTest
	public void BeforeClass() {
		ChromedriverPath = new PropertyFileReader();

		System.setProperty("webdriver.chrome.driver", ChromedriverPath.driverPath());
		driver = new ChromeDriver();

	}

	@Test
	public void QALogin() {

		try {

			logger = rep.startTest("qaUrlLaunch");
			/* Property File Declaration */
			String incommingfolder = ChromedriverPath.fileLoactionToLoad();
			String ProcessingFolder = ChromedriverPath.processingfolder();
			String CompletedFolder=ChromedriverPath.completedfolder();
		//	String RejectedFolder=ChromedriverPath.rejectedfolder();
			/* Input Declaration */
			String appUrl = "https://dev.gsihealth.net";
			
			/* Code to move the file from one folder to another folder */
			FileReader.copy(sourceFile);
			System.out.println("File moved to Destination folder");
			logger.log(LogStatus.PASS, "File moved to Destination folder");
			/* To Verify the Moved file is still exist on the incomming folder */
			boolean incommingCheck=FileReader.checkProcessingFolder(incommingfolder, sourceFile);
			if(incommingCheck==false) {
			FileReader.checkProcessingFolder(incommingfolder, sourceFile);

			logger.log(LogStatus.INFO, "File Is Moved Out from Incomming Folder");
			}else {logger.log(LogStatus.FAIL, "File Is Not Moved Out from Incomming Folder");
			}
			/*FileReader.checkProcessingFolder(incommingfolder, sourceFile);
			logger.log(LogStatus.INFO, "File Is Moved Out from Incomming Folder");
			/* To verify the file is placed on the Processing folder */
			FileReader.checkProcessingFolder(ProcessingFolder, sourceFile);
			logger.log(LogStatus.INFO, "File Is Moved Out from Processing Folder");
			/* To verify the file is placed on the Completed folder */
			
			FileReader.checkProcessingFolder(CompletedFolder, sourceFile);
			logger.log(LogStatus.INFO, "File Is in Completed Folder");
			
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
