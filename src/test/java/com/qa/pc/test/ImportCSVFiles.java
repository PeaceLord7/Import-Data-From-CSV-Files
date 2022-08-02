package com.qa.pc.test;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.qa.constants.Constants;
import com.qa.pc.driver.Driver;
import com.qa.pc.search.SearchGoogle;
import com.qa.pc.utility.CSVReaderUtility;
import com.qa.pc.utility.GetCSVDataUtility;


public class ImportCSVFiles extends Driver{

	private static final String Data = "ImportCSVFiles.csv";
	
	@Factory(dataProvider = "importCsvFiles")
	public ImportCSVFiles(Map<String, String> scenario) throws IOException {
		super(scenario);
	}
	
	@Test
	public void importCsvFiles() throws Exception{
		SearchGoogle search = new SearchGoogle(driver);
		search.goRun(getscenario().get(Constants.SEARCH_STRING));
	}

	@DataProvider(name = "importCsvFiles")
	public static Object[][] getData() throws IOException{
		return CSVReaderUtility.csvReadData(GetCSVDataUtility.getCSVFilePath(Data, ImportCSVFiles.class.getName()));
	}
	
}
