package org.rpgcli.main;

import org.rpgcli.utils.ConsoleWriter;

public class Main {
	public static void main(String[] args) {
		ConsoleWriter.getInstance().writeColorText("Hello World!", "\u001B[31m");
	}
}
