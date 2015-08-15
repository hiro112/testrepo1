package de.privat.backup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Backup {

	public static void main(String[] args) throws IOException {
	
		File propertiesFile = new File("./Files/backup.properties");
		Properties properties = new Properties();
		 
		if(propertiesFile.exists())
		{
		  BufferedInputStream bis = new BufferedInputStream(new FileInputStream(propertiesFile));
		  properties.load(bis);
		  bis.close();  
		}
		 
		System.out.println(properties.getProperty("destinationPath"));

 
	}

}
