package com.github.brunothg.swing.mvp.presenter;

import com.github.brunothg.swing.mvp.annotation.SwingPresenter;
import com.github.brunothg.swing.mvp.annotation.UiScope;
import com.github.brunothg.swing.mvp.components.Presenter;
import com.github.brunothg.swing.mvp.event.PayloadEvent;
import com.github.brunothg.swing.mvp.view.TestView;
import com.google.common.eventbus.Subscribe;

@UiScope
@SwingPresenter
public class TestPresenter extends Presenter<TestView>
{

	public void show()
	{
		getView().setVisible(true);
	}

	public void hide()
	{
		getView().setVisible(false);
	}

	@Subscribe
	protected void handleStringTestEvent(PayloadEvent<String> ev)
	{
		getView().setInfoText("Got event ->\r\n " + ev.getPayload());
	}
}
