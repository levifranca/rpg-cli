package org.rpgcli.presenters;

import org.rpgcli.views.View;

public interface Presenter<T extends View> {
	
	public void start();
	
	public void setInput(String input);
	
	public T getView();
	public void setView(T view);

	public Presenter<?> getNextPresenter();
	public void setNextPresenter(Presenter<?> nextPresenter);
}
