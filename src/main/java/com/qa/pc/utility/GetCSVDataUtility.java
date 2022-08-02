package com.qa.pc.utility;

import org.testng.log4testng.Logger;

import com.qa.constants.Constants;

public class GetCSVDataUtility {

	private static final Logger LOGGER = Logger.getLogger(GetCSVDataUtility.class);
	
	public static String getCSVFilePath(String fileName,String packageName) {
		if(!(StringUtility.isEmpty_Or_Blank(fileName) && StringUtility.isEmpty_Or_Blank(packageName))) {
			String csvPath = null;
			if(packageName.contains(Constants.TEST)) {
				csvPath = Constants.CLASSPATH + Constants.CSV_CLASSPATH + Constants.CSVS_PATH + fileName;
			}
			return csvPath;
		}
		else {
			LOGGER.warn("File not found: " + fileName + "\n");
			return null;
		}
	}
	
}
