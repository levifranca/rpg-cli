package org.rpgcli.views;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.PlayerCharacter;

public class PlayerStatusHeaderViewTest extends AbstractViewTest<PlayerStatusHeaderView> {

	@Mock
	private PlayerCharacter playerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		viewUnderTest = new PlayerStatusHeaderView(playerMock);
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {
		when(playerMock.getName()).thenReturn("Hero");
		when(playerMock.getHealthPoints()).thenReturn(100);
		when(playerMock.getAttackPower()).thenReturn(20);
		when(playerMock.getDefencePower()).thenReturn(15);
		when(playerMock.getExperiencePoints()).thenReturn(10);
		viewUnderTest.draw();
		
		String expected = "Name: Hero\t"
				+ "\u001B[31mHP: 100\u001B[0m\t"
				+ "\u001B[36mAT: 20\u001B[0m\t"
				+ "\u001B[32mDF: 15\u001B[0m\t"
				+ "\u001B[34mXP: 10\u001B[0m\n";
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		assertEquals("", consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testGetHeaderView() throws Exception {
		assertNull(viewUnderTest.getHeaderView());
	}
}
