package org.rpgcli.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class XCSVDataHandler implements DataHandler {

	@Override
	public List<String[]> fetchData(String dataSourceName) {
		if (dataSourceName == null || dataSourceName.isEmpty()) {
			return Collections.emptyList();
		}
		
		String filename = "/" + dataSourceName + ".xcsv";
		
		InputStream is = getClass().getResourceAsStream(filename);
		if (is == null) {
			// File not found
			return Collections.emptyList();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List<String[]> data = br.lines().filter(line -> !line.startsWith("#"))
				  						.map(filteredLine -> {
											  filteredLine = filteredLine.substring(1, filteredLine.length()-1);
											  String[] array = filteredLine.split("\",\"");
											  return array;
										 }).collect(Collectors.toList());
								
		return data;
	}

	@Override
	public boolean saveData(String dataSourceName, List<String[]> dataArray) {
		// For now, in this project, we won't save data on eXtende-CSV files.
		return false;
	}

}