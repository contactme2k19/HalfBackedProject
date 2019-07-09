package com.springBoot.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.primefaces.json.JSONArray;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.springBoot.Utils.ExtentManager;
import com.springBoot.Utils.FileReader;
import com.springBoot.Utils.PropertyFileReader;
import com.springBoot.Base.*;
public class readJSON1 extends FunctionalLib {
	public ExtentReports rep = ExtentManager.getInstance();
	// ExtentReports report;
	ExtentTest logger;
	//WebDriver driver;
	String sourceFile = "102_GSI COMMUNITY 102_JSONAddAddPatient.json";
	PropertyFileReader ChromedriverPath;
	boolean STATUS = false;

	@BeforeTest
	public void BeforeClass() {
		ChromedriverPath = new PropertyFileReader();

		System.setProperty("webdriver.chrome.driver", ChromedriverPath.driverPath());

	}

	@Test
	public void QALogin() {

		try {

			logger = rep.startTest("qaUrlLaunch");
			String jsonFilePath= ChromedriverPath.fileLoactionFromLoad()+sourceFile;
            //System.out.println(jsonFilePath);
			/* Property File Declaration */
			String incommingfolder = ChromedriverPath.fileLoactionToLoad();
			String ProcessingFolder = ChromedriverPath.processingfolder();
			String CompletedFolder = ChromedriverPath.completedfolder();
			/* Input Declaration */
			String appUrl = "https://qa.gsihealth.net";
			String userID= "gsi.power@gsihealth.com";
			String password="Test123#";
			JSONArray jArray = FileReader.jsonFileReader(jsonFilePath);
			String firstName = jArray.getJSONObject(0).getString("FirstName");
			String lastName=jArray.getJSONObject(0).getString("LastName");
			String city=jArray.getJSONObject(0).getString("City");
			String state=jArray.getJSONObject(0).getString("State");
			String zip=jArray.getJSONObject(0).getString("Zip");
			String phoneNumber=jArray.getJSONObject(0).getString("PhoneNumber");
			String sex=jArray.getJSONObject(0).getString("Sex");
			String DOB=jArray.getJSONObject(0).getString("DOB");
			String SSN=jArray.getJSONObject(0).getString("SSN");
			String ParentProgram = jArray.getJSONObject(0).getJSONArray("programs").getJSONObject(0).getString("ParentProgram");
			
		
			driver = new ChromeDriver();
			driver.get(appUrl);
			driver.manage().window().maximize();
			logger.log(LogStatus.PASS, "Launch browser and load an URL for QA environment");
            waitForPageToLoad();
            
           /* Login Action*/
			Boolean login=loginDashboard(userID, password);	
			if(login==true)
			{
				logger.log(LogStatus.PASS, "Step1:: Login into dashboard");
				System.out.println("Login into dashboard");
			}else {
				logger.log(LogStatus.FAIL, "Step1::Fail-Login into dashboard");
				System.out.println("Fail-Login into dashboard");
			}
			
			
			/* Navigate to Demographic app action */
			
			Boolean demogAppNav=demogAppNavigation();
			if(demogAppNav==true)
			{
				System.out.println("Navigate to Demographic app");
				logger.log(LogStatus.PASS, "Step2:: Navigate to demographic app");
				
			}else {System.out.println("Fail to navigation");
			logger.log(LogStatus.PASS, "Step2::FAIL-Navigate to demographic app");}
			
			Thread.sleep(5000);
			
			/* Search using firstname and lastname method */
			
		Boolean patientSearch=	patientSearch(firstName, lastName);
		if(patientSearch==true)
		{
			System.out.println("Patient is searchable in demographic app");
			logger.log(LogStatus.PASS, "Step2:: Patient is searchable in demographic app");
			
		}else {
			System.out.println("FAIL-Patient is searchable in demographic app");
			logger.log(LogStatus.FAIL, "Step2:: Patient is searchable in demographic app");
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
