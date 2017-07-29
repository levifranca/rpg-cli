package org.rpgcli.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.rpgcli.dataproviders.XCSVDataProvider;
import org.rpgcli.models.CharacterClass;

public class CharacterClassRepository extends AbstractRepository<CharacterClass> {

	public CharacterClassRepository() {
		setDataProvider(new XCSVDataProvider());
	}
	
	protected List<CharacterClass> getModelList(List<String[]> data) {
		return data.stream()
				   .map(record -> {
					   CharacterClass charClass = new CharacterClass();
					   charClass.setId(Integer.valueOf(record[0]));
					   charClass.setName(record[1]);
					   return charClass;
				   }).collect(Collectors.toList());
	}

	protected String getModelSourceName() {
		return "char-class";
	}
	
}