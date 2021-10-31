package application;

import java.io.File;

public class StorageManager {
	private String fileName;
	
	/**
	 * Instantiates a StorageManager with the default filename.
	 */
	public StorageManager() {
		fileName = "datastore.txt";
	}
	
	/**
	 * Instantiates StorageManager with a fileName. 
	 * @param fileName
	 */
	public StorageManager(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	/**
	 * Creates a file.
	 * @return true if file created successfully, false if file already exists.
	 * @throws Exception 
	 */
	public boolean createFile() throws Exception{
		File file = new File(this.fileName);
		return file.createNewFile();
	}
	
	/**
	 * Gets the filename the StorageManager is handling
	 * @return
	 */
	protected String getFileName() {
		return fileName;
	}
	
	/**
	 * Changes the file the StorageManager is handling.
	 * @param fileName
	 */
	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}
}