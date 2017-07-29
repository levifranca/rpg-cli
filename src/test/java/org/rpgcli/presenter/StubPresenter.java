package org.rpgcli.presenter;

import org.rpgcli.presenters.Presenter;
import org.rpgcli.views.View;

public class StubPresenter implements Presenter {

	private Presenter nextPresenter;
	
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setView(View view) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Presenter getNextPresenter() {
		return nextPresenter;
	}

	@Override
	public void setNextPresenter(Presenter nextPresenter) {
		this.nextPresenter = nextPresenter;
	}

}
