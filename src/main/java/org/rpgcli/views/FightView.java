package org.rpgcli.views;

import org.rpgcli.models.Enemy;
import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.views.AbstractView;
import org.rpgcli.views.View;

public class FightView extends AbstractView {

	private PlayerCharacter player;
	private Enemy enemy;

	public FightView(PlayerCharacter player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	@Override
	protected View getHeaderView() {
		return new PlayerStatusHeaderView(player);
	}

	@Override
	protected void drawView() {
		getConsoleWriter().breakLine()
		.write(enemy.getName() + 
				"\tHP: " + enemy.getHealthPoints() + 
				"\tAT: " + enemy.getAttackPower() + 
				"\tDF: " + enemy.getDefencePower())
		.breakLine().breakLine().breakLine()
		.write("A. Attack").breakLine()
		.write("D. Defend").breakLine()
		.write("R. Run Away").breakLine();
		
		readInput();
	}

}
