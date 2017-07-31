package org.rpgcli.presenters;

import java.util.List;

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

		savedGames = playerRepo.findAll();
		getView().setSavedPlayers(savedGames);
	}
	
	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
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
