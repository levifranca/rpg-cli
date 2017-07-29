package org.rpgcli.dataproviders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LocalFileDataHandler implements DataProvider, DataSaver {

	@Override
	public List<String[]> fetchData(String dataSourceName) {
		String userDir = System.getProperty("user.dir");
		String filename = userDir + "/" + dataSourceName + ".dat";
		File file = new File(filename);
		if (!file.exists()) {
			return Collections.emptyList();
		}
		
		List<String[]> data = null;
		try (FileReader fr = new FileReader(file)) {
			BufferedReader br = new BufferedReader(fr);
			
			data = br.lines().map(filteredLine -> {
								 filteredLine = filteredLine.substring(1, filteredLine.length()-1);
								 String[] array = filteredLine.split("\",\"");
								 return array;
							 }).collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			data = Collections.emptyList();
		} catch (IOException e) {
			if (data == null) {
				data = Collections.emptyList();
			}
		}
		
		return data;
	}

	@Override
	public void saveData(String... dataArray) {
		// TODO Auto-generated method stub
		
	}

}
