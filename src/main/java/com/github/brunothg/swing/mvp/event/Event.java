package com.github.brunothg.swing.mvp.event;

/**
 * Event type for sending over {@link EventBus}
 * 
 * @author Marvin Bruns
 * 
 *
 */
public interface Event
{
	/**
	 * Get the source of this event.
	 * 
	 * @return Source of this event
	 */
	public Object getSource();

	public static abstract class AbstractEvent implements Event
	{
		private Object source;

		public AbstractEvent(Object source)
		{
			this.source = source;
		}

		@Override
		public Object getSource()
		{
			return this.source;
		}

	}
}
