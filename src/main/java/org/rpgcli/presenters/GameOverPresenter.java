package org.rpgcli.presenters;

import org.rpgcli.views.GameOverView;

public class GameOverPresenter extends AbstractPresenter<GameOverView> {

	public GameOverPresenter() {
		super(new GameOverView());
	}
	
	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		setNextPresenter(new StartMenuPresenter());
	}

}
