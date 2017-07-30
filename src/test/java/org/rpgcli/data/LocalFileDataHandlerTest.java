package org.rpgcli.data;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.rpgcli.data.LocalFileDataHandler;

public class LocalFileDataHandlerTest {

	private LocalFileDataHandler handlerUnderTest = new LocalFileDataHandler();
	
	@AfterClass
	public static void teardown() {
		String userDir = System.getProperty("user.dir");
		String[] filenames = new String[]{"testMulti.dat", "new.dat", "old.dat", "empty-save.dat", "empty.dat"};
		for (String filename : filenames) {
			File file = new File(userDir + "/" + filename);
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
	@Test
	public void testFetchMultipleLines() throws Exception {
		String userDir = System.getProperty("user.dir");
		File testFile = new File(userDir + "/testMulti.dat");
		FileWriter fw = new FileWriter(testFile);
		fw.write("\"here\",\"goes\",\"the\",\"local\",\"file\",\"data\"\n");
		fw.write("\"here\",\"a\",\"second\",\"line\"");
		fw.close();
		
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
	
	@Test
	public void testSaveDataNewFile() throws Exception {
		String userDir = System.getProperty("user.dir");
		File testFile = new File(userDir + "/new.dat");
		assertFalse(testFile.exists());

		boolean result = handlerUnderTest.saveData("new", 
				Arrays.asList(new String[] { "1", "data" }, new String[] { "2", "data2" }));

		assertTrue(result);
		assertTrue(testFile.exists());

		BufferedReader br = new BufferedReader(new FileReader(testFile));
		String line = br.readLine();
		assertEquals("\"1\",\"data\"", line);
		line = br.readLine();
		assertEquals("\"2\",\"data2\"", line);
		br.close();
		
		testFile.delete();
	}
	
	@Test
	public void testSaveDataEmptyFile() throws Exception {
		String userDir = System.getProperty("user.dir");
		File testFile = new File(userDir + "/empty-save.dat");
		testFile.createNewFile();

		assertTrue(testFile.exists());
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		String line = br.readLine();
		br.close();
		assertNull(line);
		
		boolean result = handlerUnderTest.saveData("empty-save", 
				Arrays.asList(new String[]{"1", "data"}, new String[]{"2", "data2"}));

		assertTrue(result);
		testFile = new File(userDir + "/empty-save.dat");
		assertTrue(testFile.exists());
		
		br = new BufferedReader(new FileReader(testFile));
		line = br.readLine();
		assertEquals("\"1\",\"data\"", line);
		line = br.readLine();
		assertEquals("\"2\",\"data2\"", line);
		br.close();
		
		testFile.delete();
	}
	
	@Test
	public void testSaveEmptyFilename() throws Exception {
		boolean result = handlerUnderTest.saveData("", Arrays.asList(new String[]{}, new String[]{}));
		
		assertFalse(result);
		assertFalse(new File("/.dat").exists());
	}
	
	@Test
	public void testSaveOverwriteFile() throws Exception {
		String userDir = System.getProperty("user.dir");
		File testFile = new File(userDir + "/old.dat");
		testFile.createNewFile();
		FileWriter fw = new FileWriter(testFile);
		fw.write("\"here\",\"goes\",\"the\",\"local\",\"file\",\"data\"\n");
		fw.write("\"here\",\"a\",\"second\",\"line\"");
		fw.close();
		
		boolean result = handlerUnderTest.saveData("old", 
				Arrays.asList(new String[]{"1", "data"}, new String[]{"2", "data2"}));

		assertTrue(result);
		assertTrue(testFile.exists());
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		String line = br.readLine();
		assertEquals("\"1\",\"data\"", line);
		line = br.readLine();
		assertEquals("\"2\",\"data2\"", line);
		br.close();
		
		testFile.delete();
	}
}
