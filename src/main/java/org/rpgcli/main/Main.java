package org.rpgcli.main;

import org.rpg.cli.console.ConsoleBackgroundColor;
import org.rpg.cli.console.ConsoleFontColor;
import org.rpg.cli.console.ConsoleReader;
import org.rpg.cli.console.ConsoleWriter;

public class Main {
	public static void main(String[] args) {
		String str = ConsoleReader.getInstance().readInput();
		ConsoleWriter.getInstance().write(str, ConsoleFontColor.CYAN, ConsoleBackgroundColor.PURPLE).breakLine();
	}
}
