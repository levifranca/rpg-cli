package org.rpgcli.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.exceptions.GeneralRuntimeException;
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
								 return filteredLine.split("\",\"");
							 }).collect(Collectors.toList());
		} catch (IOException e) {
			// We're not handling it for now
			throw new GeneralRuntimeException(e);
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
			if (!file.exists() && !file.createNewFile()) {
				return false;
			}
			
			List<String> recordsAsString = dataArray.stream()
													.map(this::recordArrayAsString)
													.collect(Collectors.toList());
			
			// Regular foreach loop to leverage try-catch
			for (String record : recordsAsString) {
				fw.write(record);
				fw.write("\n");
			}
			
		} catch (IOException e) {
			// We're not handling it for now
			throw new GeneralRuntimeException(e);
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
