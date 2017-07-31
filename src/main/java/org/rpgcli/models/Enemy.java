package org.rpgcli.models;

public class Enemy extends Fighter {

	private Integer experienceGiven;

	public Enemy() {
		super();
	}
	
	public Enemy(Enemy original) {
		this.setId(original.getId());
		this.setName(original.getName());
		this.setHealthPoints(original.getHealthPoints());
		this.setAttackPower(original.getAttackPower());
		this.setDefencePower(original.getDefencePower());
		this.setExperienceGiven(original.getExperienceGiven());
	}
	
	public Integer getExperienceGiven() {
		return experienceGiven;
	}

	public void setExperienceGiven(Integer xpGiven) {
		this.experienceGiven = xpGiven;
	}
}
