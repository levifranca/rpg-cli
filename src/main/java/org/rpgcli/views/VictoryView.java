package org.rpgcli.views;

public class VictoryView extends AbstractView {

	private Integer experienceWon;

	public VictoryView(Integer experienceWon) {
		this.experienceWon = experienceWon;
	}

	@Override
	protected View getHeaderView() {
		return null;
	}

	@Override
	protected void drawView() {
		getConsoleWriter()
		.write("Yay! You won " + experienceWon + " Experience points.").breakLine();
		readInput();
	}

}
