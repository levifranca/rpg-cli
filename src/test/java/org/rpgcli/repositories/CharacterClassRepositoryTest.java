package org.rpgcli.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.data.DataHandler;
import org.rpgcli.models.CharacterClass;
import org.rpgcli.models.Location;

public class CharacterClassRepositoryTest {

	@InjectMocks
	private CharacterClassRepository repoUnderTest;

	@Mock
	private DataHandler dataProviderMock;

	@Mock
	private LocationRepository locRepoMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAllEmpty() throws Exception {
		List<String[]> mockData = Collections.emptyList();
		when(dataProviderMock.fetchData("char-classes")).thenReturn(mockData);

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
		assertEquals(Integer.valueOf(2), charClass.getStartLocation().getId());
		assertEquals("Loc2 name", charClass.getStartLocation().getName());
		assertEquals("Loc2 desc", charClass.getStartLocation().getDescription());

		charClass = classes.get(1);
		assertEquals(Integer.valueOf(2), charClass.getId());
		assertEquals("Class 2", charClass.getName());
		assertEquals(Integer.valueOf(2), charClass.getStartLocation().getId());
		assertEquals("Loc2 name", charClass.getStartLocation().getName());
		assertEquals("Loc2 desc", charClass.getStartLocation().getDescription());

	}

	@Test
	public void testFindById() throws Exception {
		mockDataProviderData();
		CharacterClass charClass = repoUnderTest.findById(1);

		assertNotNull(charClass);
		assertEquals(Integer.valueOf(1), charClass.getId());
		assertEquals("Class 1", charClass.getName());
		assertEquals(Integer.valueOf(2), charClass.getStartLocation().getId());
		assertEquals("Loc2 name", charClass.getStartLocation().getName());
		assertEquals("Loc2 desc", charClass.getStartLocation().getDescription());
	}

	@Test
	public void testFindByIdNotFound() throws Exception {
		when(dataProviderMock.fetchData("char-classes")).thenReturn(Collections.emptyList());

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
		assertEquals(Integer.valueOf(2), charClass.getStartLocation().getId());
		assertEquals("Loc2 name", charClass.getStartLocation().getName());
		assertEquals("Loc2 desc", charClass.getStartLocation().getDescription());

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
		assertEquals(Integer.valueOf(2), charClass.getStartLocation().getId());
		assertEquals("Loc2 name", charClass.getStartLocation().getName());
		assertEquals("Loc2 desc", charClass.getStartLocation().getDescription());

		charClass = classes.get(1);
		assertEquals(Integer.valueOf(2), charClass.getId());
		assertEquals("Class 2", charClass.getName());
		assertEquals(Integer.valueOf(2), charClass.getStartLocation().getId());
		assertEquals("Loc2 name", charClass.getStartLocation().getName());
		assertEquals("Loc2 desc", charClass.getStartLocation().getDescription());

	}

	@Test
	public void testGetModelArray() throws Exception {
		assertEquals(0, repoUnderTest.getModelArray(null).length);
		assertEquals(0, repoUnderTest.getModelArray(new CharacterClass()).length);
	}
	
	private void mockDataProviderData() {
		List<String[]> mockData = new ArrayList<>();
		mockData.add(new String[] { "1", "Class 1", "2" });
		mockData.add(new String[] { "2", "Class 2", "2" });
		when(dataProviderMock.fetchData("char-classes")).thenReturn(mockData);

		Location locationMock = new Location();
		locationMock.setId(2);
		locationMock.setName("Loc2 name");
		locationMock.setDescription("Loc2 desc");
		when(locRepoMock.findById(2)).thenReturn(locationMock);
	}
}
