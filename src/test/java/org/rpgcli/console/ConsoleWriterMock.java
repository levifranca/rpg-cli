package org.rpgcli.console;

public class ConsoleWriterMock implements ConsoleWriter {

	private StringBuilder mockStream;
	
	public ConsoleWriterMock() {
		mockStream = new StringBuilder();
	}
	
	public String getMockStream() {
		return mockStream.toString();
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
		
		mockStream.append(sb);
		
		return this;
	}
	
	public ConsoleWriter breakLine() {
		return write("\n");
	}

}
