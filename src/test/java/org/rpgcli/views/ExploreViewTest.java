package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Location;
import org.rpgcli.models.Player;

public class ExploreViewTest extends AbstractViewTest<ExploreView> {

	@Mock
	private Player playerMock;
	
	@Mock
	private PlayerStatusHeaderView headerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		viewUnderTest = new ExploreView(playerMock);
		viewUnderTest = spy(viewUnderTest);
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {	
		when(viewUnderTest.getHeaderView()).thenReturn(null);
		
		List<Location> locationsMock = new ArrayList<>();
		Location mockLocation = new Location();
		mockLocation.setName("Place 1");
		locationsMock.add(mockLocation);
		mockLocation = new Location();
		mockLocation.setName("Place 2");
		locationsMock.add(mockLocation);
		Location currLocationMock = mock(Location.class);
		when(currLocationMock.getClosebyLocations()).thenReturn(locationsMock);
		when(playerMock.getCurrentLocation()).thenReturn(currLocationMock);
		
		viewUnderTest.draw();
		
		String expected = "Pick a place to go:\n\n"
				+ "1. Place 1\n"
				+ "2. Place 2\n"
				+ "\n\n"
				+ "B. Back\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		assertEquals("\u001B[41m\u001B[30mInvalid option! Please pick a valid option. Options are case-sensitive.\u001B[0m\n", consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testGetHeaderView() throws Exception {
		assertNotNull(viewUnderTest.getHeaderView());
		assertTrue(viewUnderTest.getHeaderView() instanceof PlayerStatusHeaderView);
	}
}
