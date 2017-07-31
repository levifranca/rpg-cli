package org.rpgcli.models;

public abstract class Fighter implements Model {

	private Integer id;
	private String name;
	private Integer healthPoints;
	private Integer attackPower;
	private Integer defencePower;

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

	public boolean isDead() {
		return healthPoints <= 0;
	}

	public void attack(Fighter opponent) {
		if (attackPower > opponent.defencePower) {
			Integer hitpoits = (int) Math.ceil(((double)attackPower-opponent.defencePower)/2);
			
			opponent.setHealthPoints(opponent.healthPoints - hitpoits);
			
			return;
		}
		
		opponent.setHealthPoints(opponent.healthPoints - 1);
	}

}
