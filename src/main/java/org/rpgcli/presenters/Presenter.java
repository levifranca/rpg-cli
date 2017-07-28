package org.rpgcli.presenters;

import org.rpgcli.views.View;

public interface Presenter {
	public void start();
	
	public void setInput(String input);
	
	public void setView(View view);
	public View getView();
}
