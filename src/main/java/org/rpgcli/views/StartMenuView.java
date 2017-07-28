package org.rpgcli.views;

import org.rpgcli.console.ConsoleBackgroundColor;
import org.rpgcli.console.ConsoleFontColor;
import org.rpgcli.console.ConsoleReader;
import org.rpgcli.console.ConsoleReaderImpl;
import org.rpgcli.console.ConsoleWriter;
import org.rpgcli.console.ConsoleWriterImpl;
import org.rpgcli.presenters.Presenter;

public class StartMenuView implements View {

	private ConsoleWriter consoleWriter = ConsoleWriterImpl.getInstance();
	private ConsoleReader consoleReader = ConsoleReaderImpl.getInstance();
	
	private Presenter presenter;
	
	@Override
	public void draw() {
		consoleWriter.write("Welcome to RGP CLI game.").breakLine()
					 .write("What would you like to do:").breakLine()
					 .write("1. New Game.").breakLine()
					 .breakLine()
					 .write("Q. Quit.").breakLine()
					 .write("Enter your option below:").breakLine();
		getPresenter().setInput(consoleReader.readInput());
	}
	
	@Override
	public void drawInvalidInputErrorMessage() {
		consoleWriter.write("Invalid option! Please enter one of the characters at the beginning of the menu option text.", ConsoleFontColor.BLACK, ConsoleBackgroundColor.RED)
					 .breakLine();
	}

	public ConsoleReader getConsoleReader() {
		return consoleReader;
	}

	public void setConsoleReader(ConsoleReader consoleReader) {
		this.consoleReader = consoleReader;
	}

	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}

	public ConsoleWriter getConsoleWriter() {
		return consoleWriter;
	}

	@Override
	public Presenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
}
