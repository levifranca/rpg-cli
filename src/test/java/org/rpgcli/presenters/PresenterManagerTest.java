package org.rpgcli.presenters;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class PresenterManagerTest {

	private PresenterManager managerUnderTest = new PresenterManager();
	
	@Test
	public void testRunOnNull() throws Exception {
		managerUnderTest.setCurrPresenter(null);
		managerUnderTest.run();
	}
	
	@Test
	public void testRunNotNull() throws Exception {
		
		StubPresenter presenter = mock(StubPresenter.class);
		managerUnderTest.setCurrPresenter(presenter);
		managerUnderTest.run();
		
		verify(presenter).start();
	}
}
