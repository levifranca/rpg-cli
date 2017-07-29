package org.rpgcli.dataproviders;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.junit.Test;

public class LocalFileDataHandlerTest {

	private LocalFileDataHandler handlerUnderTest = new LocalFileDataHandler();
	
	@Test
	public void testFetchSingleLine() throws Exception {
		String userDir = System.getProperty("user.dir");
		File userDirFile = new File(userDir + "/test.dat");
		try (FileWriter fw = new FileWriter(userDirFile)) {
			fw.write("\"here\",\"goes\",\"the\",\"local\",\"file\",\"data\"");
		}
		
		List<String[]> data = handlerUnderTest.fetchData("test");
		
		assertNotNull(data);
		assertFalse(data.isEmpty());
		assertEquals(1, data.size());
		String[] actualArray = data.get(0);
		assertEquals(6, actualArray.length);
		assertEquals("here", actualArray[0]);
		assertEquals("goes", actualArray[1]);
		assertEquals("the", actualArray[2]);
		assertEquals("local", actualArray[3]);
		assertEquals("file", actualArray[4]);
		assertEquals("data", actualArray[5]);
		
		userDirFile.delete();
	}
	
	@Test
	public void testFetchMultipleLines() throws Exception {
		String userDir = System.getProperty("user.dir");
		File testFile = new File(userDir + "/testMulti.dat");
		try (FileWriter fw = new FileWriter(testFile)) {
			fw.write("\"here\",\"goes\",\"the\",\"local\",\"file\",\"data\"\n");
			fw.write("\"here\",\"a\",\"second\",\"line\"");
		}
		
		List<String[]> data = handlerUnderTest.fetchData("testMulti");
		
		assertNotNull(data);
		assertFalse(data.isEmpty());
		assertEquals(2, data.size());
		String[] actualArray = data.get(0);
		assertEquals(6, actualArray.length);
		assertEquals("here", actualArray[0]);
		assertEquals("goes", actualArray[1]);
		assertEquals("the", actualArray[2]);
		assertEquals("local", actualArray[3]);
		assertEquals("file", actualArray[4]);
		assertEquals("data", actualArray[5]);
		
		actualArray = data.get(1);
		assertEquals(4, actualArray.length);
		assertEquals("here", actualArray[0]);
		assertEquals("a", actualArray[1]);
		assertEquals("second", actualArray[2]);
		assertEquals("line", actualArray[3]);
		
		testFile.delete();
	}
	
	@Test
	public void testFileNotFound() throws Exception {
		List<String[]> data = handlerUnderTest.fetchData("testMulti");
		
		assertNotNull(data);
		assertTrue(data.isEmpty());
	}
	
	@Test
	public void testEmptyFile() throws Exception {
		String userDir = System.getProperty("user.dir");
		File testFile = new File(userDir + "/empty.dat");
		testFile.createNewFile();
		
		List<String[]> data = handlerUnderTest.fetchData("empty");

		assertNotNull(data);
		assertTrue(data.isEmpty());
		
		testFile.delete();
	}
	
	@Test
	public void testEmptyFilename() throws Exception {
		List<String[]> data = handlerUnderTest.fetchData("");

		assertNotNull(data);
		assertTrue(data.isEmpty());
	}
}
