package org.rpgcli.views;

public class StartMenuView extends AbstractView {

	@Override
	public void drawView() {
		getConsoleWriter().write("Welcome to RGP CLI game.").breakLine()
					 .write("What would you like to do:").breakLine()
					 .write("N. New Game.").breakLine()
					 .breakLine()
					 .write("Q. Quit.").breakLine()
					 .write("Enter your option below:").breakLine();
		readInput();
	}
	
	@Override
	protected View getHeaderView() {
		return null;
	}
}
