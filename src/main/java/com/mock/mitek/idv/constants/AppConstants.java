package com.mock.mitek.idv.constants;

import java.util.Map;
import java.util.Set;

public class AppConstants {

	public final static String FILE_LOCATION = "files/";
	
	private static Set<Map<String, String>> fileManager;
	
	private static String fileToRead;

	public static Set<Map<String, String>> getFileManager() {
		return fileManager;
	}

	public static void setFileManager(Set<Map<String, String>> fileManager) {
		AppConstants.fileManager = fileManager;
	}

	public static String getFileToRead() {
		return fileToRead;
	}

	public static void setFileToRead(String fileToRead) {
		AppConstants.fileToRead = fileToRead;
	}
}
