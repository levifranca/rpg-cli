package org.rpgcli.config;

import org.rpgcli.utils.StringUtils;

public class GameConfig {

	private static GameConfig gameConfig;
	
	private String theme;
	
	private GameConfig() {
		this.theme = "default";
	}
	
	public static GameConfig getInstance() {
		if (gameConfig == null) {
			gameConfig = new GameConfig();
		}
		return gameConfig;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		if (!StringUtils.isBlank(theme)) {
			this.theme = theme;
		}
	}
	
}
