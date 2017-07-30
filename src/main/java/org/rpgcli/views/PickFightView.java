package org.rpgcli.views;

import java.util.List;

import org.rpgcli.models.Enemy;
import org.rpgcli.models.Player;

public class PickFightView extends AbstractView {

	private Player player;

	public PickFightView(Player player) {
		this.player = player;
	}
	
	@Override
	protected View getHeaderView() {
		return new PlayerStatusHeaderView(player);
	}

	@Override
	protected void drawView() {
		getConsoleWriter()
		.write("Pick an enemy to fight:").breakLine().breakLine();
		
		List<Enemy> enemies = player.getCurrentLocation().getAvailableEnemies();
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			getConsoleWriter().write((i+1) + ". " + enemy.getName() + "\tAT: " + enemy.getAttackPower() + "\tDF: " + enemy.getDefencePower()).breakLine();
		}
		
		getConsoleWriter().breakLine().breakLine()
		.write("B. Back").breakLine();
		
		readInput();
	}

}
