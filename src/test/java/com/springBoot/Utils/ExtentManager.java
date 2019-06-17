package com.springBoot.Utils;
import java.util.Date;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public static ExtentReports getInstance()  {
		if (extent == null) {
			
			try {
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
