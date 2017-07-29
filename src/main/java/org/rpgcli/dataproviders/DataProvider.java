package org.rpgcli.dataproviders;
import java.util.List;

public interface DataProvider {
	
	public List<String[]> fetchData(String dataSourceName);
	
}
