package com.github.brunothg.swing.mvp;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SwingView {
	public String AUTO_CREATION_NAME = "#auto_creation_name#";

	/**
	 * Matching specified name of presenter
	 */
	String name() default AUTO_CREATION_NAME;
}