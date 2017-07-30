package org.rpgcli.presenters;

import org.rpgcli.models.Enemy;
import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.utils.Constants;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.FightView;

public class FightPresenter extends AbstractPresenter<FightView> {

	private PlayerCharacter player;
	private Enemy enemy;

	public FightPresenter(PlayerCharacter player, Enemy enemy) {
		super(new FightView(player, enemy));
		this.player = player;
		this.enemy = enemy;
	}

	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}

		if (!Constants.ATTACK_OPTION.equals(input) 
		 && !Constants.DEFEND_OPTION.equals(input) 
		 && !Constants.RUN_AWAY_OPTION.equals(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		if (Constants.RUN_AWAY_OPTION.equals(input)) {
			setNextPresenter(new PickFightPresenter(player));
			return;
		}
		
		
		if (Constants.ATTACK_OPTION.equals(input)) {
			player.attack(enemy);
			if (enemy.isDead()) {
				setNextPresenter(new VictoryPresenter(player, enemy.getExperienceGiven()));
				return;
			}
		}
		
		enemy.attack(player);
		if (player.isDead()) {
			setNextPresenter(new GameOverPresenter());
			return;
		}
		
		setNextPresenter(this);
	}

	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

}
