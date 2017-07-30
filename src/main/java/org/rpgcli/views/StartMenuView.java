package org.rpgcli.views;

import java.util.ArrayList;
import java.util.List;

import org.rpgcli.models.PlayerCharacter;

public class StartMenuView extends AbstractView {

	private List<PlayerCharacter> savedPlayers = new ArrayList<>();
	
	@Override
	public void drawView() {
		getConsoleWriter().write("Welcome to RPG CLI game.").breakLine()
					 .write("What would you like to do:").breakLine()
					 .write("N. New Game.").breakLine();
		
		for (int i = 0; i < savedPlayers.size(); i++) {
			getConsoleWriter().write((i+1) + ". " + savedPlayers.get(i).getName() + "\t"
					+ "XP: " + savedPlayers.get(i).getExperiencePoints()).breakLine();
		}
		
		getConsoleWriter().breakLine()
					 .write("Q. Quit.").breakLine()
					 .write("Enter your option below:").breakLine();
		readInput();
	}
	
	@Override
	protected View getHeaderView() {
		return null;
	}

	public void setSavedPlayers(List<PlayerCharacter> savedPlayers) {
		this.savedPlayers = savedPlayers;
	}
	
}
