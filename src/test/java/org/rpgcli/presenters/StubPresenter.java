package org.rpgcli.presenters;

import org.rpgcli.presenters.Presenter;
import org.rpgcli.views.View;

public class StubPresenter implements Presenter<View> {

	private Presenter<?> nextPresenter;
	
	@Override
	public void start() {
	}

	@Override
	public void setInput(String input) {
	}

	@Override
	public void setView(View view) {
	}

	@Override
	public View getView() {
		return null;
	}

	@Override
	public Presenter<?> getNextPresenter() {
		return nextPresenter;
	}

	@Override
	public void setNextPresenter(Presenter<?> nextPresenter) {
		this.nextPresenter = nextPresenter;
	}

}
