package org.rpgcli.presenters;

import org.rpgcli.views.View;

public abstract class AbstractPresenter<T extends View> implements Presenter<T> {

	private T view;
	private Presenter<?> nextPresenter;

	public AbstractPresenter(T view) {
		this.view = view;
		if (view != null) {
			view.setPresenter(this);
		}
	}
	
	@Override
	public T getView() {
		return view;
	}

	@Override
	public void setView(T view) {
		this.view = view;
	}
	
	@Override
	public Presenter<?> getNextPresenter() {
		return this.nextPresenter;
	}
	
	@Override
	public void setNextPresenter(Presenter<?> nextPresenter) {
		this.nextPresenter = nextPresenter;
	}

}
