package org.rpgcli.views;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.rpgcli.console.ConsoleReaderMock;
import org.rpgcli.console.ConsoleWriterMock;
import org.rpgcli.presenter.StubPresenter;

public class StartMenuViewTest {

	private StartMenuView viewUnderTest;
	private ConsoleWriterMock consoleWriterMock;
	private ConsoleReaderMock consoleReaderMock;
	
	@Before
	public void setup() {
		viewUnderTest = new StartMenuView();
		viewUnderTest.setPresenter(new StubPresenter());
		
		consoleWriterMock = new ConsoleWriterMock();
		viewUnderTest.setConsoleWriter(consoleWriterMock);
		consoleReaderMock = new ConsoleReaderMock();
		viewUnderTest.setConsoleReader(new ConsoleReaderMock());
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
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		String expected = "\u001B[41m\u001B[30mInvalid option! " 
		+ "Please enter one of the characters at the beginning of the menu option text.\u001B[0m\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
}
