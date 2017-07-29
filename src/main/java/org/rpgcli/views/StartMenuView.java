package org.rpgcli.views;

import org.rpgcli.console.ConsoleBackgroundColor;
import org.rpgcli.console.ConsoleFontColor;

public class StartMenuView extends AbstractView {

	@Override
	public void draw() {
		getConsoleWriter().write("Welcome to RGP CLI game.").breakLine()
					 .write("What would you like to do:").breakLine()
					 .write("N. New Game.").breakLine()
					 .breakLine()
					 .write("Q. Quit.").breakLine()
					 .write("Enter your option below:").breakLine();
		readInput();
	}
	
	@Override
	public void drawInvalidInputErrorMessage() {
		getConsoleWriter().write("Invalid option! Please enter a valid option.", ConsoleFontColor.BLACK, ConsoleBackgroundColor.RED)
					 .breakLine();
		readInput();
	}
	
}
