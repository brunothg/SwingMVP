package com.github.brunothg.swing.mvp.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

/**
 * UI event bus. Actually only an wrapper for {@link com.google.common.eventbus.EventBus}. May
 * change in the future.
 * 
 * @author Marvin Bruns
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBus
{
	private static final Logger LOG = LoggerFactory.getLogger(EventBus.class);

	private static long counter = 0;
	private com.google.common.eventbus.EventBus eventBus = new com.google.common.eventbus.EventBus(
		EventBus.class.getCanonicalName() + "" + (counter++));

	/**
	 * Register an object's event handler methods. Use {@link Subscribe} to mark a method as an
	 * handler.
	 * 
	 * @param subscriber Listener, that wants to register
	 */
	public void subscribe(Object subscriber)
	{
		eventBus.register(subscriber);
		LOG.info("New event subscriber [{}]", subscriber);
	}

	/**
	 * Unsubscribe object's listener methods.
	 * 
	 * @see #subscribe(Object)
	 * @param subscriber
	 */
	public void unsubscribe(Object subscriber)
	{
		eventBus.unregister(subscriber);
		LOG.info("Lost event subscriber [{}]", subscriber);
	}

	/**
	 * Fire an event. All registered event handlers will be invoked.
	 * 
	 * @param ev The delivered event
	 */
	public void fire(Event ev)
	{
		LOG.info("Fire event [{}]->{}", ev.getSource(), ev);
		eventBus.post(ev);
	}

}