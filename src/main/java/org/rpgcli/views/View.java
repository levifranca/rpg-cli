package org.rpgcli.views;

import org.rpgcli.presenters.Presenter;

public interface View {
	public void draw();
	public void drawInvalidInputErrorMessage();
	
	public Presenter<?> getPresenter();
	public void setPresenter(Presenter<?> presenter);
}
