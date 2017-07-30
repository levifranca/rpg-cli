package org.rpgcli.presenters;

import org.rpgcli.models.Enemy;
import org.rpgcli.models.PlayerCharacter;

public class FightPresenter extends AbstractPresenter<FightView> {

	private PlayerCharacter player;
	private Enemy enemy;

	public FightPresenter(PlayerCharacter player, Enemy enemy) {
		super(new FightView(player, enemy));
		// TODO Auto-generated constructor stub
		this.player = player;
		this.enemy = enemy;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub
		
	}

}
