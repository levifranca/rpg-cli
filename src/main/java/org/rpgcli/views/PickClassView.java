package org.rpgcli.views;

import java.util.ArrayList;
import java.util.List;

import org.rpgcli.models.CharacterClass;

public class PickClassView extends AbstractView {

	private List<CharacterClass> characterClasses;

	public PickClassView(List<CharacterClass> characterClasses) {
		if (characterClasses == null) {
			characterClasses = new ArrayList<>();
		}
		this.characterClasses = characterClasses;
	}

	@Override
	public void draw() {
		getConsoleWriter().write("Select you character class:").breakLine();
		for (int i = 0; i < characterClasses.size(); i++) {
			getConsoleWriter().write((i + 1) + ". ").write(characterClasses.get(i).getName()).breakLine();
		}

		readInput();
	}

	@Override
	public void drawInvalidInputErrorMessage() {
		writeErrorMessage("Please, pick a valid option.");
		readInput();
	}

}
