package org.rpgcli.presenters;

import java.util.List;

import org.rpgcli.config.GameConfig;
import org.rpgcli.models.Player;
import org.rpgcli.repositories.PlayerRepository;
import org.rpgcli.utils.Constants;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.StartMenuView;

/**
 * @author levifranca
 *
 */
public class StartMenuPresenter extends AbstractPresenter<StartMenuView> {

	private PlayerRepository playerRepo;
	private List<Player> savedGames;
	
	public StartMenuPresenter() {
		super(new StartMenuView());
		playerRepo = new PlayerRepository();

	}
	
	@Override
	public void start() {
		savedGames = playerRepo.findAllByTheme(GameConfig.getInstance().getTheme());
		
		getView().setSavedPlayers(savedGames);
		
		super.start();
	}
	
	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		if (Constants.NEW_GAME_OPTION.equals(input)) {
			setNextPresenter(new PickNamePresenter());
			return;
		}
		
		if (Constants.QUIT_OPTION.equals(input)) {
			setNextPresenter(null);
			return;
		}
		
		Integer option = StringUtils.getIntValue(input);
		if (option == null || option == 0 || option > savedGames.size()) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		
		setNextPresenter(new LocationPresenter(savedGames.get(option-1)));
		
	}

	public void setPlayerRepo(PlayerRepository playerRepo) {
		this.playerRepo = playerRepo;
	}

	public void setSavedGames(List<Player> savedGames) {
		this.savedGames = savedGames;
	}

}
