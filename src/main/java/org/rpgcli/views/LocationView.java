package org.rpgcli.views;

import org.rpgcli.models.CharacterClass;
import org.rpgcli.models.PlayerCharacter;

public class LocationView extends AbstractView {

	@Override
	public void drawInvalidInputErrorMessage() {
		// TODO Auto-generated method stub

	}

	@Override
	protected View getHeaderView() {
		return new PlayerStatusHeaderView(new PlayerCharacter("Levi", new CharacterClass()));
	}

	@Override
	protected void drawView() {
		// TODO Auto-generated method stub
		
	}

}
