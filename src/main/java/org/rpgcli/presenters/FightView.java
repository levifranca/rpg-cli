package org.rpgcli.presenters;

import org.rpgcli.models.Enemy;
import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.views.AbstractView;
import org.rpgcli.views.View;

public class FightView extends AbstractView {

	private PlayerCharacter player;
	private Enemy enemy;

	public FightView(PlayerCharacter player, Enemy enemy) {
		this.player = player;
		// TODO Auto-generated constructor stub
		this.enemy = enemy;
	}

	@Override
	protected View getHeaderView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void drawView() {
		// TODO Auto-generated method stub

	}

}
