package org.rpgcli.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.rpgcli.dataproviders.DataProvider;
import org.rpgcli.models.Model;

public abstract class AbstractRepository<T extends Model> implements Repository<T> {

	private DataProvider dataProvider;

	private Map<Integer, T> cache = new HashMap<>();

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
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
			return cache.values().stream()
								 .sorted((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
								 .collect(Collectors.toList());
		}
		
		List<String[]> data = getDataProvider().fetchData(getModelSourceName());
		
		List<T> classes = getModelList(data);
		
		if (!classes.isEmpty()) {
			// Populate cache map to avoid open file every time
			classes.stream().forEach(charClass -> cache.put(charClass.getId(), charClass));
		}
		
		return classes;
	}

	protected abstract String getModelSourceName();
	protected abstract List<T> getModelList(List<String[]> data);
	
}
