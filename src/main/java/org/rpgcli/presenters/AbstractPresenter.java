package org.rpgcli.presenters;

import org.rpgcli.views.View;

public abstract class AbstractPresenter implements Presenter {

	private View view;
	private Presenter nextPresenter;

	public AbstractPresenter(View view) {
		this.view = view;
		if (view != null) {
			view.setPresenter(this);
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
	
	@Override
	public Presenter getNextPresenter() {
		return this.nextPresenter;
	}
	
	@Override
	public void setNextPresenter(Presenter nextPresenter) {
		this.nextPresenter = nextPresenter;
	}

	public abstract void start();
	public abstract void setInput(String input);

}
