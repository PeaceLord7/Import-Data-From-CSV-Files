package com.qa.pc.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qa.constants.Constants;
public class CSVReaderUtility {
	
	public static Object[][] csvReadData(String filePath) throws IOException {
		List<Object> cases = new ArrayList<Object>();
		String[] columnNames = null;
		
		String fileName = filePath;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String currentLine = null;
		currentLine = br.readLine();
		if(currentLine !=  null) {
			columnNames = currentLine.split(Constants.SEPARATORS);
		}
		
		String[] currentRow;
		while((currentLine = br.readLine()) != null) {
			Map<String,String> data = new HashMap<String,String>();
			currentRow = currentLine.split(Constants.SEPARATORS);
			int currentdata = 0;
			while(currentdata<columnNames.length) {
				try {
					data.put(columnNames[currentdata], currentRow[currentdata]);
				}catch(Exception ex) {
					data.put(columnNames[currentdata], null);
				}
				currentdata++;
			}
			cases.add(data);
		}
		br.close();
		List<Object> returnData = new ArrayList<Object>();
		int i = 0;
		while(i<cases.size()) {
			String testRun = (String)((Map<?,?>) cases.get(i)).get("testRun"); 
			if(testRun.equalsIgnoreCase(Constants.TEST_RUN_YES)) {
			returnData.add(cases.get(i));
			}
			i++;
		}
		int returnDataSize = returnData.size();
		Object [][] finalResult = new Object[returnDataSize][1];
		for(int k = 0;k < returnDataSize; k++) {
			finalResult[k][0] = returnData.get(k);
		}
		return finalResult;
	}
	
}
