package org.rpgcli.presenters;

import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.views.SaveConfirmationView;

public class SaveConfirmationPresenter extends AbstractPresenter<SaveConfirmationView> {
	
	private PlayerCharacter player;

	public SaveConfirmationPresenter(PlayerCharacter player) {
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
