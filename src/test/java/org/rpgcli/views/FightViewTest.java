package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Enemy;
import org.rpgcli.models.PlayerCharacter;

public class FightViewTest extends AbstractViewTest<FightView> {

	@Mock
	private PlayerCharacter playerMock;

	@Mock
	private Enemy enemyMock;
	
	@Mock
	private PlayerStatusHeaderView headerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		viewUnderTest = new FightView(playerMock, enemyMock);
		viewUnderTest = spy(viewUnderTest);
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {	
		when(viewUnderTest.getHeaderView()).thenReturn(null);
		
		when(enemyMock.getName()).thenReturn("Enemy");
		when(enemyMock.getHealthPoints()).thenReturn(100);
		when(enemyMock.getAttackPower()).thenReturn(10);
		when(enemyMock.getDefencePower()).thenReturn(20);
		
		viewUnderTest.draw();
		
		String expected = "\nEnemy\tHP: 100\tAT: 10\tDF: 20\n\n\n"
				+ "A. Attack\n"
				+ "D. Defend\n"
				+ "R. Run Away\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		assertEquals("\u001B[41m\u001B[30m"
				+ "Invalid option! Please pick a valid option."
				+ "\u001B[0m\n", consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testGetHeaderView() throws Exception {
		assertNotNull(viewUnderTest.getHeaderView());
		assertTrue(viewUnderTest.getHeaderView() instanceof PlayerStatusHeaderView);
	}
}
