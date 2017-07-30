package org.rpgcli.data;
import java.util.List;

public interface DataHandler {
	
	public List<String[]> fetchData(String dataSourceName);
	public boolean saveData(String dataSourceName, List<String[]> dataArray);
	
}
