package org.rpgcli.presenters;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.rpgcli.presenters.PresenterManager;

public class PresenterManagerTest {

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	private PresenterManager managerUnderTest = new PresenterManager();
	
	@Test
	public void testRunOnNull() throws Exception {
		exit.expectSystemExitWithStatus(0);
		
		managerUnderTest.setCurrPresenter(null);
		managerUnderTest.run();
	}
	
	@Test
	public void testRunNotNull() throws Exception {
		// For the second call to run
		exit.expectSystemExitWithStatus(0);
		
		
		StubPresenter presenter = mock(StubPresenter.class);
		managerUnderTest.setCurrPresenter(presenter);
		managerUnderTest.run();
		
		verify(presenter).start();
	}
}
