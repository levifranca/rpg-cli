package org.rpgcli.views;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.rpgcli.presenter.StubPresenter;

public class StartMenuViewTest {

	private StartMenuView viewUnderTest;
	private ByteArrayOutputStream testOutputStream;
	
	@Before
	public void setup() {
		viewUnderTest = new StartMenuView();
		viewUnderTest.setPresenter(new StubPresenter());
		
		testOutputStream = new ByteArrayOutputStream();
		viewUnderTest.getConsoleWriter().setPrintStream(new PrintStream(testOutputStream));
	}
	
	@Test
	public void testBasicDraw() throws Exception {
		viewUnderTest.draw();
		
		String expected = "Welcome to RGP CLI game.\n"
				+ "What would you like to do:\n"
				+ "1. New Game.\n"
				+ "\n"
				+ "Q. Quit.\n"
				+ "Enter your option below:\n";
		assertEquals(expected, getLastBufferString());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		String expected = "\u001B[41m\u001B[30mInvalid option! " 
		+ "Please enter one of the characters at the beginning of the menu option text.\u001B[0m\n";
		
		assertEquals(expected, getLastBufferString());
	}
	
	private String getLastBufferString() {
		return testOutputStream.toString();
	}
}
