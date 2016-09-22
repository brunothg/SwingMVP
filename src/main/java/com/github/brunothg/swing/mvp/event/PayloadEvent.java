package com.github.brunothg.swing.mvp.event;

import com.github.brunothg.swing.mvp.event.Event.AbstractEvent;

public class PayloadEvent<P> extends AbstractEvent
{
	private P payload;

	public PayloadEvent(Object source, P payload)
	{
		super(source);
		this.setPayload(payload);
	}

	public P getPayload()
	{
		return payload;
	}

	public void setPayload(P payload)
	{
		this.payload = payload;
	}

}
