package com.github.brunothg.swing.mvp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import com.github.brunothg.swing.mvp.components.Presenter;

/**
 * Mark a class as {@link Presenter}, so that MVP can calculate the presenter &lt;-&gt;
 * relationship.
 * 
 * @author Marvin Bruns
 *
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SwingPresenter {
	public String AUTO_CREATION_NAME = "#auto_creation_name#";

	/**
	 * A presenter will always be matched with a view in an application.
	 */
	String viewName() default AUTO_CREATION_NAME;
}