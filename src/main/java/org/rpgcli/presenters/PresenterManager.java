package org.rpgcli.presenters;

public class PresenterManager {

	private Presenter<?> currPresenter;

	public PresenterManager(Presenter<?> startPresenter) {
		currPresenter = startPresenter;
	}

	public void run() {
		while (currPresenter != null) {
			currPresenter.start();
			currPresenter = currPresenter.getNextPresenter();
		}
	}

	public Presenter<?> getCurrPresenter() {
		return currPresenter;
	}

	public void setCurrPresenter(Presenter<?> currPresenter) {
		this.currPresenter = currPresenter;
	}

}
