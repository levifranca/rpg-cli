package org.rpgcli.presenters;

import java.util.List;

import org.rpgcli.models.CharacterClass;
import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.repositories.CharacterClassRepository;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.PickClassView;

public class PickClassPresenter extends AbstractPresenter<PickClassView> {

	private CharacterClassRepository charClassRepo;
	private String playerName;
	
	public PickClassPresenter(String playerName) {
		super(new PickClassView());
		charClassRepo = new CharacterClassRepository();
		this.playerName = playerName;
	}
	
	@Override
	public void start() {
		List<CharacterClass> classes = charClassRepo.findAll();
		
		getView().setCharacterClasses(classes);
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		Integer option = StringUtils.getIntValue(input);
		if (option == null) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		List<CharacterClass> classes = charClassRepo.findAll();
		if (option == 0 || option > classes.size()) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		PlayerCharacter player = PlayerCharacter.newPlayer(playerName, classes.get(option-1));
		LocationPresenter nextPresenter = new LocationPresenter(player);
		setNextPresenter(nextPresenter);
	}

	public void setCharacterClassRepository(CharacterClassRepository charClassRepo) {
		this.charClassRepo = charClassRepo;
	}
	
}
