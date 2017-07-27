package org.rpgcli.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

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
		
		instance.writePlainText(null);

		assertTrue(getLastBufferString(), getLastBufferString().isEmpty());
	}
	
	@Test
	public void testWritePlainTextEmptyString() throws Exception {
		ConsoleWriter instance = ConsoleWriter.getInstance();
		
		instance.writePlainText("");
		
		assertTrue(getLastBufferString().isEmpty());
	}
	
	@Test
	public void testWritePlainTextRegularString() throws Exception {
		
		
		instanceUnderTest.writePlainText("Testing...");
		
		assertEquals("Testing...", getLastBufferString());
	}
	
	private String getLastBufferString() {
		return testOutputStream.toString();
	}
}
