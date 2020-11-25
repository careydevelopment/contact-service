package com.careydevelopment.ecosystem.user.util;

import org.apache.commons.lang3.StringUtils;

import com.careydevelopment.ecosystem.user.model.User;

public class FileNameUtil {

    
	public static String createFileName(User user) {
		String fileName = null;
		
		if (user == null) throw new IllegalArgumentException("User cannot be null!");
		if (StringUtils.isEmpty(user.getId())) throw new IllegalArgumentException("User ID cannot be null!");
		
		Long currentTime = System.currentTimeMillis();
		
		StringBuilder builder = new StringBuilder(user.getId());
		builder.append("-");
		builder.append(currentTime.toString());
		
		fileName = builder.toString();
		
		return fileName;
	}
	
	
	public static String createFileName(User user, String originalFileName) {
		String fileName = createFileName(user);

		fileName = appendExtensionFromOriginalFileName(fileName, originalFileName);
		
		return fileName;
	}
	
	
	public static String appendExtensionFromOriginalFileName(String fileName, String originalFileName) {
		if (StringUtils.isEmpty(fileName)) throw new IllegalArgumentException("File name can't be null!");
		if (StringUtils.isEmpty(originalFileName)) throw new IllegalArgumentException("Original file name can't be null!");
		
		StringBuilder builder = new StringBuilder(fileName);
		if (!fileName.endsWith(".")) builder.append(".");
		
		String currentExtension = getCurrentExtensionFromFileName(originalFileName);
		builder.append(currentExtension);
		
		String newFileName = builder.toString();
		
		return newFileName;
	}
	
	
	public static String getCurrentExtensionFromFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) throw new IllegalArgumentException("File name can't be null!");
		if (fileName.indexOf(".") == -1) throw new IllegalArgumentException("File name doesn't have an extension!");
		
		int lastPeriodLoc = fileName.lastIndexOf(".");
		String extension = fileName.substring(lastPeriodLoc + 1, fileName.length());
		
		return extension;
	}
	
	
	public static boolean fileNameHasSpecialChars(String fileName) {
		if (StringUtils.isEmpty(fileName)) throw new IllegalArgumentException("File name can't be null!");
		
		return false;
	}
}
