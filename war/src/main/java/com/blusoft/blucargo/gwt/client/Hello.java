package com.blusoft.blucargo.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Hello implements EntryPoint {

	public void onModuleLoad() {

		TestServiceAsync testService = (TestServiceAsync) GWT.create(TestService.class);

		AsyncCallback callback = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// do some UI stuff to show failure
			}

			public void onSuccess(Object result) {
				final Label testLabel = new Label(result.toString());

				RootPanel.get("testContainer").add(testLabel);
			}
		};

		testService.test(callback);

		final Label testLabel = new Label("This is a test");

		RootPanel.get("testContainer").add(testLabel);

		final Label l = new Label("GWT says : hello");
		RootPanel.get().add(l);

		Button b = new Button("click me !");
		RootPanel.get().add(b);

	}
}
