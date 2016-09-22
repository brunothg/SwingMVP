package com.github.brunothg.swing.mvp.event;

public interface Event
{
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
