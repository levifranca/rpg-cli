package org.rpgcli.repositories;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.data.XCSVDataHandler;
import org.rpgcli.models.Enemy;

public class EnemyRepository extends AbstractRepository<Enemy> {

	public EnemyRepository() {
		super(new XCSVDataHandler());
	}
	
	protected List<Enemy> getModelList(List<String[]> data) {
		if (data == null || data.isEmpty()) {
			return Collections.emptyList();
		}
		
		return data.stream().map(record -> {
				Enemy enemy = new Enemy();
				enemy.setId(Integer.valueOf(record[0]));
				enemy.setName(record[1]);
				enemy.setHealthPoints(Integer.valueOf(record[2]));
				enemy.setAttackPower(Integer.valueOf(record[3]));
				enemy.setDefencePower(Integer.valueOf(record[4]));
				enemy.setExperienceGiven(Integer.valueOf(record[5]));
				return enemy;
			}).collect(Collectors.toList());
	}

	protected String getModelSourceName() {
		return "enemies";
	}

	@Override
	protected String[] getModelArray(Enemy data) {
		return new String[0];
	}
	
}
