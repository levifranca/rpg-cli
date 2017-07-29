package org.rpgcli.models;

public class Enemy implements Model {

	private Integer id;
	private String name;
	private Integer healthPoints;
	private Integer attackPower;
	private Integer defencePower;
	private Integer xpGiven;

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

	public Integer getXpGiven() {
		return xpGiven;
	}

	public void setXpGiven(Integer xpGiven) {
		this.xpGiven = xpGiven;
	}

}
