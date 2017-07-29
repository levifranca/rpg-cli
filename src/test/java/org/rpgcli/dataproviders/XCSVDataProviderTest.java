package org.rpgcli.dataproviders;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class XCSVDataProviderTest {

	private XCSVDataProvider providerUnderTest = new XCSVDataProvider();
	
	@Test
	public void testFetchSingleLine() throws Exception {
		List<String[]> data = providerUnderTest.fetchData("test");
		
		assertNotNull(data);
		assertFalse(data.isEmpty());
		assertEquals(1, data.size());
		String[] actualArray = data.get(0);
		assertEquals(4, actualArray.length);
		assertEquals("here", actualArray[0]);
		assertEquals("goes", actualArray[1]);
		assertEquals("the", actualArray[2]);
		assertEquals("data", actualArray[3]);
	}
	
	@Test
	public void testFetchMultipleLines() throws Exception {
		List<String[]> data = providerUnderTest.fetchData("testMulti");
		
		assertNotNull(data);
		assertFalse(data.isEmpty());
		assertEquals(2, data.size());
		
		String[] actualArray = data.get(0);
		assertEquals(3, actualArray.length);
		assertEquals("first", actualArray[0]);
		assertEquals("line", actualArray[1]);
		assertEquals("here", actualArray[2]);
		
		actualArray = data.get(1);
		assertEquals(2, actualArray.length);
		assertEquals("second", actualArray[0]);
		assertEquals("line", actualArray[1]);
		
	}
	
	@Test
	public void testFileNotFound() throws Exception {
		List<String[]> data = providerUnderTest.fetchData("not-found");
		
		assertNotNull(data);
		assertTrue(data.isEmpty());
	}
	
	@Test
	public void testEmptyFile() throws Exception {
		List<String[]> data = providerUnderTest.fetchData("empty");
		
		assertNotNull(data);
		assertTrue(data.isEmpty());
	}
	
	@Test
	public void testEmptyFilename() throws Exception {
		List<String[]> data = providerUnderTest.fetchData("");
		
		assertNotNull(data);
		assertTrue(data.isEmpty());
	}
}
