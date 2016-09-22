package com.github.brunothg.swing.mvp;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * {@link View} provider for spring applications.
 * 
 * @author Marvin Bruns
 *
 */
@Component
public class SpringViewProvider
{
	private static final Logger LOG = LoggerFactory.getLogger(SpringViewProvider.class);

	@Autowired
	private ApplicationContext applicationContext;

	// @Autowired
	// private BeanDefinitionRegistry beanDefinitionRegistry;

	private final Map<String, Set<String>> viewNameToBeanNamesMap = new ConcurrentHashMap<String, Set<String>>();

	/**
	 * Scan for views and build view -&gt; bean dictionary.
	 */
	@PostConstruct
	void init()
	{
		LOG.info("Looking up SpringViews");
		int count = 0;
		String[] viewBeanNames = applicationContext.getBeanNamesForAnnotation(SwingView.class);
		for (String beanName : viewBeanNames)
		{
			Class<?> type = applicationContext.getType(beanName);
			if (View.class.isAssignableFrom(type))
			{
				SwingView annotation = applicationContext.findAnnotationOnBean(beanName, SwingView.class);
				String viewName = MVP.getViewNameFromAnnotation(type, annotation);
				LOG.info("Found SpringView bean [{}] with name[{}] with view name [{}]", type.getCanonicalName(),
					beanName, viewName);
				if (applicationContext.isSingleton(beanName))
				{
					throw new IllegalStateException("SpringView bean [" + beanName + "] must not be a singleton");
				}
				Set<String> beanNames = viewNameToBeanNamesMap.get(viewName);
				if (beanNames == null)
				{
					beanNames = new ConcurrentSkipListSet<String>();
					viewNameToBeanNamesMap.put(viewName, beanNames);
				}

				if (beanNames.size() > 0)
				{
					LOG.warn("Multiple beans for view [{}]", viewName);
				}
				beanNames.add(beanName);
				count++;
			}
			else
			{
				LOG.error("The view bean [{}] with name [{}] does not implement View", type.getCanonicalName(),
					beanName);
				throw new IllegalStateException("SpringView bean [" + beanName + "] must implement View");
			}
		}
		LOG.info("{} SpringViews found", count);
	}

	/**
	 * Fetch view from application context.
	 * 
	 * @param viewName The view's name
	 * @param beanName Name of the spring bean for the specific view.
	 * @return The view or null
	 */
	private View getViewFromApplicationContext(String viewName, String beanName)
	{
		View view = null;

		view = getViewFromApplicationContextAndCheckAccess(beanName);

		return view;
	}

	/**
	 * Test application context access and fetch view.
	 * 
	 * @param beanName Name of the spring bean for the specific view.
	 * @return The view or null
	 */
	private View getViewFromApplicationContextAndCheckAccess(String beanName)
	{
		View view = null;

		try
		{
			view = (View) applicationContext.getBean(beanName);
		}
		catch (BeansException e)
		{
			LOG.error("View bean is not a view [{}]", beanName);
		}

		return view;
	}

	/**
	 * Get the view for a specific {@link View}'s name.
	 * 
	 * @param viewName The name of a view
	 * @return The view or null, if no view exists for this name.
	 */
	public View getView(String viewName)
	{
		final Set<String> beanNames = viewNameToBeanNamesMap.get(viewName);
		if (beanNames != null)
		{
			for (String beanName : beanNames)
			{
				return getViewFromApplicationContext(viewName, beanName);
			}
		}
		LOG.warn("No view found with name [{}]", viewName);
		return null;
	}

}