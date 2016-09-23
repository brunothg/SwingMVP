package com.github.brunothg.swing.mvp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.github.brunothg.swing.mvp.components.Presenter;
import com.github.brunothg.swing.mvp.components.View;

/**
 * Set the scope of {@link Presenter} and {@link View} to UI.
 * 
 * @author Marvin Bruns
 *
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface UiScope {
}