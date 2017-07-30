package org.rpgcli.views;

import org.rpgcli.models.PlayerCharacter;

public class SaveView extends AbstractView {

	private PlayerCharacter player;

	public SaveView(PlayerCharacter player) {
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
