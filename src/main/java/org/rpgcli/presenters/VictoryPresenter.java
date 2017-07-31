package org.rpgcli.presenters;

import org.rpgcli.models.Player;
import org.rpgcli.views.VictoryView;

public class VictoryPresenter extends AbstractPresenter<VictoryView> {

	private Player player;
	private Integer experienceWon;

	public VictoryPresenter(Player player, Integer experienceWon) {
		super(new VictoryView(experienceWon));
		this.player = player;
		this.experienceWon = experienceWon;
		
	}
	
	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		player.gainExperience(experienceWon);
		setNextPresenter(new PickFightPresenter(player));
	}

	public void setExperienceWon(Integer experienceWon) {
		this.experienceWon = experienceWon;
	}

	
}
