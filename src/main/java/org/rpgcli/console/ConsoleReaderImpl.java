package org.rpgcli.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.rpgcli.exceptions.GeneralRuntimeException;

public class ConsoleReaderImpl implements ConsoleReader {

	private static ConsoleReaderImpl instance;
	private BufferedReader bufferedReader;
	
	private ConsoleReaderImpl() {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static ConsoleReaderImpl getInstance() {
		if (instance == null) {
			instance = new ConsoleReaderImpl();
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
			// We're not handling it for now
			throw new GeneralRuntimeException(e);
		}
		
		return result;
	}
	
}
