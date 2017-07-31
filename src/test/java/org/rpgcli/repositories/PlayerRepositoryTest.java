package org.rpgcli.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.config.GameConfig;
import org.rpgcli.data.DataHandler;
import org.rpgcli.models.CharacterClass;
import org.rpgcli.models.Location;
import org.rpgcli.models.Player;

public class PlayerRepositoryTest {

	@InjectMocks
	private PlayerRepository repoUnderTest;

	@Mock
	private DataHandler dataHandlerMock;
	
	@Mock
	private CharacterClassRepository charClassRepoMock;
	
	@Mock
	private LocationRepository locRepoMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		spy(repoUnderTest);
		
		CharacterClass charClassMock = new CharacterClass();
		charClassMock.setName("class 1");
		when(charClassRepoMock.findById(1)).thenReturn(charClassMock);
		charClassMock = new CharacterClass();
		charClassMock.setName("class 2");
		when(charClassRepoMock.findById(2)).thenReturn(charClassMock);
		
		Location locationMock = new Location();
		locationMock.setName("loc 1");
		when(locRepoMock.findById(1)).thenReturn(locationMock);
		locationMock = new Location();
		locationMock.setName("loc 2");
		when(locRepoMock.findById(2)).thenReturn(locationMock);
		
	}

	@After
	public void teardown() {
		GameConfig.getInstance().setTheme("default");
	}
	
	@Test
	public void testGetModelSourceName() throws Exception {
		assertEquals("players", repoUnderTest.getModelSourceName());
	}
	
	@Test
	public void testGetModelListNullData() throws Exception {
		List<Player> result = repoUnderTest.getModelList(null);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testGetModelListEmptyData() throws Exception {
		List<Player> result = repoUnderTest.getModelList(Collections.emptyList());
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testGetModelListRegularData() throws Exception {
		List<String[]> mockData = Arrays.asList(new String[]{"1", "player name", "2", "1", "10", "20", "15", "10", "theme1"},
				new String[]{"2", "player name 2", "1", "2", "20", "40", "30", "20", "theme2"});
		
		List<Player> result = repoUnderTest.getModelList(mockData);
		
		assertNotNull(result);
		assertEquals(2, result.size());
		
		Player player = result.get(0);
		assertEquals(Integer.valueOf(1), player.getId());
		assertEquals("player name", player.getName());
		assertEquals("class 2", player.getCharClass().getName());
		assertEquals(Integer.valueOf(10), player.getHealthPoints());
		assertEquals(Integer.valueOf(20), player.getAttackPower());
		assertEquals(Integer.valueOf(15), player.getDefencePower());
		assertEquals(Integer.valueOf(10), player.getExperiencePoints());
		assertEquals("loc 1", player.getCurrentLocation().getName());
		assertEquals("theme1", player.getTheme());
		
		player = result.get(1);
		assertEquals(Integer.valueOf(2), player.getId());
		assertEquals("player name 2", player.getName());
		assertEquals("class 1", player.getCharClass().getName());
		assertEquals(Integer.valueOf(20), player.getHealthPoints());
		assertEquals(Integer.valueOf(40), player.getAttackPower());
		assertEquals(Integer.valueOf(30), player.getDefencePower());
		assertEquals(Integer.valueOf(20), player.getExperiencePoints());
		assertEquals("theme2", player.getTheme());
	}
	
	@Test
	public void testGetModelArrayNullObj() throws Exception {
		String[] result = repoUnderTest.getModelArray(null);
		
		assertNotNull(result);
		assertEquals(0, result.length);
	}
	
	@Test
	public void testGetModelArrayRegular() throws Exception {
		Player mockPlayer = new Player();
		mockPlayer.setId(1);
		mockPlayer.setName("player name 1");
		CharacterClass mockCharClass = new CharacterClass();
		mockCharClass.setId(2);
		mockPlayer.setCharClass(mockCharClass);
		Location mockCurrentLocation = new Location();
		mockCurrentLocation.setId(3);
		mockPlayer.setCurrentLocation(mockCurrentLocation);
		mockPlayer.setHealthPoints(10);
		mockPlayer.setAttackPower(20);
		mockPlayer.setDefencePower(30);
		mockPlayer.setExperiencePoints(40);
		mockPlayer.setTheme("theme");
		
		String[] result =  repoUnderTest.getModelArray(mockPlayer);
		
		assertEquals(9, result.length);
		assertEquals("1", result[0]);
		assertEquals("player name 1", result[1]);
		assertEquals("2", result[2]);
		assertEquals("3", result[3]);
		assertEquals("10", result[4]);
		assertEquals("20", result[5]);
		assertEquals("30", result[6]);
		assertEquals("40", result[7]);
		assertEquals("theme", result[8]);
	}
	
	@Test
	public void testSaveWithIDData() throws Exception {
		Player data = new Player();
		data.setId(1);
		data.setName("player name 1");
		CharacterClass mockCharClass = new CharacterClass();
		mockCharClass.setId(2);
		data.setCharClass(mockCharClass);
		Location mockCurrentLocation = new Location();
		mockCurrentLocation.setId(3);
		data.setCurrentLocation(mockCurrentLocation);
		data.setHealthPoints(10);
		data.setAttackPower(20);
		data.setDefencePower(30);
		data.setExperiencePoints(40);
		
		repoUnderTest.save(data);
		
		verify(dataHandlerMock).saveData(ArgumentMatchers.eq("players"), ArgumentMatchers.anyList());
	}
	
	@Test
	public void testSaveNoIDData() throws Exception {
		Player data = new Player();
		data.setName("player name 1");
		CharacterClass mockCharClass = new CharacterClass();
		mockCharClass.setId(2);
		data.setCharClass(mockCharClass);
		Location mockCurrentLocation = new Location();
		mockCurrentLocation.setId(3);
		data.setCurrentLocation(mockCurrentLocation);
		data.setHealthPoints(10);
		data.setAttackPower(20);
		data.setDefencePower(30);
		data.setExperiencePoints(40);
		
		repoUnderTest.save(data);
		
		verify(dataHandlerMock).saveData(ArgumentMatchers.eq("players"), ArgumentMatchers.anyList());
	}

	@Test
	public void testFindAllByTheme() throws Exception {
		List<String[]> mockPlayers = new ArrayList<>();
		mockPlayers.add(new String[]{"1", "name1", "1", "2", "64", "20", "15", "0", "t1"});
		mockPlayers.add(new String[]{"2", "name2", "2", "2", "64", "20", "15", "0", "t1"});
		mockPlayers.add(new String[]{"4", "name3", "3", "2", "64", "20", "15", "0", "test"});
		mockPlayers.add(new String[]{"5", "name5", "5", "2", "64", "20", "15", "0", "t2"});
		
		when(dataHandlerMock.fetchData("players")).thenReturn(mockPlayers);
		
		GameConfig.getInstance().setTheme("test");
		List<Player> result = repoUnderTest.findAllByTheme("test");
		
		assertNotNull(result);
		assertEquals(1, result.size());
		
		Player player = result.get(0);
		assertEquals(Integer.valueOf(4), player.getId());
		assertEquals("test", player.getTheme());
	}
}
