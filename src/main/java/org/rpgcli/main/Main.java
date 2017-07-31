package org.rpgcli.main;

import org.rpgcli.presenters.PresenterManager;

public class Main {
	private Main() {}
	
	public static void main(String[] args) {
		new PresenterManager().run();

		System.exit(0);
	}
}
