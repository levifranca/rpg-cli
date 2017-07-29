package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.presenters.PickNamePresenter;
import org.rpgcli.presenters.StartMenuPresenter;
import org.rpgcli.utils.Constants;
import org.rpgcli.views.StartMenuView;

public class StartMenuPresenterTest {
	
	@InjectMocks
	private StartMenuPresenter presenterUnderTest;
	
	@Mock
	private StartMenuView mockView;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
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
	
}
