package org.rpgcli.views;

import org.rpgcli.models.Player;

public class SaveView extends AbstractView {

	private Player player;

	public SaveView(Player player) {
		this.player = player;
	}

	@Override
	protected View getHeaderView() {
		return new PlayerStatusHeaderView(player);
	}

	@Override
	protected void drawView() {
		getConsoleWriter()
		.write("Are you sure you want to save this game?").breakLine()
		.write("Y. Yes").breakLine()
		.write("N. No").breakLine();
		readInput();
	}

}
