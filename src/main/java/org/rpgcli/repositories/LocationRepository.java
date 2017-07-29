package org.rpgcli.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.dataproviders.XCSVDataProvider;
import org.rpgcli.models.Location;

public class LocationRepository extends AbstractRepository<Location> {
	
	public LocationRepository() {
		super(new XCSVDataProvider());
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
		
		// TODO location.setAvailableEnemies();
		return locations;
	}

	private void addClosebys(List<Location> locations) {
		if (locations == null || locations.isEmpty()) {
			return;
		}
		
		List<String[]> locToLoc = getDataProvider().fetchData("locations-locations");
		
		locToLoc.forEach(record -> {
			Location from = locations.get(Integer.valueOf(record[0])-1);
			Location to = locations.get(Integer.valueOf(record[1])-1);
			from.addClosebyLocation(to);
		});
	}

}
