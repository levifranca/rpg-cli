package org.rpgcli.views;

import org.rpgcli.console.ConsoleReaderMock;
import org.rpgcli.console.ConsoleWriterMock;
import org.rpgcli.presenter.StubPresenter;

public class AbstractViewTest<T extends AbstractView> {

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
