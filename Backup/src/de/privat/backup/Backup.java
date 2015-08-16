package de.privat.backup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Properties;

public class Backup {

	public static void main(String[] args) throws IOException {

		File propertiesFile = new File("./Files/backup.properties");
		Properties properties = new Properties();

		if (propertiesFile.exists()) {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(propertiesFile));
			properties.load(bis);
			bis.close();
		}
		File a = new File(args[0]);
		if (args[0] != null && !args[0].isEmpty()) {
			String string = properties.getProperty("destinationPath").replace("\"", "")
					+ "\\backup_" + System.currentTimeMillis();
			copyDir(new File(args[0]), new File(string));
		} else {
			System.out.println("Parameter 1 is null or empty... try again");
		}
	}

	public static void copyDir(File quelle, File ziel) throws FileNotFoundException, IOException {

		File[] files = quelle.listFiles();
		/*
		 * In diesem Objekt wird für jedes File der Zielpfad gespeichert. 1. Der
		 * alte Zielpfad 2. Das systemspezifische Pfadtrennungszeichen 3. Der
		 * Name des aktuellen Ordners/der aktuellen Datei
		 */
		File newFile = null;
		ziel.mkdirs(); // Erstellt alle benötigten Ordner
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				newFile = new File(ziel, files[i].getName());
				if (files[i].isDirectory()) {
					copyDir(files[i], newFile);
				} else {
					copyFile(files[i], newFile);
				}
			}
		}
	}

	public static void copyFile(File file, File target) throws FileNotFoundException, IOException {

		FileChannel in = new FileInputStream(file).getChannel();
		FileChannel out = new FileOutputStream(target).getChannel();
		in.transferTo(0, file.length(), out);
		in.close();
		out.close();
	}

}
