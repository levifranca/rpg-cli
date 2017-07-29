package org.rpgcli.presenter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.presenters.PickClassPresenter;
import org.rpgcli.presenters.PickNamePresenter;
import org.rpgcli.views.PickNameView;

public class PickNamePresenterTest {

	@InjectMocks
	private PickNamePresenter presenterUnderTest;
	
	@Mock
	private PickNameView view;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testStart() throws Exception {
		presenterUnderTest.start();
		
		verify(view).draw();
	}
	
	@Test
	public void testNullInput() throws Exception {
		presenterUnderTest.setInput(null);
		
		verify(view).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testEmptyInput() throws Exception {
		presenterUnderTest.setInput("");
		
		verify(view).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testBlankInput() throws Exception {
		presenterUnderTest.setInput(" ");
		
		verify(view).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testRegularInput() throws Exception {
		presenterUnderTest.setInput("My Name");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof PickClassPresenter);
	}
}
