package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class StartMenuViewTest extends AbstractViewTest<StartMenuView> {

	@Before
	public void setup() {
		viewUnderTest = new StartMenuView();
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {
		viewUnderTest.draw();
		
		String expected = "Welcome to RGP CLI game.\n"
				+ "What would you like to do:\n"
				+ "N. New Game.\n"
				+ "\n"
				+ "Q. Quit.\n"
				+ "Enter your option below:\n";
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
