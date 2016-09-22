package com.github.brunothg.swing.mvp;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

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