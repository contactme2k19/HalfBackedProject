package com.springBoot.Utils;

import java.io.BufferedReader;
import java.io.FileReader;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

import java.util.Date;
import java.util.Properties;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public static ExtentReports getInstance()  {
		if (extent == null) {
			
			try {
				/* String propertyFilePath="./config.properties";
				 BufferedReader reader;
			
			reader = new BufferedReader(new FileReader(propertyFilePath));
			Properties props = new Properties();
			props.load(reader);*/
				PropertyFileReader reportName=new PropertyFileReader();
			String reportFileName = reportName.reportName();
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+"\\"+reportFileName+"\\"+fileName,true, DisplayOrder.NEWEST_FIRST);
			
			}catch(Exception e) {
			System.out.println(e);
			}
		}
		return extent;
	}
}
