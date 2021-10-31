package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * StorageManager is a class designed to manage iChess data within .txt files. The data is expected to be comma separated values (csv).
 * @author Luis Alvarez Sanchez
 */
public class StorageManager {
	private static final int USER_NUM_ATTRIBUTES = 4; // username, password, wins, losses
	private static final String DEFAULT_FILE_NAME = "datastore.txt";
	
	private String filename;
	private int numItemsPerRow;
	
	/**
	 * Instantiates a StorageManager with the default filename. 
	 */
	public StorageManager() {
		filename = DEFAULT_FILE_NAME;
		numItemsPerRow = USER_NUM_ATTRIBUTES;
	}

	/**
	 * Instantiates StorageManager with a filename. 
	 * @param filename size > 0. Should include .txt
	 * @param numItemsPerRow must be greater than zero or the default will be used
	 */
	public StorageManager(String filename, int numItemsPerRow) {
		this.filename = filename.length() > 0 ? filename : DEFAULT_FILE_NAME;
		this.numItemsPerRow = numItemsPerRow > 0 ? numItemsPerRow : USER_NUM_ATTRIBUTES;
	}
	
	/**
	 * Returns a scanner loaded with the entire file.
	 * @return scanner if successful, null if not possible
	 */
	public Scanner getScanner() {
		try {
			return new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * writes values to the current file storage. Must be one line at a time
	 * @param str - data to be written
	 * @return true if successful, false if not
	 */
	public boolean write(String str) {
		if(!isValidEntry(str)) return false; // invalid entry
		
		
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(str + "\n"); // appending \n per write to ensure one entry per line in .txt file
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Ensures values entered are in csv format.
	 * Expected format:
	 * 		- Values separated by comma
	 * 		- Number of values entered matches the expected amount
	 * @param str
	 * @return	true if valid, false if not
	 */
	private boolean isValidEntry(String str) {
		return str.split(",").length == numItemsPerRow;
	}

	/**
	 * Creates a file.
	 * 
	 * @return true if file created successfully, false if file already exists.
	 * @throws Exception
	 */
	public boolean createFile() throws Exception {
		File file = new File(this.filename);
		return file.createNewFile();
	}

	/**
	 * Gets the filename the StorageManager is handling
	 * 
	 * @return
	 */
	protected String getfilename() {
		return filename;
	}

	/**
	 * Changes the file the StorageManager is handling.
	 *
	 * @param filename
	 */
	protected void setfilename(String filename) {
		this.filename = filename;
	}
}