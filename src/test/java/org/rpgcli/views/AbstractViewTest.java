package org.rpgcli.views;

import org.rpgcli.console.ConsoleReaderMock;
import org.rpgcli.console.ConsoleWriterMock;
import org.rpgcli.presenters.StubPresenter;

public abstract class AbstractViewTest<T extends AbstractView> {

	protected T viewUnderTest;
	
	protected ConsoleWriterMock consoleWriterMock;
	protected ConsoleReaderMock consoleReaderMock;
	
	protected void setMocks() {
		viewUnderTest.setPresenter(new StubPresenter());
		consoleWriterMock = new ConsoleWriterMock();
		viewUnderTest.setConsoleWriter(consoleWriterMock);
		consoleReaderMock = new ConsoleReaderMock();
		viewUnderTest.setConsoleReader(consoleReaderMock);
	}
}
