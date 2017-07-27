package org.rpgcli.utils;

import java.io.PrintStream;

public class ConsoleWriter {

	private static ConsoleWriter instance;
	private PrintStream printStream;

	private ConsoleWriter() {
		printStream = System.out;
	}

	// TODO: Does it really needs to be a singleton
	public static ConsoleWriter getInstance() {
		if (instance == null) {
			instance = new ConsoleWriter();
		}
		return instance;
	}

	public void writeColorText(String text, String color) {
		writePlainText(color);
		writePlainText(text);
		writePlainText("\u001B[0m");
	}

	public void writePlainText(String text) {
		if (text == null) {
			return;
		}

		printStream.print(text);
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public PrintStream getPrintStream() {
		return printStream;
	}
}
