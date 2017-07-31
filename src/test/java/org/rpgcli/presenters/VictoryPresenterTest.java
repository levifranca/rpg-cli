package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Player;
import org.rpgcli.views.VictoryView;

public class VictoryPresenterTest {
	
	@InjectMocks
	private VictoryPresenter presenterUnderTest;
	
	@Mock
	private VictoryView mockView;
	
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
		presenterUnderTest.setExperienceWon(1);
		presenterUnderTest.setInput("");

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickFightPresenter);
		
		presenterUnderTest.setInput(null);

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickFightPresenter);
		
		presenterUnderTest.setInput("a");

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickFightPresenter);
		
		verify(playerMock, times(3)).gainExperience(ArgumentMatchers.anyInt());
	}
	
}
