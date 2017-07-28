package org.rpgcli.console;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.rpgcli.console.ConsoleBackgroundColor;
import org.rpgcli.console.ConsoleFontColor;
import org.rpgcli.console.ConsoleWriter;

public class ConsoleWriterTest {
	
	private ConsoleWriter instanceUnderTest;
	private ByteArrayOutputStream testOutputStream;
	
	@Before
	public void setup() {
		instanceUnderTest = ConsoleWriter.getInstance();
	
		testOutputStream = new ByteArrayOutputStream();
		instanceUnderTest.setPrintStream(new PrintStream(testOutputStream));
	}
	
	@Test
	public void testSingleInstanceAvailable() throws Exception {
		ConsoleWriter instance2 = ConsoleWriter.getInstance();
		
		assertEquals(instanceUnderTest, instance2);
	}
	
	@Test
	public void testWritePlainTextEmptyNull() throws Exception {
		ConsoleWriter instance = ConsoleWriter.getInstance();
		
		instance.write(null);

		assertTrue(getLastBufferString(), getLastBufferString().isEmpty());
	}
	
	@Test
	public void testWritePlainTextEmptyString() throws Exception {
		ConsoleWriter instance = ConsoleWriter.getInstance();
		
		instance.write("");
		
		assertTrue(getLastBufferString().isEmpty());
	}
	
	@Test
	public void testWritePlainTextRegularString() throws Exception {
		instanceUnderTest.write("Testing...");
		
		assertEquals("Testing...", getLastBufferString());
	}
	
	@Test
	public void testWriteFontColorText() throws Exception {
		instanceUnderTest.write("Test", ConsoleFontColor.RED);
		
		assertEquals("\u001B[31mTest\u001B[0m", getLastBufferString());
	}
	
	@Test
	public void testWriteBackgrounColorText() throws Exception {
		instanceUnderTest.write("Test", null, ConsoleBackgroundColor.CYAN);
		
		assertEquals("\u001B[46mTest\u001B[0m", getLastBufferString());
	}
	
	@Test
	public void testWriteFontAndBackgrounColorText() throws Exception {
		instanceUnderTest.write("Test", ConsoleFontColor.BLUE, ConsoleBackgroundColor.GREEN);
		
		assertEquals("\u001B[42m\u001B[34mTest\u001B[0m", getLastBufferString());
	}
	
	@Test
	public void testWriteReturnsConsoleWriter() throws Exception {
		ConsoleWriter result = instanceUnderTest.write("test");
		assertEquals(instanceUnderTest, result);
	}
	
	@Test
	public void testWriteFontColorReturnsConsoleWriter() throws Exception {
		ConsoleWriter result = instanceUnderTest.write("test", null);
		assertEquals(instanceUnderTest, result);
	}
	
	@Test
	public void testWriteFontBGColorReturnsConsoleWriter() throws Exception {
		ConsoleWriter result = instanceUnderTest.write("test", null, null);
		assertEquals(instanceUnderTest, result);
	}
	
	@Test
	public void testBreakLine() throws Exception {
		instanceUnderTest.breakLine();
		assertEquals("\n", getLastBufferString());
	}
	
	private String getLastBufferString() {
		return testOutputStream.toString();
	}
}
