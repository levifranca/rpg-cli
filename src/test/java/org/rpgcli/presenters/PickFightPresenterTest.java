package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Enemy;
import org.rpgcli.models.Location;
import org.rpgcli.models.Player;
import org.rpgcli.views.PickFightView;

public class PickFightPresenterTest {

	@InjectMocks
	private PickFightPresenter presenterUnderTest;
	
	@Mock
	private PickFightView viewMock;
	
	@Mock
	private Player player;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setView(viewMock);
	}
	
	@Test
	public void testStart() throws Exception {
		presenterUnderTest.start();
		
		verify(viewMock).draw();
	}
	
	@Test
	public void testInvalidInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("4");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testInvalidEmptyInput() throws Exception {
		presenterUnderTest.setInput("");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testInvalidBlankInput() throws Exception {
		presenterUnderTest.setInput(" ");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testInvalidNumberInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("z");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testZeroInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("0");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testValidInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("2");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof FightPresenter);
	}
	
	@Test
	public void testBackInput() throws Exception {
		
		presenterUnderTest.setInput("B");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof LocationPresenter);
	}

	private void mockPlayerData() {
		List<Enemy> enemies = new ArrayList<>();
		Enemy locMock = new Enemy();
		locMock.setId(1);
		enemies.add(locMock);
		locMock = new Enemy();
		locMock.setId(2);
		enemies.add(locMock);
		locMock = new Enemy();
		locMock.setId(3);
		enemies.add(locMock);
		
		Location mockLocation = mock(Location.class);
		when(mockLocation.getAvailableEnemies()).thenReturn(enemies);
		when(player.getCurrentLocation()).thenReturn(mockLocation);
		
	}
}
