package org.rpgcli.presenters;

import java.util.List;

import org.rpgcli.models.Location;
import org.rpgcli.models.PlayerCharacter;
import org.rpgcli.repositories.LocationRepository;
import org.rpgcli.utils.Constants;
import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.ExploreView;

public class ExplorePresenter extends AbstractPresenter<ExploreView> {

	private LocationRepository locRepo;
	private PlayerCharacter player;
	
	public ExplorePresenter(PlayerCharacter player) {
		super(new ExploreView(player));
		this.player = player;
		locRepo = new LocationRepository();
	}

	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		if (Constants.BACK_OPTION.equals(input)) {
			setNextPresenter(new LocationPresenter(player));
			return;
		}
		
		Integer option = StringUtils.getIntValue(input);
		if (option == null) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		List<Location> closebyLocations = player.getCurrentLocation().getClosebyLocations();
		if (option == 0 || option > closebyLocations.size()) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		
		Integer locId = closebyLocations.get(option-1).getId();
		player.setCurrentLocation(locRepo.findById(locId));
		setNextPresenter(new LocationPresenter(player));
	}

	public void setLocRepo(LocationRepository locRepo) {
		this.locRepo = locRepo;
	}

}
