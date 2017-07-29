package org.rpgcli.views;

import org.rpgcli.models.PlayerCharacter;

public class LocationView extends AbstractView {

	private PlayerCharacter player;
	
	public LocationView(PlayerCharacter player) {
		this.player = player;
	}
	
	@Override
	public void drawInvalidInputErrorMessage() {
		writeErrorMessage("Invalid option! Please pick a valid option.");
		readInput();
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
