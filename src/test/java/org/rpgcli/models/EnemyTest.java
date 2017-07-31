package org.rpgcli.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest {

	private Enemy modelUnderTest = new Enemy();
	
	@Test
	public void testClone() throws Exception {
		modelUnderTest.setId(1);
		modelUnderTest.setName("enemy name");
		modelUnderTest.setHealthPoints(10);
		modelUnderTest.setAttackPower(20);
		modelUnderTest.setDefencePower(30);
		modelUnderTest.setExperienceGiven(40);
		
		Enemy clonedEnemy = new Enemy(modelUnderTest);
		
		assertNotNull(clonedEnemy);
		assertEquals(Integer.valueOf(1), clonedEnemy.getId());
		assertEquals("enemy name", clonedEnemy.getName());
		assertEquals(Integer.valueOf(10), clonedEnemy.getHealthPoints());
		assertEquals(Integer.valueOf(20), clonedEnemy.getAttackPower());
		assertEquals(Integer.valueOf(30), clonedEnemy.getDefencePower());
		assertEquals(Integer.valueOf(40), clonedEnemy.getExperienceGiven());
	}
}
