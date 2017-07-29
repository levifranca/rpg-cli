package org.rpgcli.views;

import org.rpgcli.console.ConsoleBackgroundColor;
import org.rpgcli.console.ConsoleFontColor;
import org.rpgcli.console.ConsoleReader;
import org.rpgcli.console.ConsoleReaderImpl;
import org.rpgcli.console.ConsoleWriter;
import org.rpgcli.console.ConsoleWriterImpl;
import org.rpgcli.presenters.Presenter;

public abstract class AbstractView implements View {

	private Presenter<?> presenter;
	private ConsoleWriter consoleWriter = ConsoleWriterImpl.getInstance();
	private ConsoleReader consoleReader = ConsoleReaderImpl.getInstance();
	
	@Override
	public Presenter<?> getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(Presenter<?> presenter) {
		this.presenter = presenter;
	}

	protected ConsoleReader getConsoleReader() {
		return consoleReader;
	}

	protected void setConsoleReader(ConsoleReader consoleReader) {
		this.consoleReader = consoleReader;
	}

	protected void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}

	protected ConsoleWriter getConsoleWriter() {
		return consoleWriter;
	}
	
	protected void writeErrorMessage(String text) {
		getConsoleWriter().write(text, ConsoleFontColor.BLACK, ConsoleBackgroundColor.RED).breakLine();
	}
	
	protected void readInput() {
		getPresenter().setInput(getConsoleReader().readInput());
	}
	
	@Override
	public void draw() {
		if (getHeaderView() != null) {
			getHeaderView().draw();
		}
		drawView();
	}
	
	protected abstract View getHeaderView();
	protected abstract void drawView();
}
