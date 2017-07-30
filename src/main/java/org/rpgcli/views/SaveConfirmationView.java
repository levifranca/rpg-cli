package org.rpgcli.views;

import org.rpgcli.views.AbstractView;
import org.rpgcli.views.View;

public class SaveConfirmationView extends AbstractView {

	@Override
	protected View getHeaderView() {
		return null;
	}

	@Override
	protected void drawView() {
		getConsoleWriter()
		.write("Game saved successfully!").breakLine();
		readInput();
	}

}
