package org.rpgcli.presenters;

import org.rpgcli.models.Player;
import org.rpgcli.views.SaveConfirmationView;

public class SaveConfirmationPresenter extends AbstractPresenter<SaveConfirmationView> {
	
	private Player player;

	public SaveConfirmationPresenter(Player player) {
		super(new SaveConfirmationView());
		this.player = player;
	}

	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		setNextPresenter(new LocationPresenter(player));
	}

}
