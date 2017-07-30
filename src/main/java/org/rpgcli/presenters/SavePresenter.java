package org.rpgcli.presenters;

import org.rpgcli.models.Player;
import org.rpgcli.repositories.PlayerRepository;
import org.rpgcli.utils.Constants;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.SaveView;

public class SavePresenter extends AbstractPresenter<SaveView> {

	private PlayerRepository playerRepository;
	private Player player;
	
	public SavePresenter(Player player) {
		super(new SaveView(player));
		this.player = player;
		playerRepository = new PlayerRepository();
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

		switch (input) {
		case Constants.YES_OPTION:
			playerRepository.save(player);
			setNextPresenter(new SaveConfirmationPresenter(player));
			break;
		case Constants.NO_OPTION:
			setNextPresenter(new LocationPresenter(player));
			break;

		default:
			getView().drawInvalidInputErrorMessage();
			break;
		}
	}

	public void setPlayRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

}
