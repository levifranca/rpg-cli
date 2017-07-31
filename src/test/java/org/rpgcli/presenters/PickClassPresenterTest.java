package org.rpgcli.presenters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpgcli.models.CharacterClass;
import org.rpgcli.repositories.CharacterClassRepository;
import org.rpgcli.views.PickClassView;

public class PickClassPresenterTest {

	@InjectMocks
	private PickClassPresenter presenterUnderTest;
	
	@Mock
	private PickClassView viewMock;
	
	@Mock
	private CharacterClassRepository repoMock;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		presenterUnderTest.setView(viewMock);
		presenterUnderTest.setCharacterClassRepository(repoMock);
	}
	
	@Test
	public void testStart() throws Exception {
		List<CharacterClass> mockList = new ArrayList<>();
		when(repoMock.findAll()).thenReturn(mockList);
		
		presenterUnderTest.start();
		
		verify(viewMock).draw();
	}
	
	@Test
	public void testInvalidInput() throws Exception {
		List<CharacterClass> mockList = new ArrayList<>();
		CharacterClass charClass = new CharacterClass();
		charClass.setId(1);
		mockList.add(charClass);
		when(repoMock.findAll()).thenReturn(mockList);
		
		presenterUnderTest.setInput("2");
		
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
		List<CharacterClass> mockList = new ArrayList<>();
		CharacterClass charClass = new CharacterClass();
		charClass.setId(1);
		mockList.add(charClass);
		when(repoMock.findAll()).thenReturn(mockList);
		
		presenterUnderTest.setInput("0");
		
		verify(viewMock).drawInvalidInputErrorMessage();
	}
	
	@Test
	public void testValidInput() throws Exception {
		List<CharacterClass> mockList = new ArrayList<>();
		CharacterClass charClass = new CharacterClass();
		charClass.setId(1);
		mockList.add(charClass);
		charClass = new CharacterClass();
		charClass.setId(2);
		mockList.add(charClass);
		when(repoMock.findAll()).thenReturn(mockList);
		
		presenterUnderTest.setInput("2");
		
		assertNotNull(presenterUnderTest.getNextPresenter());
		assertTrue(presenterUnderTest.getNextPresenter() instanceof LocationPresenter);
	}
}
