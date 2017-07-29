package org.rpgcli.presenters;

import org.rpgcli.models.Game;
import org.rpgcli.views.PickNameView;

public class PickNamePresenter extends AbstractPresenter {
	
	private Game game;
	
	public PickNamePresenter() {
		super(new PickNameView());
		this.game = new Game();
	}

	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		if (input == null || input.trim().isEmpty()) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		setNextPresenter(new PickClassPresenter());
	}

}
