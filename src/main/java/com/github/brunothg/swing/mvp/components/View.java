package com.github.brunothg.swing.mvp.components;

import com.github.brunothg.swing.mvp.event.EventBus;

/**
 * A {@link Presenter}'s view
 * 
 * @author Marvin Bruns
 *
 */
public interface View
{
	/**
	 * Will be called by the presenter. The view will get it's {@link EventBus} on this way.
	 * 
	 * @param eventBus The {@link View}'s {@link EventBus}
	 */
	public void setEventBus(EventBus eventBus);
}