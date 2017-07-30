package org.rpgcli.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Enemy;

public class EnemyRepositoryTest {

	@InjectMocks
	private EnemyRepository repoUnderTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetModelSourceName() throws Exception {
		assertEquals("enemies", repoUnderTest.getModelSourceName());
	}
	
	@Test
	public void testGetModelListNullData() throws Exception {
		List<Enemy> result = repoUnderTest.getModelList(null);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testGetModelListEmptyData() throws Exception {
		List<Enemy> result = repoUnderTest.getModelList(Collections.emptyList());
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testGetModelListRegularData() throws Exception {
		List<String[]> mockData = Arrays.asList(new String[]{"1", "enemy name", "10", "20", "15", "10"},
				new String[]{"2", "enemy name 2", "20", "40", "30", "20"});
		
		List<Enemy> result = repoUnderTest.getModelList(mockData);
		
		assertNotNull(result);
		assertEquals(2, result.size());
		
		Enemy enemy = result.get(0);
		assertEquals(Integer.valueOf(1), enemy.getId());
		assertEquals("enemy name", enemy.getName());
		assertEquals(Integer.valueOf(10), enemy.getHealthPoints());
		assertEquals(Integer.valueOf(20), enemy.getAttackPower());
		assertEquals(Integer.valueOf(15), enemy.getDefencePower());
		assertEquals(Integer.valueOf(10), enemy.getExperienceGiven());
		
		enemy = result.get(1);
		assertEquals(Integer.valueOf(2), enemy.getId());
		assertEquals("enemy name 2", enemy.getName());
		assertEquals(Integer.valueOf(20), enemy.getHealthPoints());
		assertEquals(Integer.valueOf(40), enemy.getAttackPower());
		assertEquals(Integer.valueOf(30), enemy.getDefencePower());
		assertEquals(Integer.valueOf(20), enemy.getExperienceGiven());
	}
	
	@Test
	public void testGetModelArrayNullObj() throws Exception {
		String[] result = repoUnderTest.getModelArray(null);
		
		assertNotNull(result);
		assertEquals(0, result.length);
	}
	
	@Test
	public void testGetModelArray() throws Exception {
		assertEquals(0, repoUnderTest.getModelArray(null).length);
		assertEquals(0, repoUnderTest.getModelArray(new Enemy()).length);
	}
}
