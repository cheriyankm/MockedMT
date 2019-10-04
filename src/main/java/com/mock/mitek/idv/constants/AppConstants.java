package com.mock.mitek.idv.constants;

import java.util.Map;
import java.util.Set;

public class AppConstants {

	public final static String FILE_LOCATION = "files/";
	
	private static Set<Map<String, String>> fileManager;
	
	private static String fileToRead;
	
	private static String checkId;
	
	private static String refId;

	public static String getRefId() {
		return refId;
	}

	public static void setRefId(String refId) {
		AppConstants.refId = refId;
	}

	public static String getCheckId() {
		return checkId;
	}

	public static void setCheckId(String checkId) {
		AppConstants.checkId = checkId;
	}

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
