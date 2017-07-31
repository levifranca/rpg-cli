package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameOverViewTest extends AbstractViewTest<GameOverView> {

	@Mock
	private PlayerStatusHeaderView headerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		viewUnderTest = new GameOverView();
		viewUnderTest = spy(viewUnderTest);
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {	
		when(viewUnderTest.getHeaderView()).thenReturn(null);

		viewUnderTest.draw();
		
		String expected = "\u001B[31mGAME OVER!\u001B[0m\n"
				+ "Please, try again!\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		assertEquals("\u001B[41m\u001B[30m"
				+ "Invalid option! Please pick a valid option."
				+ "\u001B[0m\n", consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testGetHeaderView() throws Exception {
		assertNull(viewUnderTest.getHeaderView());
	}
}
