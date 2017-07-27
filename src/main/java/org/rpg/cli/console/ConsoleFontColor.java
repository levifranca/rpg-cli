package org.rpg.cli.console;

// https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
public enum ConsoleFontColor {
	BLACK(30),
	RED(31),
	GREEN(32),
	YELLOW(33),
	BLUE(34),
	PURPLE(35),
	CYAN(36),
	WHITE(37);
	
	private int ansiEscapeCode;
	private static final String ESC_ESCAPE = "\u001B[";
	
	public static final String RESET_ESCAPE = ESC_ESCAPE + "0m";
	
	private ConsoleFontColor(int ansiEscapeCode) {
		this.ansiEscapeCode = ansiEscapeCode;
	}
	
	public String getAnsiConsoleEscape() {
		return ESC_ESCAPE + ansiEscapeCode + "m";
	}
}
