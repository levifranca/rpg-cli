package org.rpgcli.views;

import org.rpgcli.console.ConsoleBackgroundColor;
import org.rpgcli.console.ConsoleFontColor;

public class PickNameView extends AbstractView {

	@Override
	public void draw() {
		getConsoleWriter().write("Type the name of your character:").breakLine();
		readInput();
	}

	@Override
	public void drawInvalidInputErrorMessage() {
		getConsoleWriter().write("Please, type in a valid name:", ConsoleFontColor.BLACK, ConsoleBackgroundColor.RED).breakLine();
		readInput();
	}

}
