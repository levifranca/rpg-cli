package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Player;
import org.rpgcli.repositories.PlayerRepository;
import org.rpgcli.utils.Constants;
import org.rpgcli.views.StartMenuView;

public class StartMenuPresenterTest {
	
	@InjectMocks
	private StartMenuPresenter presenterUnderTest;
	
	@Mock
	private StartMenuView mockView;
	
	@Mock
	private PlayerRepository playerRepoMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setSavedGames(Collections.emptyList());
		when(playerRepoMock.findAll()).thenReturn(Collections.emptyList());
		presenterUnderTest.setPlayerRepo(playerRepoMock);
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
	public void testQuitInput() throws Exception {
		presenterUnderTest.setInput(Constants.QUIT_OPTION);
		
		assertNull(presenterUnderTest.getNextPresenter());
	}
	
	@Test
	public void testNewGameInput() throws Exception {
		presenterUnderTest.setInput(Constants.NEW_GAME_OPTION);
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickNamePresenter);
	}
	
	@Test
	public void testNumberInputOutOfBound() throws Exception {
		presenterUnderTest.setInput("1");
		
		verify(mockView).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testPickSavedGame() throws Exception {
		List<Player> mockList =  new ArrayList<>();
		mockList.add(new Player());
		mockList.add(new Player());
		presenterUnderTest.setSavedGames(mockList);
		
		presenterUnderTest.setInput("2");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof LocationPresenter);
	}
}
