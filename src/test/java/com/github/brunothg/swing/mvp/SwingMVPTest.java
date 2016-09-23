package com.github.brunothg.swing.mvp;

import static org.junit.Assert.assertEquals;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.brunothg.swing.mvp.annotation.SwingPresenter;
import com.github.brunothg.swing.mvp.annotation.SwingView;
import com.github.brunothg.swing.mvp.components.SpringViewProvider;
import com.github.brunothg.swing.mvp.presenter.TestPresenter;
import com.github.brunothg.swing.mvp.view.TestView;

public class SwingMVPTest extends BaseSpringTest
{

	@Test
	public void getViewNameFromViewTest() throws Exception
	{
		String viewName = SpringViewProvider.getViewNameFromAnnotation(TestView.class,
			TestView.class.getAnnotation(SwingView.class));
		assertEquals("com.github.brunothg.swing.mvp.view.TestView", viewName);
	}

	@Test
	public void getViewNameFromPresenterTest() throws Exception
	{
		String viewName = SpringViewProvider.getViewNameFromAnnotation(TestView.class,
			TestPresenter.class.getAnnotation(SwingPresenter.class));
		assertEquals("com.github.brunothg.swing.mvp.view.TestView", viewName);
	}

	//XXX Run for demo Application
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

		TestPresenter presenter = context.getBean(TestPresenter.class);
		presenter.show();

		context.close();
	}
}
