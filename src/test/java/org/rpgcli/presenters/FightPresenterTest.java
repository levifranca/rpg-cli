package org.rpgcli.presenters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Enemy;
import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.utils.Constants;
import org.rpgcli.views.FightView;

public class FightPresenterTest {
	
	@InjectMocks
	private FightPresenter presenterUnderTest;
	
	@Mock
	private FightView mockView;
	
	@Mock
	private PlayerCharacter playerMock;
	
	@Mock
	private Enemy enemyMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setView(mockView);
		presenterUnderTest.setPlayer(playerMock);
		presenterUnderTest.setEnemy(enemyMock);
	}
	
	@Test
	public void testStart() throws Exception {
		presenterUnderTest.start();
		
		verify(mockView).draw();
	}
	
	@Test
	public void testInvalidInput() throws Exception {
		presenterUnderTest.setInput("z");
		
		verify(mockView).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testEmptyInput() throws Exception {
		presenterUnderTest.setInput("");
		
		verify(mockView).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testBlankInput() throws Exception {
		presenterUnderTest.setInput(" ");
		
		verify(mockView).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testAttackNoDeathInput() throws Exception {
		presenterUnderTest.setInput(Constants.ATTACK_OPTION);

		verify(playerMock).attack(ArgumentMatchers.eq(enemyMock));
		verify(enemyMock).isDead();
		verify(enemyMock).attack(ArgumentMatchers.eq(playerMock));
		verify(playerMock).isDead();
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof FightPresenter);
		assertEquals(presenterUnderTest, presenterUnderTest.getNextPresenter());
	}
	
	@Test
	public void testAttackVictoryInput() throws Exception {
		when(enemyMock.isDead()).thenReturn(true);
		
		presenterUnderTest.setInput(Constants.ATTACK_OPTION);

		verify(playerMock).attack(ArgumentMatchers.eq(enemyMock));
		verify(enemyMock).isDead();
		
		verify(enemyMock, never()).attack(ArgumentMatchers.eq(playerMock));
		verify(playerMock, never()).isDead();
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof VictoryPresenter);
	}
	
	@Test
	public void testAttackGameOverInput() throws Exception {
		when(playerMock.isDead()).thenReturn(true);
		
		presenterUnderTest.setInput(Constants.ATTACK_OPTION);

		verify(playerMock).attack(ArgumentMatchers.eq(enemyMock));
		verify(enemyMock).isDead();
		
		verify(enemyMock).attack(ArgumentMatchers.eq(playerMock));
		verify(playerMock).isDead();
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof GameOverPresenter);
	}
	
	@Test
	public void testDefendNoDeathInput() throws Exception {
		presenterUnderTest.setInput(Constants.DEFEND_OPTION);
		
		verify(enemyMock).attack(ArgumentMatchers.eq(playerMock));
		verify(playerMock).isDead();
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof FightPresenter);
		assertEquals(presenterUnderTest, presenterUnderTest.getNextPresenter());
	}
	
	@Test
	public void testDefendGameOverInput() throws Exception {
		when(playerMock.isDead()).thenReturn(true);
		
		presenterUnderTest.setInput(Constants.DEFEND_OPTION);
		
		verify(enemyMock).attack(ArgumentMatchers.eq(playerMock));
		verify(playerMock).isDead();
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof GameOverPresenter);
	}
	
	@Test
	public void testRunAwayInput() throws Exception {
		presenterUnderTest.setInput(Constants.RUN_AWAY_OPTION);
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickFightPresenter);
	}

}
