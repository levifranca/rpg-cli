package org.rpgcli.presenters;

import org.rpgcli.models.Player;
import org.rpgcli.utils.Constants;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.LocationView;

public class LocationPresenter extends AbstractPresenter<LocationView> {

	private Player player;

	public LocationPresenter(Player player) {
		super(new LocationView(player));
		this.player = player;
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
		case Constants.EXPLORE_OPTION:
			setNextPresenter(new ExplorePresenter(player));
			break;
		case Constants.FIGHT_OPTION:
			setNextPresenter(new PickFightPresenter(player));
			break;
		case Constants.SAVE_OPTION:
			setNextPresenter(new SavePresenter(player));
			break;
		case Constants.QUIT_OPTION:
			setNextPresenter(null);
			break;
		default:
			getView().drawInvalidInputErrorMessage();
			break;
		}
	}

}
