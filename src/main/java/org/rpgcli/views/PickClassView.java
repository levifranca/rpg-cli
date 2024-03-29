package org.rpgcli.views;

import java.util.ArrayList;
import java.util.List;

import org.rpgcli.models.CharacterClass;

public class PickClassView extends AbstractView {

	private List<CharacterClass> characterClasses = new ArrayList<>();

	@Override
	public void drawView() {
		getConsoleWriter().write("Select you character class:").breakLine();
		for (int i = 0; i < characterClasses.size(); i++) {
			getConsoleWriter().write((i + 1) + ". ").write(characterClasses.get(i).getName()).breakLine();
		}

		readInput();
	}

	public void setCharacterClasses(List<CharacterClass> characterClasses) {
		this.characterClasses = characterClasses;
	}

	@Override
	protected View getHeaderView() {
		return null;
	}
}
