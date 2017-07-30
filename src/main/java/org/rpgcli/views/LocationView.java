package org.rpgcli.views;

import org.rpgcli.models.Player;

public class LocationView extends AbstractView {

	private Player player;
	
	public LocationView(Player player) {
		this.player = player;
	}

	@Override
	protected View getHeaderView() {
		return new PlayerStatusHeaderView(player);
	}

	@Override
	protected void drawView() {
		getConsoleWriter()
		.write(player.getCurrentLocation().getDescription()).breakLine()
		.write("Pick your next action:").breakLine()
		.write("E. Explore").breakLine()
		.write("F. Fight").breakLine()
		.breakLine()
		.breakLine()
		.write("S. Save").breakLine()
		.write("Q. Quit").breakLine();
		
		readInput();
	}

}
