package com.github.brunothg.swing.mvp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import com.github.brunothg.swing.mvp.components.View;

/**
 * Mark a class as {@link View}, so that MVP can calculate the presenter &lt;-&gt; relationship.
 * 
 * @author Marvin Bruns
 *
 */
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