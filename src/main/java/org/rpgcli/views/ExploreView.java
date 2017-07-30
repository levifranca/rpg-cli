package org.rpgcli.views;

import org.rpgcli.models.Location;
import org.rpgcli.models.PlayerCharacter;

public class ExploreView extends AbstractView {

	private PlayerCharacter player;

	public ExploreView(PlayerCharacter player) {
		this.player = player;
	}
	
	@Override
	protected View getHeaderView() {
		return new PlayerStatusHeaderView(player);
	}

	@Override
	protected void drawView() {
		getConsoleWriter()
		.write("Pick a place to go:").breakLine().breakLine();
		
		Location curLocation = player.getCurrentLocation();
		for (int i = 0; i < curLocation.getClosebyLocations().size(); i++) {
			getConsoleWriter().write((i+1) + ". " + curLocation.getClosebyLocations().get(i).getName()).breakLine();
		}
		getConsoleWriter().breakLine().breakLine()
		.write("B. Back").breakLine();
		readInput();
	}

}
