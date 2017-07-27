package org.rpg.cli.console;

// https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
public enum ConsoleBackgroundColor {
	BLACK(40),
	RED(41),
	GREEN(42),
	YELLOW(43),
	BLUE(44),
	PURPLE(45),
	CYAN(46),
	WHITE(47);
	
	private int ansiEscapeCode;
	private static final String ESC_ESCAPE = "\u001B[";
	
	public static final String RESET_ESCAPE = ESC_ESCAPE + "0m";
	
	private ConsoleBackgroundColor(int ansiEscapeCode) {
		this.ansiEscapeCode = ansiEscapeCode;
	}
	
	public String getAnsiConsoleEscape() {
		return ESC_ESCAPE + ansiEscapeCode + "m";
	}
}
