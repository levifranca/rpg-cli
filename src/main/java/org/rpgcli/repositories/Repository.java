package org.rpgcli.repositories;

import java.util.List;

import org.rpgcli.models.Model;

public interface Repository<T extends Model> {

	public T findById(Integer id);
	public List<T> findAll();
	
	public void save(T data);
}
