package com.github.brunothg.swing.mvp.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBus
{
	private static final Logger LOG = LoggerFactory.getLogger(EventBus.class);

	private static long counter = 0;
	private com.google.common.eventbus.EventBus eventBus = new com.google.common.eventbus.EventBus(
		EventBus.class.getCanonicalName() + "" + (counter++));

	public void subscribe(Object subscriber)
	{
		eventBus.register(subscriber);
		LOG.info("New event subscriber [{}]", subscriber);
	}

	public void unsubscribe(Object subscriber)
	{
		eventBus.unregister(subscriber);
		LOG.info("Lost event subscriber [{}]", subscriber);
	}

	public void fire(Event ev)
	{
		LOG.info("Fire event [{}]->{}", ev.getSource(), ev);
		eventBus.post(ev);
	}

}