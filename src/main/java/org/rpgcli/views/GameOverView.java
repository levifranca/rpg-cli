package org.rpgcli.views;

import org.rpgcli.console.ConsoleFontColor;

public class GameOverView extends AbstractView {

	@Override
	protected View getHeaderView() {
		return null;
	}

	@Override
	protected void drawView() {
		getConsoleWriter().write("GAME OVER!", ConsoleFontColor.RED).breakLine()
		.write("Please, try again!").breakLine();
		readInput();
	}

}
