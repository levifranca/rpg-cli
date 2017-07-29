package org.rpgcli.views;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PickNameViewTest extends AbstractViewTest {

	@Before
	public void setup() {
		viewUnderTest = new PickNameView();
		
		super.setMocks();
	}
	
	@Test
	public void testDraw() throws Exception {
		viewUnderTest.draw();
		
		String expected = "Type the name of your character:\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		String expected = "\u001B[41m\u001B[30m"
				+ "Please, type in a valid name:"
				+ "\u001B[0m\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
}
