package sg.edu.nus.iss.universitystore.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;

/**
 * JUnit Utility Class
 * 
 * @author Sanjay
 *
 */
public class JUnitUtility {

	/**
	 * Copy File one file from Specified Backup to Test Directory
	 * 
	 * @param fileName
	 * @param directoryPath
	 * @throws IOException
	 */
	public static void copyFile(String fileName, String directoryPath) throws IOException {
		Files.copy(
				new File(JUnitConstants.Data.BACKUP_FILE_PATH + directoryPath + fileName + Constants.Data.FILE_EXT)
						.toPath(),
				new File(JUnitConstants.Data.TEST_FILE_PATH + fileName + Constants.Data.FILE_EXT).toPath(),
				StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Copy File one file from Default Backup to Test Directory
	 * 
	 * @param fileName
	 * @param directoryPath
	 * @throws IOException
	 */
	public static void copyFile(String fileName) throws IOException {
		copyFile(fileName, JUnitConstants.Common.DEFAULT_PATH);
	}

	/**
	 * Copy File all files from specified Backup to Test Directory
	 * 
	 * @param fileName
	 * @param directoryPath
	 * @throws IOException
	 */
	public static void copyAllFilesFromDirectory(String directory) throws IOException {
		File directoryPath = new File(JUnitConstants.Data.BACKUP_FILE_PATH + directory);
		for (File file : directoryPath.listFiles()) {
			copyFile(file.getName(), directory);
		}
	}

	/**
	 * Check number of occurrences of a file name
	 * 
	 * @param fileName
	 * @param count
	 * @return
	 */
	public static boolean checkFileCount(String fileName, int count) {
		File testDataDir = new File(JUnitConstants.Data.TEST_FILE_PATH);
		for (File testDataFile : testDataDir.listFiles()) {

			if (testDataFile.isDirectory())
				continue;

			if (testDataFile.getName().toUpperCase().startsWith(fileName.toUpperCase()))
				count--;
		}
		return count == 0;
	}

}
