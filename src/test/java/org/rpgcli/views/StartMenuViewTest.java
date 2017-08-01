package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.rpgcli.models.Player;

public class StartMenuViewTest extends AbstractViewTest<StartMenuView> {

	@Before
	public void setup() {
		viewUnderTest = new StartMenuView();
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {
		viewUnderTest.setAsciiLogo(new String[]{"RPG-CLI"});
		viewUnderTest.draw();
		
		String expected = "Welcome to:\n\u001B[31mRPG-CLI\u001B[0m\n\n"
				+ "What would you like to do:\n"
				+ "N. New Game.\n"
				+ "\n"
				+ "Q. Quit.\n"
				+ "Enter your option below:\n";
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawWithSavedGames() throws Exception {
		List<Player> savedPlayersMock = new ArrayList<>();
		Player playerMock = new Player();
		playerMock.setName("player 1");
		playerMock.setExperiencePoints(10);
		savedPlayersMock.add(playerMock);
		playerMock = new Player();
		playerMock.setName("player 2");
		playerMock.setExperiencePoints(20);
		savedPlayersMock.add(playerMock);
		viewUnderTest.setSavedPlayers(savedPlayersMock);
		
		viewUnderTest.setAsciiLogo(new String[]{"RPG-CLI"});
		viewUnderTest.draw();
		
		String expected = "Welcome to:\n\u001B[31mRPG-CLI\u001B[0m\n\n"
				+ "What would you like to do:\n"
				+ "N. New Game.\n"
				+ "1. player 1\tXP: 10\n"
				+ "2. player 2\tXP: 20\n"
				+ "\n"
				+ "Q. Quit.\n"
				+ "Enter your option below:\n";
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		assertEquals("\u001B[41m\u001B[30m"
				+ "Invalid option! Please pick a valid option. Options are case-sensitive."
				+ "\u001B[0m\n", consoleWriterMock.getMockStream());
	}

	@Test
	public void testGetHeaderView() throws Exception {
		assertNull(viewUnderTest.getHeaderView());
	}
}
