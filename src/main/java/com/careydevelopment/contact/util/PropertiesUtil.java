package com.careydevelopment.contact.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	private Properties properties = new Properties();	
	
	public PropertiesUtil(String filePath) {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			throw new RuntimeException("Can't find file: " + filePath);
		} catch (IOException ie) {
			ie.printStackTrace();
			throw new RuntimeException(ie.getMessage());
		}
	}
	
	
	public String getProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}
}
