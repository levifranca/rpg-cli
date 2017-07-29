package org.rpgcli.presenters;

import org.rpgcli.views.PickClassView;

public class PickClassPresenter extends AbstractPresenter {

	public PickClassPresenter() {
		// TODO get list of class
		super(new PickClassView(null));
	}
	
	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub

	}

}
