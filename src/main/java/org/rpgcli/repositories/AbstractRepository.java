package org.rpgcli.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.rpgcli.data.DataHandler;
import org.rpgcli.models.Model;

public abstract class AbstractRepository<T extends Model> implements Repository<T> {

	private DataHandler dataHandler;
	private Map<Integer, T> cache = new HashMap<>();

	public AbstractRepository(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}
	
	@Override
	public T findById(Integer id) {
		if (cache.isEmpty()) {
			// populate cache
			findAll();
		}
		
		return cache.get(id);
	}
	
	@Override
	public List<T> findAll() {
		if (!cache.isEmpty()) {
			return getDataSorted(cache.values());
		}
		
		List<String[]> data = dataHandler.fetchData(getModelSourceName());
		
		List<T> classes = getModelList(data);
		
		if (!classes.isEmpty()) {
			// Populate cache map to avoid open file every time
			classes.stream().forEach(charClass -> cache.put(charClass.getId(), charClass));
		}
		
		return classes;
	}

	private List<T> getDataSorted(Collection<T> collection) {
		return collection.stream()
						 .sorted((o1, o2) -> {
							 if (o1 == null || o1.getId() == null) {
								 return -1;
							 }
							 if (o2 == null || o2.getId() == null) {
								 return 1;
							 }
							 return Integer.compare(o1.getId(), o2.getId());
							})
						 .collect(Collectors.toList());
	}
	
	@Override
	public void save(T data) {
		if (cache.isEmpty()) {
			// populate cache
			findAll();
		}
		if (data.getId() == null) {
			List<T> allDataSorted = getDataSorted(cache.values());
			data.setId(allDataSorted.size()+1);	
		}
		cache.put(data.getId(), data);
		
		List<T> allDataSorted = getDataSorted(cache.values());
		List<String[]> dataAsArray = allDataSorted.stream()
												  .map(record -> getModelArray(record))
												  .collect(Collectors.toList());
		
		dataHandler.saveData(getModelSourceName(), dataAsArray);
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}
	
	protected abstract String getModelSourceName();
	protected abstract List<T> getModelList(List<String[]> data);
	protected abstract String[] getModelArray(T data);
}
