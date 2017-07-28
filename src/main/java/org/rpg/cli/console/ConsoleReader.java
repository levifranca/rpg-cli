package org.rpg.cli.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

	private static ConsoleReader instance;
	private BufferedReader bufferedReader;
	
	private ConsoleReader() {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static ConsoleReader getInstance() {
		if (instance == null) {
			instance = new ConsoleReader();
		}
		return instance;
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public String readInput() {
		String result = null;
		try {
			result = getBufferedReader().readLine();
		} catch (IOException e) {
			// TODO fix logs
			System.err.println("Could not read input.: " + e);
		}
		
		return result;
	}
	
}
