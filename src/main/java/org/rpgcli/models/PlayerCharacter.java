package org.rpgcli.models;

public class PlayerCharacter implements Model {

	private Integer id;
	private String name;
	private CharacterClass charClass;
	private Integer healthPoints;
	private Integer attackPower;
	private Integer defencePower;
	private Location currentLocation;

	public PlayerCharacter(String name, CharacterClass charClass) {
		// New Player TODO (move to a factory)
		this.name = name;
		this.charClass = charClass;
		this.healthPoints = 100;
		this.attackPower = 20;
		this.defencePower = 15;
		this.currentLocation = charClass.getStartLocation();
	}

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

	public CharacterClass getCharClass() {
		return charClass;
	}

	public void setCharClass(CharacterClass charClass) {
		this.charClass = charClass;
	}

	public Integer getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(Integer healthPoints) {
		this.healthPoints = healthPoints;
	}

	public Integer getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(Integer attackPower) {
		this.attackPower = attackPower;
	}

	public Integer getDefencePower() {
		return defencePower;
	}

	public void setDefencePower(Integer defencePower) {
		this.defencePower = defencePower;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

}
