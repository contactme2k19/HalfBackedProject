package com.springBoot.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

import com.relevantcodes.extentreports.LogStatus;

public class FileReader {
	static PropertyFileReader fileLoactionFromProp = new PropertyFileReader();

	public static void copy(String sourceF) throws InterruptedException, IOException {
		PropertyFileReader fileLoactionFromProp = new PropertyFileReader();
		String SourceFileLocation = fileLoactionFromProp.fileLoactionFromLoad();
		String DestinationFileLoaction = fileLoactionFromProp.fileLoactionToLoad();
		File source = new File(SourceFileLocation + sourceF);
		File dest = new File(DestinationFileLoaction + sourceF);
// copy file using FileStreams
		copyFileUsingFileStreams(source, dest);

	}

	private static void copyFileUsingFileStreams(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		}catch (Exception e){
			System.out.println("exception"+e);
		} finally {
			input.close();
			output.close();
		}
	}

	/* Validating the file is present on directory or not */
	public static Boolean checkProcessingFolder(String processingFolder, String sourceFile) throws InterruptedException {
			File f = new File(processingFolder + sourceFile);
			int i=0;
			
		Boolean isFound = false;
		while (isFound != true ) {
			i++;
			System.out.println("LoopStatementTrue");
			System.out.println(f);
			if (f.exists()) {
				isFound = false;
				Thread.sleep(9000);
				System.out.println("count"+i);
				if(i==15) {
					
					break;
					}
				
				} else {
				System.out.println("File Is Moved Out from folder");
				isFound = true;
				break;
			}

		}
		return isFound;
		
		
		

	}
	/* Validating the file is present in the completed folder */
	public static Boolean checkCompletedFolder(String completedFolder, String sourceFile)
	{
		File f = new File(completedFolder + sourceFile);
		Boolean isFound = null;
		if (f.exists()) {
			System.out.println("File Is Presented on completed Folder");
						
		isFound=true;
		} else {
			System.out.println("File Is Not Presented on completed Folder");
			isFound=false;
			
		}
		return isFound;

	}
	
	
	
	public static JSONArray jsonFileReader(String jsonFilePath) throws IOException
	{
		//To store the readed file values
		 String content = "";
		   JSONArray jArray = null;
		 try {
			content = new String ( Files.readAllBytes( Paths.get(jsonFilePath) ) );
			jArray = new JSONArray(content);
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return jArray;
		
	}
}
