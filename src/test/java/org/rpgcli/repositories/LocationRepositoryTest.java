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
import org.rpgcli.dataproviders.DataProvider;
import org.rpgcli.models.Location;

public class LocationRepositoryTest {
	@InjectMocks
	private LocationRepository repoUnderTest;

	@Mock
	private DataProvider dataProviderMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAllEmpty() throws Exception {
		List<String[]> mockData = Collections.emptyList();
		when(dataProviderMock.fetchData("locations")).thenReturn(mockData);

		List<Location> classes = repoUnderTest.findAll();

		assertNotNull(classes);
		assertTrue(classes.isEmpty());
	}
	

	@Test
	public void testFindAllRegularData() throws Exception {
		mockDataProviderData();

		List<Location> locations = repoUnderTest.findAll();

		assertNotNull(locations);
		assertEquals(3, locations.size());

		Location location = locations.get(0);
		assertEquals(Integer.valueOf(1), location.getId());
		assertEquals("Loc name 1", location.getName());
		assertEquals("Loc desc 1", location.getDescription());

		location = locations.get(1);
		assertEquals(Integer.valueOf(2), location.getId());
		assertEquals("Loc name 2", location.getName());
		assertEquals("Loc desc 2", location.getDescription());
		
		location = locations.get(2);
		assertEquals(Integer.valueOf(3), location.getId());
		assertEquals("Loc name 3", location.getName());
		assertEquals("Loc desc 3", location.getDescription());

	}

	@Test
	public void testFindById() throws Exception {
		mockDataProviderData();
		Location location = repoUnderTest.findById(1);

		assertNotNull(location);
		assertEquals(Integer.valueOf(1), location.getId());
		assertEquals("Loc name 1", location.getName());
		assertEquals("Loc desc 1", location.getDescription());
	}

	@Test
	public void testFindByIdNotFound() throws Exception {
		when(dataProviderMock.fetchData("location")).thenReturn(Collections.emptyList());

		Location location = repoUnderTest.findById(1);

		assertNull(location);

	}
	
	private void mockDataProviderData() {
		List<String[]> mockData = new ArrayList<>();
		mockData.add(new String[] { "1", "Loc name 1", "Loc desc 1" });
		mockData.add(new String[] { "2", "Loc name 2", "Loc desc 2" });
		mockData.add(new String[] { "3", "Loc name 3", "Loc desc 3" });
		when(dataProviderMock.fetchData("locations")).thenReturn(mockData);

	}
}
