package org.rpgcli.console;

import java.util.Stack;

public class ConsoleReaderMock implements ConsoleReader {

	private Stack<String> inputStack;
	
	public ConsoleReaderMock() {
		inputStack = new Stack<>();
	}
	
	public void setNextInputs(String... nextInputs) {
		if (nextInputs == null || nextInputs.length == 0) {
			return;
		}
		
		for (String input: nextInputs) {
			inputStack.push(input);
		}
	}
	
	@Override
	public String readInput() {
		if (inputStack.isEmpty()){
			return null;
		}
		
		return inputStack.pop();
	}

}
