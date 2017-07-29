package org.rpgcli.presenters;

import org.rpgcli.views.StartMenuView;

public class StartMenuPresenter extends AbstractPresenter<StartMenuView> {

	public static final String NEW_GAME_OPTION = "N";
	public static final String QUIT_OPTION = "Q";

	public StartMenuPresenter() {
		super(new StartMenuView());
	}
	
	@Override
	public void start() {
		getView().draw();
	}
	
	@Override
	public void setInput(String input) {
		if (NEW_GAME_OPTION.equals(input)) {
			setNextPresenter(new PickNamePresenter());
			return;
		}
		
		if (QUIT_OPTION.equals(input)) {
			setNextPresenter(null);
			return;
		}
		
		getView().drawInvalidInputErrorMessage();
		
		
	}

}
