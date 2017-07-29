package org.rpgcli.repositories;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.dataproviders.DataProvider;
import org.rpgcli.models.CharacterClass;

public class CharacterClassRepositoryTest {

	@InjectMocks
	private CharacterClassRepository repoUnderTest;
	
	@Mock
	private DataProvider dataProviderMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAllEmpty() throws Exception {
		List<String[]> mockData = Collections.emptyList();
		when(dataProviderMock.fetchData("char-class")).thenReturn(mockData);
		
		List<CharacterClass> classes = repoUnderTest.findAll();
		
		assertNotNull(classes);
		assertTrue(classes.isEmpty());
	}
	
	@Test
	public void testFindAllRegularData() throws Exception {
		mockDataProviderData();
		
		List<CharacterClass> classes = repoUnderTest.findAll();
		
		assertNotNull(classes);
		assertEquals(2, classes.size());
		
		CharacterClass charClass = classes.get(0);
		assertEquals(Integer.valueOf(1), charClass.getId());
		assertEquals("Class 1", charClass.getName());
		// TODO assertEquals("Class 1", charClass.getStartLocation());
		
		charClass = classes.get(1);
		assertEquals(Integer.valueOf(2), charClass.getId());
		assertEquals("Class 2", charClass.getName());
		// TODO assertEquals("Class 1", charClass.getStartLocation());
		
	}
	
	@Test
	public void testFindById() throws Exception {
		List<String[]> mockData = new ArrayList<>();
		mockData.add(new String[]{"1", "Class 1", "2"});
		when(dataProviderMock.fetchData("char-class")).thenReturn(mockData);
		
		CharacterClass charClass = repoUnderTest.findById(1);
		
		assertNotNull(charClass);
		assertEquals(Integer.valueOf(1), charClass.getId());
		assertEquals("Class 1", charClass.getName());
		// TODO assertEquals("Class 1", charClass.getStartLocation());
		
	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(dataProviderMock.fetchData("char-class")).thenReturn(Collections.emptyList());
		
		CharacterClass charClass = repoUnderTest.findById(1);
		
		assertNull(charClass);
		
	}
	
	@Test
	public void testFindByIdCachedData() throws Exception {
		mockDataProviderData();
		
		repoUnderTest.findById(1);
		CharacterClass charClass = repoUnderTest.findById(2);
		
		assertNotNull(charClass);
		assertEquals(Integer.valueOf(2), charClass.getId());
		assertEquals("Class 2", charClass.getName());
		// TODO assertEquals("Class 1", charClass.getStartLocation());
		
	}
	
	@Test
	public void testFindAllCachedData() throws Exception {
		mockDataProviderData();
		
		repoUnderTest.findAll();
		List<CharacterClass> classes = repoUnderTest.findAll();
		
		assertNotNull(classes);
		assertEquals(2, classes.size());
		
		CharacterClass charClass = classes.get(0);
		assertEquals(Integer.valueOf(1), charClass.getId());
		assertEquals("Class 1", charClass.getName());
		// TODO assertEquals("Class 1", charClass.getStartLocation());
		
		charClass = classes.get(1);
		assertEquals(Integer.valueOf(2), charClass.getId());
		assertEquals("Class 2", charClass.getName());
		// TODO assertEquals("Class 1", charClass.getStartLocation());
		
	}

	private void mockDataProviderData() {
		List<String[]> mockData = new ArrayList<>();
		mockData.add(new String[]{"1", "Class 1", "2"});
		mockData.add(new String[]{"2", "Class 2", "2"});
		when(dataProviderMock.fetchData("char-class")).thenReturn(mockData);
	}
}
