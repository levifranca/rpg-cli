package org.rpgcli.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.data.XCSVDataHandler;
import org.rpgcli.models.Enemy;
import org.rpgcli.models.Location;

/**
 * @author levifranca
 *
 */
public class LocationRepository extends AbstractRepository<Location> {
	
	private EnemyRepository enemyRepo;
	
	public LocationRepository() {
		super(new XCSVDataHandler());
		enemyRepo = new EnemyRepository();
	}
	
	@Override
	protected String getModelSourceName() {
		return "locations";
	}

	@Override
	protected List<Location> getModelList(List<String[]> data) {
		List<Location> locations =  data.stream().map(record -> {
													Location location = new Location();
													location.setId(Integer.valueOf(record[0]));
													location.setName(record[1]);
													location.setDescription(record[2]);
													
													return location;
												}).collect(Collectors.toList());
		
		addClosebys(locations);
		addEnemies(locations);
		
		return locations;
	}

	private void addClosebys(List<Location> locations) {
		if (locations == null || locations.isEmpty()) {
			return;
		}
		
		List<String[]> locToLoc = getDataHandler().fetchData("locations-locations");
		
		locToLoc.forEach(record -> {
			Location from = locations.get(Integer.valueOf(record[0])-1);
			Location to = locations.get(Integer.valueOf(record[1])-1);
			from.addClosebyLocation(to);
		});
	}
	
	private void addEnemies(List<Location> locations) {
		if (locations == null || locations.isEmpty()) {
			return;
		}
		
		List<String[]> locToLoc = getDataHandler().fetchData("locations-enemies");
		
		locToLoc.forEach(record -> {
			Location loc = locations.get(Integer.valueOf(record[0])-1);
			Integer enemyId = Integer.valueOf(record[1]);
			Enemy enemy = enemyRepo.findById(enemyId);
			loc.addAvailableEnemies(enemy);
		});
	}

	@Override
	protected String[] getModelArray(Location data) {
		return new String[0];
	}

	public void setEnemyRepo(EnemyRepository enemyRepo) {
		this.enemyRepo = enemyRepo;
	}

}
