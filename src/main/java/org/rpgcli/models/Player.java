package org.rpgcli.models;

public class Player extends Fighter {

	private CharacterClass charClass;
	private Integer experiencePoints;
	private Location currentLocation;

	public static Player newPlayer(String name, CharacterClass charClass) {
		Player player = new Player();
		player.setName(name);
		player.setCharClass(charClass);
		player.setHealthPoints(100);
		player.setAttackPower(20);
		player.setDefencePower(15);
		player.setExperiencePoints(0);
		player.setCurrentLocation(charClass.getStartLocation());
		
		return player;
	}

	public CharacterClass getCharClass() {
		return charClass;
	}

	public void setCharClass(CharacterClass charClass) {
		this.charClass = charClass;
	}

	public Integer getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(Integer experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

}
