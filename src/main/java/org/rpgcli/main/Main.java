package org.rpgcli.main;

import org.rpgcli.config.GameConfig;
import org.rpgcli.presenters.PresenterManager;
import org.rpgcli.presenters.StartMenuPresenter;

public class Main {
	private Main() {}
	
	public static void main(String[] args) {
		if (args.length > 0) {
			GameConfig.getInstance().setTheme(args[0]);
		}
		
		new PresenterManager(new StartMenuPresenter()).run();

		System.exit(0);
	}
}
