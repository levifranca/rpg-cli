package org.rpgcli.console;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rpg.cli.console.ConsoleReader;

public class ConsoleReaderTest {

	private ConsoleReader instanceUnderTest;
	private File unitTestFile;
	
	@Before
	public void setup() throws IOException {
		instanceUnderTest = ConsoleReader.getInstance();
		
		unitTestFile = Files.createTempFile("console_test", null).toFile();
		InputStream inputStream = new FileInputStream(unitTestFile);
		instanceUnderTest.setBufferedReader(new BufferedReader(new InputStreamReader(inputStream)));
	}
	
	@After
	public void teardown() throws IOException {
		Files.deleteIfExists(unitTestFile.toPath());
	}
	
	@Test
	public void testReturnsSameIntance() throws Exception {
		ConsoleReader instance2 = ConsoleReader.getInstance();
		
		assertEquals(instanceUnderTest, instance2);
	}
	
	@Test
	public void testReadInput() throws Exception {
		writeToUnittestFile("testReadInput");
		
		String result = instanceUnderTest.readInput();
		
		assertEquals("testReadInput", result);
	}
	
	private void writeToUnittestFile(String input) throws IOException {
		try (FileWriter fw = new FileWriter(unitTestFile)) {
			fw.write(input);
		}
	}
}
