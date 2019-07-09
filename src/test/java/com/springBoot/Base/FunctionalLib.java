package com.springBoot.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class FunctionalLib {
	public WebDriver driver;

	public void waitForPageToLoad()  {
		wait(1);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String state = (String) js.executeScript("return document.readyState");

		while (!state.equals("complete")) {
			wait(2);
			state = (String) js.executeScript("return document.readyState");
		}
	}
	public void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Boolean loginDashboard(String userID, String password) throws Exception
	{
		Boolean STATUS=null;
		
		try {
	String xpathUserID = "//input[@name='UserID']";
	String xpathPassword="//input[@name='Password']";
	String xpathLoginButton="//*[@id='btnLogin']";
	String xpathHomeLabel="//*[@aria-label='home']";
	String xpathSAMSAPOPUP="//label[contains(text(),'I have read and understand the redisclosure warning.')]";
	String xpathSAMSAAgreeCheckBox="//label[contains(text(),'I have read and understand the redisclosure warning.')]/../following::*/div[text()='I Agree']";
	String xpathGotIt="//div[text()='Got it']";
	
	
	driver.findElement(By.xpath(xpathUserID)).sendKeys(userID);
		driver.findElement(By.xpath(xpathPassword)).sendKeys(password);
		driver.findElement(By.xpath(xpathLoginButton)).click();
		//waitForPageToLoad();
		Thread.sleep(10000);
		if(driver.findElements(By.xpath(xpathHomeLabel)).size()>0){
			//System.out.println("Step1::Login into dashboard.");
			//waitForPageToLoad();
			if(driver.findElements(By.xpath(xpathGotIt)).size()>0)
			{
			driver.findElement(By.xpath(xpathGotIt)).click();
			
		 STATUS=true;
			}
		}else if(driver.findElements(By.xpath(xpathSAMSAPOPUP)).size()>0){
			System.out.println("SAMSA Popup box is displayed for the user.");
			
			driver.findElement(By.xpath(xpathSAMSAAgreeCheckBox)).click();
		
			if(driver.findElements(By.xpath(xpathGotIt)).size()>0)
			{
			driver.findElement(By.xpath(xpathGotIt)).click();
			
			 STATUS=true;}
		
		}else {
			
			//System.out.println("Step1::Login into dashboard.-Fail");
			 STATUS=false;
		}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return STATUS;
	}
	
	public Boolean demogAppNavigation() {
		Boolean STATUS=null;
		try {
			
			String xpathdemogApp="//div[@id='appEnrollment_menu']";
			String xpathLastName="//input[@name='lastName']";
			if(driver.findElements(By.xpath(xpathdemogApp)).size()>0)
			{
			driver.findElement(By.xpath(xpathdemogApp)).click();
			System.out.println("demograpic App icon is displayed");
			if(driver.findElements(By.xpath(xpathLastName)).size()>0)
			{
				//System.out.println("Navigate to View list of demographic app");
				STATUS=true;
			}else {
				//System.out.println("FAIL--Navigate to View list of demographic app");
			STATUS=false;
			}
			
			}else {//System.out.println("demograpic App icon is displayed");
			STATUS=false;
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		return STATUS;
	}
	public Boolean patientSearch(String firstName, String lastName)
	{
		Boolean STATUS= null;
		try {
			/* Search using firstname and lastname method */
			String xpathLastNameInput="//input[@name='lastName']";
			String xpathFirstNameInput="//input[@name='firstName']";
			String xpathSearch="//div[contains(text(),'Search')][1]";
			String xptahCheckLastName="//td/div[text()='"+lastName+"']";
			String xptahCheckFirstName="//td/div[text()='"+firstName+"']";
			String xpathNoresultValuePopup="//td[text()='Search did not return any results. Please modify your search criteria.']";
			String xpathOkButton="//div[text()='OK']";
			String xpathSelect="//div[text()='Select']";
			String xpathViewLabel="//td[text()='View Patient']";
			driver.findElement(By.xpath(xpathFirstNameInput)).clear();
			driver.findElement(By.xpath(xpathFirstNameInput)).sendKeys(firstName);
			driver.findElement(By.xpath(xpathLastNameInput)).clear();
			driver.findElement(By.xpath(xpathLastNameInput)).sendKeys(lastName);
			
			driver.findElement(By.xpath(xpathSearch)).click();
			Thread.sleep(15000);
			if(driver.findElements(By.xpath(xptahCheckLastName)).size()>0 && driver.findElements(By.xpath(xptahCheckFirstName)).size()>0)
			{
				System.out.println("Patient is searchable in demographic app");
				driver.findElement(By.xpath(xptahCheckLastName)).click();
				driver.findElement(By.xpath(xpathSelect)).click();
				if(driver.findElements(By.xpath(xpathViewLabel)).size()>0)
				{
					System.out.println("Navigate to view patient page");
					STATUS=true;
				}else {
					System.out.println("FAIL-View screen navigation");
					STATUS=false;
				}
			}else if(driver.findElements(By.xpath(xpathNoresultValuePopup)).size()>0) {
				driver.findElement(By.xpath(xpathOkButton)).click();
				System.out.println("FAIL-Warning as-> Search did not return any results. Please modify your search criteria.");
				STATUS=false;
			}else {
				System.out.println("Maybe unexpected error popoup is displayed");
				STATUS=false;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return STATUS;
		
		
	}

	
}
