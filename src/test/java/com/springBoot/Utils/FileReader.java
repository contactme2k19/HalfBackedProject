package com.springBoot.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		} finally {
			input.close();
			output.close();
		}
	}

	/* Validating the Moved file is placed on incomming folder or not */
	/*
	 * public static void checkIncomingFileExist(String sourceF) throws
	 * InterruptedException { String DestinationFileLoaction =
	 * fileLoactionFromProp.fileLoactionToLoad(); File f = new
	 * File(DestinationFileLoaction +sourceF); Boolean isFound = false; while
	 * (isFound != true) { System.out.println("LootStatementTrue");
	 * Thread.sleep(20000); if (f.exists()) { isFound = false; } else {
	 * System.out.println("File Is Moved Out from Incomming Folder"); break; }
	 * 
	 * } }
	 */
	/* Validating the file is present on directory or not */
	public static void checkProcessingFolder(String processingFolder, String sourceFile) throws InterruptedException {

		File f = new File(processingFolder + sourceFile);
		Boolean isFound = false;
		while (isFound != true) {
			System.out.println("LoopStatementTrue");
			Thread.sleep(20000);
			if (f.exists()) {
				isFound = false;
			} else {
				System.out.println("File Is Moved Out from folder");
				break;
			}

		}

	}
}
