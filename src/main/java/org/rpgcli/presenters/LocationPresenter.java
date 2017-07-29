package org.rpgcli.presenters;

import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.views.LocationView;

public class LocationPresenter extends AbstractPresenter<LocationView> {

	private PlayerCharacter player;
	
	public LocationPresenter(PlayerCharacter player) {
		super(new LocationView());
		this.player = player;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub
		
	}

}
