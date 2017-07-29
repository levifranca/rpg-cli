package org.rpgcli.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.Location;
import org.rpgcli.models.PlayerCharacter;

public class LocationViewTest extends AbstractViewTest<LocationView> {

	@Mock
	private PlayerCharacter playerMock;
	
	@Mock
	private PlayerStatusHeaderView headerMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		viewUnderTest = new LocationView(playerMock);
		viewUnderTest = spy(viewUnderTest);
		
		super.setMocks();
	}
	
	@Test
	public void testBasicDraw() throws Exception {	
		when(viewUnderTest.getHeaderView()).thenReturn(null);
		
		Location mockLocation = new Location();
		mockLocation.setDescription("The temple is a place for self-reflection.");
		when(playerMock.getCurrentLocation()).thenReturn(mockLocation);
		viewUnderTest.draw();
		
		String expected = "The temple is a place for self-reflection.\n"
				+ "Pick your next action:\n"
				+ "E. Explore\n"
				+ "F. Fight\n"
				+ "\n\n"
				+ "S. Save\n"
				+ "Q. Quit\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		assertEquals("\u001B[41m\u001B[30mInvalid option! Please pick a valid option.\u001B[0m\n", consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testGetHeaderView() throws Exception {
		assertNotNull(viewUnderTest.getHeaderView());
		assertTrue(viewUnderTest.getHeaderView() instanceof PlayerStatusHeaderView);
	}
}
