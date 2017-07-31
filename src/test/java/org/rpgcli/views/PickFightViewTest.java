package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Enemy;
import org.rpgcli.models.Location;
import org.rpgcli.models.Player;

public class PickFightViewTest extends AbstractViewTest<PickFightView> {

	@Mock
	private Player playerMock;
	
	@Mock
	private PlayerStatusHeaderView headerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		viewUnderTest = new PickFightView(playerMock);
		viewUnderTest = spy(viewUnderTest);
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {	
		when(viewUnderTest.getHeaderView()).thenReturn(null);
		
		List<Enemy> enemiesMock = new ArrayList<>();
		Enemy enemyMock = new Enemy();
		enemyMock.setName("Enemy 1");
		enemyMock.setAttackPower(10);
		enemyMock.setDefencePower(20);
		enemiesMock.add(enemyMock);
		enemyMock = new Enemy();
		enemyMock.setName("Enemy 2");
		enemyMock.setAttackPower(20);
		enemyMock.setDefencePower(30);
		enemiesMock.add(enemyMock);
		Location currLocationMock = mock(Location.class);
		when(currLocationMock.getAvailableEnemies()).thenReturn(enemiesMock);
		when(playerMock.getCurrentLocation()).thenReturn(currLocationMock);
		
		viewUnderTest.draw();
		
		String expected = "Pick an enemy to fight:\n\n"
				+ "1. Enemy 1\tAT: 10\tDF: 20\n"
				+ "2. Enemy 2\tAT: 20\tDF: 30\n"
				+ "\n\n"
				+ "B. Back\n";
		
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
		assertNotNull(viewUnderTest.getHeaderView());
		assertTrue(viewUnderTest.getHeaderView() instanceof PlayerStatusHeaderView);
	}
}
