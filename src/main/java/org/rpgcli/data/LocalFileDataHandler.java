package org.rpgcli.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.utils.StringUtils;

public class LocalFileDataHandler implements DataHandler {

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
	public boolean saveData(String dataSourceName, List<String[]> dataArray) {
		if (StringUtils.isBlank(dataSourceName)) {
			return false;
		}
		
		String userDir = System.getProperty("user.dir");
		String filename = userDir + "/" + dataSourceName + ".dat";
		File file = new File(filename);

		try (FileWriter fw = new FileWriter(file)) {
			file.createNewFile();
			
			List<String> recordsAsString = dataArray.stream()
													.map(record -> recordArrayAsString(record))
													.collect(Collectors.toList());
			
			// Regular foreach loop to leverage try-catch
			for (String record : recordsAsString) {
				fw.write(record);
				fw.write("\n");
			}
			
		} catch (IOException e) {
			return false;
		}
		
		
		return true;
	}

	private String recordArrayAsString(String[] record) {
		StringBuilder sb = new StringBuilder();
		for (String str : record) {
			sb.append("\"").append(str).append("\",");
		}
		// remove trailing comma
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
}
