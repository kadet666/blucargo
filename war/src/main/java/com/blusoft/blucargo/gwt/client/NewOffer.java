package com.blusoft.blucargo.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class NewOffer implements EntryPoint {

	public void onModuleLoad() {

		Label label = new Label("test");
		CheckBox checkBox = new CheckBox();

		RootPanel.get().add(label);
		RootPanel.get().add(checkBox);

	}

}
