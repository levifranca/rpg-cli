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
import org.rpgcli.utils.Constants;
import org.rpgcli.views.LocationView;

public class LocationPresenterTest {
	
	@InjectMocks
	private LocationPresenter presenterUnderTest;
	
	@Mock
	private LocationView mockView;
	
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
	public void testExploreInput() throws Exception {
		presenterUnderTest.setInput(Constants.EXPLORE_OPTION);

		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof ExplorePresenter);
	}
	
	@Test
	public void testFightInput() throws Exception {
		presenterUnderTest.setInput(Constants.FIGHT_OPTION);
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickFightPresenter);
	}
	
	@Test
	public void testSaveInput() throws Exception {
		presenterUnderTest.setInput(Constants.SAVE_OPTION);
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof SavePresenter);
	}
	
	@Test
	public void testQuitInput() throws Exception {
		presenterUnderTest.setInput(Constants.QUIT_OPTION);
		
		assertNull(presenterUnderTest.getNextPresenter());
	}
}
