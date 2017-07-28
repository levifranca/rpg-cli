package org.rpgcli.main;

import org.rpgcli.console.ConsoleBackgroundColor;
import org.rpgcli.console.ConsoleFontColor;
import org.rpgcli.console.ConsoleReader;
import org.rpgcli.console.ConsoleWriter;

public class Main {
	public static void main(String[] args) {
		String str = ConsoleReader.getInstance().readInput();
		ConsoleWriter.getInstance().write(str, ConsoleFontColor.CYAN, ConsoleBackgroundColor.PURPLE).breakLine();
	}
}
