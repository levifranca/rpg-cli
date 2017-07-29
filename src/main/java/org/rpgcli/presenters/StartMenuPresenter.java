package org.rpgcli.presenters;

import org.rpgcli.utils.Constants;
import org.rpgcli.views.StartMenuView;

public class StartMenuPresenter extends AbstractPresenter<StartMenuView> {

	public StartMenuPresenter() {
		super(new StartMenuView());
	}
	
	@Override
	public void start() {
		getView().draw();
	}
	
	@Override
	public void setInput(String input) {
		if (Constants.NEW_GAME_OPTION.equals(input)) {
			setNextPresenter(new PickNamePresenter());
			return;
		}
		
		if (Constants.QUIT_OPTION.equals(input)) {
			setNextPresenter(null);
			return;
		}
		
		getView().drawInvalidInputErrorMessage();
		
		
	}

}
