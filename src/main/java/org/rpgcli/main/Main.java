package org.rpgcli.main;

import org.rpgcli.presenters.Presenter;
import org.rpgcli.presenters.StartMenuPresenter;
import org.rpgcli.views.StartMenuView;

public class Main {
	public static void main(String[] args) {
		Presenter presenter = new StartMenuPresenter();
		
		StartMenuView view = new StartMenuView();
		view.setPresenter(presenter);
		
		presenter.setView(view);
		
		presenter.start();
	}
}
