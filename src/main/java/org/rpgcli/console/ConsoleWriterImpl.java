package org.rpgcli.console;

import java.io.PrintStream;

public class ConsoleWriterImpl implements ConsoleWriter {

	private static ConsoleWriterImpl instance;
	private PrintStream printStream;

	private ConsoleWriterImpl() {
		printStream = System.out;
	}

	public static ConsoleWriterImpl getInstance() {
		if (instance == null) {
			instance = new ConsoleWriterImpl();
		}
		return instance;
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	public PrintStream getPrintStream() {
		return printStream;
	}

	public ConsoleWriter write(String text) {
		return write(text, null);
	}

	public ConsoleWriter write(String text, ConsoleFontColor fontColor) {
		return write(text, fontColor, null);
	}
	
	public ConsoleWriter write(String text, ConsoleFontColor fontColor, ConsoleBackgroundColor bgColor) {
		if (text == null || text.isEmpty()) {
			return this;
		}
		
		boolean needReset = false;
		StringBuilder sb = new StringBuilder();
		
		if (bgColor!= null) {
			sb.append(bgColor.getAnsiConsoleEscape());
			needReset = true;
		}
		
		if (fontColor != null) {
			sb.append(fontColor.getAnsiConsoleEscape());
			needReset = true;
		}
		
		sb.append(text);
		if (needReset) {
			sb.append(ConsoleFontColor.RESET_ESCAPE);
		}
		getPrintStream().print(sb);
		
		return this;
	}
	
	public ConsoleWriter breakLine() {
		return write("\n");
	}
}
