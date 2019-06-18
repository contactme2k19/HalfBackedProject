package com.springBoot.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	public String propertyFilePath = "./config.properties";
	public Properties properties;

	/* To load the config File */
	public PropertyFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			properties.load(reader);

		} catch (Exception e) {
			System.out.println("FileReader" + e);

		}

	}

	/* To get the driver path from propertyFile */
	public String driverPath() {
		String driverPath = properties.getProperty("chromeDriver");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Config.properties file.");
	}

	/* To get the reportName from propertyFile */
	public String reportName() {
		String reportName = properties.getProperty("reportFolderName");
		if (reportName != null)
			return reportName;
		else
			throw new RuntimeException("Report Name not specified in the Config.properties file.");
	}

	/* To get the PatientLoader filePath For Destination and Source */
	public String fileLoactionFromLoad() {
		String sourceFilePath = properties.getProperty("sourceFolder");
		if (sourceFilePath != null)
			return sourceFilePath;
		else
			throw new RuntimeException("File Path is not specified in the propertyFiles");
	}

	/* To get the PatientLoader filePath For Destination and Source */
	public String fileLoactionToLoad() {
		String destinationFilePath = properties.getProperty("destination");
		if (destinationFilePath != null)
			return destinationFilePath;
		else
			throw new RuntimeException("File Path is not specified in the propertyFiles");
	}

	/* To get the folder location of Processing folder */
	public String processingfolder() {
		String processingFolderPath = properties.getProperty("processing");
		if (processingFolderPath != null)
			return processingFolderPath;
		else
			throw new RuntimeException("File Path is not specified in the propertyFiles");
	}
}
