package org.rpgcli.presenters;

import org.rpgcli.utils.StringUtils;
import org.rpgcli.views.PickNameView;

public class PickNamePresenter extends AbstractPresenter<PickNameView> {
		
	public PickNamePresenter() {
		super(new PickNameView());
	}

	@Override
	public void start() {
		getView().draw();
	}

	@Override
	public void setInput(String input) {
		if (StringUtils.isBlank(input)) {
			getView().drawInvalidInputErrorMessage();
			return;
		}
		setNextPresenter(new PickClassPresenter(input));
	}

}
