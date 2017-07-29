package org.rpgcli.models;

import java.util.List;

public class Location implements Model {
	
	private Integer id;
	private String name;
	private String description;
	private List<Enemy> availableEnemies;
	private List<Location> closebyLocations;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Enemy> getAvailableEnemies() {
		return availableEnemies;
	}
	public void setAvailableEnemies(List<Enemy> availableEnemies) {
		this.availableEnemies = availableEnemies;
	}
	public List<Location> getClosebyLocations() {
		return closebyLocations;
	}
	public void setClosebyLocations(List<Location> closebyLocations) {
		this.closebyLocations = closebyLocations;
	}
}