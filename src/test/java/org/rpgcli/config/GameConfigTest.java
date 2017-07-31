package org.rpgcli.config;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

public class GameConfigTest {

	private GameConfig classUnderTest = GameConfig.getInstance();
	
	@After
	public void teardown() {
		GameConfig.getInstance().setTheme("default");
	}
	
	@Test
	public void testGetInstanceReturnsSameInstance() throws Exception {
		assertEquals(classUnderTest, GameConfig.getInstance());
	}
	
	@Test
	public void testSetNullThemeKeepsOld() throws Exception {
		GameConfig.getInstance().setTheme("default");
		
		GameConfig.getInstance().setTheme(null);
		assertEquals("default", GameConfig.getInstance().getTheme());
	}
	
	@Test
	public void testSetEmptyThemeKeepsOld() throws Exception {
		GameConfig.getInstance().setTheme("default");
		
		GameConfig.getInstance().setTheme("");
		assertEquals("default", GameConfig.getInstance().getTheme());
	}
	
	@Test
	public void testSetBlankThemeKeepsOld() throws Exception {
		GameConfig.getInstance().setTheme("default");
		
		GameConfig.getInstance().setTheme(" ");
		assertEquals("default", GameConfig.getInstance().getTheme());
	}
	
	@Test
	public void testGetSetRegularTheme() throws Exception {
		GameConfig.getInstance().setTheme("test");
		assertEquals("test", GameConfig.getInstance().getTheme());
	}
}
