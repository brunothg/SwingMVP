package com.github.brunothg.swing.mvp.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.brunothg.swing.mvp.BaseSpringTest;
import com.github.brunothg.swing.mvp.presenter.TestPresenter;

public class PresenterTest extends BaseSpringTest
{

	@Autowired
	private TestPresenter presenter;

	@Test
	public void createTest() throws Exception
	{
		// Do nothing just create
	}

	@Test
	public void getViewNameTest() throws Exception
	{
		String viewName = presenter.getViewName();
		assertEquals("com.github.brunothg.swing.mvp.view.TestView", viewName);
	}

}
