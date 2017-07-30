package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Location;
import org.rpgcli.models.Player;
import org.rpgcli.repositories.LocationRepository;
import org.rpgcli.views.ExploreView;

public class ExplorePresenterTest {

	@InjectMocks
	private ExplorePresenter presenterUnderTest;
	
	@Mock
	private ExploreView viewMock;
	
	@Mock
	private Player player;
	
	@Mock
	private LocationRepository locRepoMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setView(viewMock);
		presenterUnderTest.setLocRepo(locRepoMock);
	}
	
	@Test
	public void testStart() throws Exception {
		presenterUnderTest.start();
		
		verify(viewMock).draw();
	}
	
	@Test
	public void testInvalidInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("4");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testInvalidEmptyInput() throws Exception {
		presenterUnderTest.setInput("");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testInvalidBlankInput() throws Exception {
		presenterUnderTest.setInput(" ");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testInvalidNumberInput() throws Exception {
		presenterUnderTest.setInput("z");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testZeroInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("0");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testValidInput() throws Exception {
		mockPlayerData();
		
		presenterUnderTest.setInput("2");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof LocationPresenter);
		verify(locRepoMock).findById(ArgumentMatchers.eq(2));
		verify(player).setCurrentLocation(ArgumentMatchers.any());
	}
	
	@Test
	public void testBackInput() throws Exception {
		
		presenterUnderTest.setInput("B");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof LocationPresenter);
		verify(player, never()).setCurrentLocation(ArgumentMatchers.any());
	}

	private void mockPlayerData() {
		List<Location> closebys = new ArrayList<>();
		Location locMock = new Location();
		locMock.setId(1);
		closebys.add(locMock);
		locMock = new Location();
		locMock.setId(2);
		closebys.add(locMock);
		locMock = new Location();
		locMock.setId(3);
		closebys.add(locMock);
		
		Location mockLocation = mock(Location.class);
		when(mockLocation.getClosebyLocations()).thenReturn(closebys);
		when(player.getCurrentLocation()).thenReturn(mockLocation);
		
		when(locRepoMock.findById(2)).thenReturn(new Location());
	}
}
