package org.rpgcli.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {

	private Player modelUnderTest = new Player();
	
	@Test
	public void testAttackGreaterThanDefence() throws Exception {
		
		Fighter enemyMock = new Enemy();
		enemyMock.setDefencePower(50);
		enemyMock.setHealthPoints(100);
		
		modelUnderTest.setAttackPower(100);
		
		modelUnderTest.attack(enemyMock);
		
		// As Attacker AP is greater than Opponent's DP, calc here is: 
		// Hitpoint = ceil[(Attacker AP - Opponent DP)/2]
		
		assertEquals(Integer.valueOf(75), enemyMock.getHealthPoints());
		
	}
	
	@Test
	public void testAttackEqualsDefence() throws Exception {
		
		Fighter enemyMock = new Enemy();
		enemyMock.setDefencePower(100);
		enemyMock.setHealthPoints(100);
		
		modelUnderTest.setAttackPower(100);
		
		modelUnderTest.attack(enemyMock);
		
		// As Attacker AP is less than or equals to Opponent DP, calc here is: 
		// Hitpoint = 1
		
		assertEquals(Integer.valueOf(99), enemyMock.getHealthPoints());
		
	}
	
	@Test
	public void testAttackLessThanDefence() throws Exception {
		
		Fighter enemyMock = new Enemy();
		enemyMock.setDefencePower(200);
		enemyMock.setHealthPoints(100);
		
		modelUnderTest.setAttackPower(100);
		
		modelUnderTest.attack(enemyMock);
		
		// As Attacker AP is less than or equals to Opponent DP, calc here is: 
		// Hitpoint = 1
		
		assertEquals(Integer.valueOf(99), enemyMock.getHealthPoints());
		
	}
	
	@Test
	public void testIsDeadGreaterThanZero() throws Exception {
		modelUnderTest.setHealthPoints(1);
		assertFalse(modelUnderTest.isDead());
	}
	
	@Test
	public void testIsDeadZero() throws Exception {
		modelUnderTest.setHealthPoints(0);
		assertTrue(modelUnderTest.isDead());
	}
	
	@Test
	public void testIsDeadLessThanZero() throws Exception {
		modelUnderTest.setHealthPoints(-1);
		assertTrue(modelUnderTest.isDead());
	}
	
	@Test
	public void testGainExperienceGreaterThanZero() throws Exception {
		modelUnderTest.setExperiencePoints(10);
		
		modelUnderTest.gainExperience(10);
		
		assertEquals(Integer.valueOf(20), modelUnderTest.getExperiencePoints());
	}

	@Test
	public void testGainExperienceLessThanZero() throws Exception {
		modelUnderTest.setExperiencePoints(10);
		
		modelUnderTest.gainExperience(-10);
		
		assertEquals(Integer.valueOf(10), modelUnderTest.getExperiencePoints());
	}

	@Test
	public void testGainExperienceZero() throws Exception {
		modelUnderTest.setExperiencePoints(10);
		
		modelUnderTest.gainExperience(0);
		
		assertEquals(Integer.valueOf(10), modelUnderTest.getExperiencePoints());
	}
}
