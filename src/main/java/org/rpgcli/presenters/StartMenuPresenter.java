package org.rpgcli.presenters;

import org.rpgcli.views.View;

public class StartMenuPresenter implements Presenter {

	private View view;
	
	@Override
	public void start() {
		view.draw();
	}
	
	@Override
	public void setInput(String input) {
		// FIXME Change for the actual code
		switch (input) {
		case "1":
			// TODO
			break;
		case "Q":
			System.exit(0);
			break;
		default:
			getView().drawInvalidInputErrorMessage();
			break;
		}
		
	}

	@Override
	public View getView() {
		return view;
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

}
