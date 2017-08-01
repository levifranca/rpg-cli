package org.rpgcli.views;

import java.util.ArrayList;
import java.util.List;

import org.rpgcli.console.ConsoleFontColor;
import org.rpgcli.models.Player;

public class StartMenuView extends AbstractView {

	private static final String[] DEFAULT_ASCII_LOGO = new String[]{
			" /$$$$$$$  /$$$$$$$   /$$$$$$           /$$$$$$  /$$       /$$$$$$ ",
			"| $$__  $$| $$__  $$ /$$__  $$         /$$__  $$| $$      |_  $$_/",
			"| $$  \\ $$| $$  \\ $$| $$  \\__/        | $$  \\__/| $$        | $$  ",
			"| $$$$$$$/| $$$$$$$/| $$ /$$$$ /$$$$$$| $$      | $$        | $$  ",
			"| $$__  $$| $$____/ | $$|_  $$|______/| $$      | $$        | $$  ",
			"| $$  \\ $$| $$      | $$  \\ $$        | $$    $$| $$        | $$  ", 
			"| $$  | $$| $$      |  $$$$$$/        |  $$$$$$/| $$$$$$$$ /$$$$$$",
			"|__/  |__/|__/      \\______/          \\______/ |________/|______/"};
	
	private List<Player> savedPlayers = new ArrayList<>();
	private String[] asciiLogo = DEFAULT_ASCII_LOGO;
	
	@Override
	public void drawView() {
		getConsoleWriter()
		.write("Welcome to:").breakLine();
		for (String line : asciiLogo) {
			getConsoleWriter().write(line, ConsoleFontColor.RED).breakLine();
		}
		getConsoleWriter().breakLine()
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

	public void setSavedPlayers(List<Player> savedPlayers) {
		this.savedPlayers = savedPlayers;
	}
	
	public void setAsciiLogo(String[] asciiLogo) {
		this.asciiLogo = asciiLogo;
	}
	
}
