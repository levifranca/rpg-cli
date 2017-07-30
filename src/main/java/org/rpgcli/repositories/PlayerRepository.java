package org.rpgcli.repositories;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.data.LocalFileDataHandler;
import org.rpgcli.models.PlayerCharacter;

public class PlayerRepository extends AbstractRepository<PlayerCharacter> {

	private CharacterClassRepository charClassRepo;
	private LocationRepository locationRepo;
	
	public PlayerRepository() {
		super(new LocalFileDataHandler());
		charClassRepo = new CharacterClassRepository();
		locationRepo = new LocationRepository();
	}
	
	@Override
	protected String getModelSourceName() {
		return "players";
	}

	@Override
	protected List<PlayerCharacter> getModelList(List<String[]> data) {
		if (data == null || data.isEmpty()) {
			return Collections.emptyList();
		}
		return data.stream().map(record -> {
			PlayerCharacter player = new PlayerCharacter();
			player.setId(Integer.valueOf(record[0]));
			player.setName(record[1]);
			player.setCharClass(charClassRepo.findById(Integer.valueOf(record[2])));
			player.setCurrentLocation(locationRepo.findById(Integer.valueOf(record[3])));
			player.setHealthPoints(Integer.valueOf(record[4]));
			player.setAttackPower(Integer.valueOf(record[5]));
			player.setDefencePower(Integer.valueOf(record[6]));
			player.setExperiencePoints(Integer.valueOf(record[7]));
			return player;
		}).collect(Collectors.toList());
	}

	@Override
	protected String[] getModelArray(PlayerCharacter data) {
		if (data == null) {
			return new String[0];
		}
		
		String[] array = new String[8];
		array[0] = data.getId().toString();
		array[1] = data.getName();
		array[2] = data.getCharClass().getId().toString();
		array[3] = data.getCurrentLocation().getId().toString();
		array[4] = data.getHealthPoints().toString();
		array[5] = data.getAttackPower().toString();
		array[6] = data.getDefencePower().toString();
		array[7] = data.getExperiencePoints().toString();
		
		return array;
	}

	public void setCharClassRepo(CharacterClassRepository charClassRepo) {
		this.charClassRepo = charClassRepo;
	}

	public void setLocationRepo(LocationRepository locationRepo) {
		this.locationRepo = locationRepo;
	}

}
