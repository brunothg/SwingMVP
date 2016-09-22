package com.github.brunothg.swing.mvp.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.brunothg.swing.mvp.BaseSpringTest;
import com.github.brunothg.swing.mvp.event.EventBus;
import com.github.brunothg.swing.mvp.event.PayloadEvent;
import com.google.common.eventbus.Subscribe;

public class EventBusTest extends BaseSpringTest
{
	private EventBus eventBus;

	@Before
	public void setUp()
	{
		eventBus = new EventBus();
		eventBus.subscribe(this);
	}

	@Test
	public void fireEventTest() throws Exception
	{
		PayloadEvent<Boolean> testEvent = new PayloadEvent<Boolean>(this, false);
		eventBus.fire(testEvent);

		assertTrue("Test if event got handled", testEvent.getPayload());
	}

	@Test
	public void unsubscribeTest() throws Exception
	{
		eventBus.unsubscribe(this);

		PayloadEvent<Boolean> testEvent = new PayloadEvent<Boolean>(this, false);
		eventBus.fire(testEvent);

		assertFalse("Test if event got handled", testEvent.getPayload());
	}

	@Subscribe
	public void eventHandler(PayloadEvent<Boolean> ev)
	{
		ev.setPayload(true);
	}

}
