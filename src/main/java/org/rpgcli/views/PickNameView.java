package org.rpgcli.views;

public class PickNameView extends AbstractView {

	@Override
	public void draw() {
		getConsoleWriter().write("Type the name of your character:").breakLine();
		readInput();
	}

	@Override
	public void drawInvalidInputErrorMessage() {
		writeErrorMessage("Please, type in a valid name:");
		readInput();
	}

}
