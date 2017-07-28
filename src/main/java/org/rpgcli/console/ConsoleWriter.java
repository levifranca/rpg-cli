package org.rpgcli.console;

public interface ConsoleWriter {
	
	public ConsoleWriter write(String text);
	public ConsoleWriter write(String text, ConsoleFontColor fontColor);
	public ConsoleWriter write(String text, ConsoleFontColor fontColor, ConsoleBackgroundColor bgColor);
	public ConsoleWriter breakLine();
	
}
