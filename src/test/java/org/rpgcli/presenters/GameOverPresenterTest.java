package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Player;
import org.rpgcli.views.GameOverView;

public class GameOverPresenterTest {

	@InjectMocks
	private GameOverPresenter presenterUnderTest;
	
	@Mock
	private GameOverView mockView;
	
	@Mock
	private Player playerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setView(mockView);
	}
	
	@Test
	public void testStart() throws Exception {
		presenterUnderTest.start();
		
		verify(mockView).draw();
	}
	
	@Test
	public void testOnInput() throws Exception {
		presenterUnderTest.setInput("");

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof StartMenuPresenter);
		
		presenterUnderTest.setInput(null);

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof StartMenuPresenter);
		
		presenterUnderTest.setInput("a");

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof StartMenuPresenter);
		
	}
	
}
