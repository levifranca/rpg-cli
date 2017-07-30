package org.rpgcli.presenters;

import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.views.VictoryView;

public class VictoryPresenter extends AbstractPresenter<VictoryView> {

	private PlayerCharacter player;

	public VictoryPresenter(PlayerCharacter player, Integer experienceWon) {
		super(new VictoryView(experienceWon));
		this.player = player;
		
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub
		
	}

}
