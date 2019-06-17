package com.springBoot.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileReader {
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
	
	/*Validating the Moved file is placed on incomming folder or not */
}
