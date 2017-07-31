package org.rpgcli.presenters;

import org.rpgcli.views.GameOverView;

public class GameOverPresenter extends AbstractPresenter<GameOverView> {

	private StartMenuPresenter startMenuPresenter;
	
	public GameOverPresenter() {
		super(new GameOverView());
	}
	
	@Override
	public void setInput(String input) {
		setNextPresenter(getStartMenuPresenter());
	}

	public StartMenuPresenter getStartMenuPresenter() {
		if (startMenuPresenter == null) {
			startMenuPresenter = new StartMenuPresenter();
		}
		return startMenuPresenter;
	}

	public void setStartMenuPresenter(StartMenuPresenter startMenuPresenter) {
		this.startMenuPresenter = startMenuPresenter;
	}

}
