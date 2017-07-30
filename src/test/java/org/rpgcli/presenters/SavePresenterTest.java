package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Player;
import org.rpgcli.repositories.PlayerRepository;
import org.rpgcli.utils.Constants;
import org.rpgcli.views.SaveView;

public class SavePresenterTest {
	
	@InjectMocks
	private SavePresenter presenterUnderTest;
	
	@Mock
	private SaveView mockView;
	
	@Mock
	private PlayerRepository playerRepoMock;
	
	@Mock
	private Player playerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setView(mockView);
		presenterUnderTest.setPlayRepository(playerRepoMock);
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
	public void testYesInput() throws Exception {
		presenterUnderTest.setInput(Constants.YES_OPTION);

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof SaveConfirmationPresenter);
		verify(playerRepoMock).save(ArgumentMatchers.any(Player.class));
	}
	
	@Test
	public void testNoInput() throws Exception {
		presenterUnderTest.setInput(Constants.NO_OPTION);
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof LocationPresenter);

		verify(playerRepoMock, never()).save(ArgumentMatchers.any(Player.class));
	}
	
}
