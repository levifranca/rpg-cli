package org.rpgcli.views;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.rpgcli.models.CharacterClass;

public class PickClassViewTest extends AbstractViewTest {
	@Before
	public void setup() {
		List<CharacterClass> mockList = new ArrayList<>();
		CharacterClass cc = new CharacterClass();
		cc.setName("Knight");
		mockList.add(cc);
		cc = new CharacterClass();
		cc.setName("Dwarf");
		mockList.add(cc);
		cc = new CharacterClass();
		cc.setName("Paladin");
		mockList.add(cc);
		viewUnderTest = new PickClassView(mockList);
		
		super.setMocks();
	}
	
	@Test
	public void testDrawNullList() throws Exception {
		viewUnderTest = new PickClassView(null);
		super.setMocks();
		viewUnderTest.draw();
		
		String expected = "Select you character class:\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDraw() throws Exception {
		viewUnderTest.draw();
		
		String expected = "Select you character class:\n"
				+ "1. Knight\n"
				+ "2. Dwarf\n"
				+ "3. Paladin\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
	
	@Test
	public void testDrawInvalidInputErrorMessage() throws Exception {
		viewUnderTest.drawInvalidInputErrorMessage();
		
		String expected = "\u001B[41m\u001B[30m"
				+ "Please, pick a valid option."
				+ "\u001B[0m\n";
		
		assertEquals(expected, consoleWriterMock.getMockStream());
	}
}
