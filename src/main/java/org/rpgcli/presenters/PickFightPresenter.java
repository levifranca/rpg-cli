package org.rpgcli.presenters;

import java.util.List;

import org.rpgcli.models.Enemy;
import org.rpgcli.models.Player;
import org.rpgcli.utils.Constants;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.PickFightView;

public class PickFightPresenter extends AbstractPresenter<PickFightView> {

	private Player player;

	public PickFightPresenter(Player player) {
		super(new PickFightView(player));
		this.player = player;
	}

	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}

		if(Constants.BACK_OPTION.equals(input)) {
			setNextPresenter(new LocationPresenter(player));
			return;
		}
		
		List<Enemy> enemies = player.getCurrentLocation().getAvailableEnemies();
		
		Integer option = StringUtils.getIntValue(input);
		if (option == null || option == 0 || option > enemies.size()) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		setNextPresenter(new FightPresenter(player, new Enemy(enemies.get(option-1))));
		
	}

}
