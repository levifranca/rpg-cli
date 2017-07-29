package org.rpgcli.presenter;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.presenters.PickClassPresenter;
import org.rpgcli.views.PickClassView;

public class PickClassPresenterTest {

	@InjectMocks
	private PickClassPresenter presenterUnderTest;
	
	@Mock
	private PickClassView view;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testStart() throws Exception {
		presenterUnderTest.start();
		
		verify(view).draw();
	}
	
}
