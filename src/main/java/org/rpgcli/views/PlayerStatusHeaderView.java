package org.rpgcli.views;

import org.rpgcli.console.ConsoleFontColor;
import org.rpgcli.models.Player;

public class PlayerStatusHeaderView extends AbstractView {

	private Player player;
	
	public PlayerStatusHeaderView(Player player) {
		this.player = player;
	}
	
	@Override
	public void drawInvalidInputErrorMessage() {
		// do nothing
	}

	@Override
	protected View getHeaderView() {
		return null;
	}

	@Override
	protected void drawView() {
		getConsoleWriter().write("Name: " + player.getName())
						  .write("\t")
						  .write("HP: " + player.getHealthPoints().toString(), ConsoleFontColor.RED)
						  .write("\t")
						  .write("AT: " + player.getAttackPower().toString(), ConsoleFontColor.CYAN)
						  .write("\t")
						  .write("DF: " + player.getDefencePower().toString(), ConsoleFontColor.GREEN)
						  .write("\t")
						  .write("XP: " + player.getExperiencePoints().toString(), ConsoleFontColor.BLUE)
						  .breakLine();
	}

}
