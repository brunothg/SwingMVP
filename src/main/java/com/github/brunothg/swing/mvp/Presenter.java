package com.github.brunothg.swing.mvp;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.brunothg.swing.mvp.event.EventBus;

/**
 * Base class for all presenters.
 * 
 * @author Marvin Bruns
 *
 * @param <V> The view this presenter is responsible for
 */
public class Presenter<V extends View>
{
	private static final Logger LOG = LoggerFactory.getLogger(Presenter.class);

	@Autowired
	private EventBus eventBus;

	@Autowired
	private SpringViewProvider viewProvider;

	private String viewName = null;
	private V view = null;

	/**
	 * Initialization of this spring bean. For example it registers this bean as event listener.
	 */
	@PostConstruct
	protected void _init()
	{
		eventBus.subscribe(this);
	}

	/**
	 * Cleaning up this spring bean. For example it unregisters this bean as event listener.
	 */
	@PreDestroy
	private void _destroy()
	{
		eventBus.unsubscribe(this);
	}

	/**
	 * Get the name of the view this presenter is responsible for.
	 * 
	 * @return The view name of this presenter.
	 */
	public final String getViewName()
	{
		if (viewName != null)
		{
			return viewName;
		}

		Class<?> clazz = getClass();
		if (clazz.isAnnotationPresent(SwingPresenter.class))
		{
			SwingPresenter sp = clazz.getAnnotation(SwingPresenter.class);
			Class<?> viewType = null;
			try
			{
				viewType = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			}
			catch (ClassCastException e)
			{
				LOG.warn("Could not get generic view for [{}]", this.getClass().getCanonicalName());
			}
			viewName = MVP.getViewNameFromAnnotation(viewType, sp);
		}
		else
		{
			LOG.error("Presenter [{}] does not have a @SwingPresenter annotation!", clazz.getCanonicalName());
		}

		return viewName;
	}

	/**
	 * Hands back the View that this Presenter works with. A match is made if the ViewProvider finds
	 * a SwingView annotated View whose name matches Presenter's viewName
	 * 
	 * @return an implementor of {@link View}
	 */
	@SuppressWarnings("unchecked")
	public V getView()
	{
		if (view != null)
		{
			return view;
		}

		String viewName = getViewName();
		if (viewName != null)
		{
			View providedView = viewProvider.getView(viewName);
			try
			{
				view = (V) providedView;
				view.setEventBus(getEventBus());
			}
			catch (ClassCastException e)
			{
				view = null;
				LOG.error("No view found for [{}]", this.getClass().getCanonicalName());
			}
		}
		return view;
	}

	/**
	 * Get the {@link EventBus} of this presenter.
	 * 
	 * @return The {@link EventBus}
	 */
	protected EventBus getEventBus()
	{
		return eventBus;
	}
}
