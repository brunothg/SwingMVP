package com.github.brunothg.swing.mvp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.github.brunothg.swing.mvp.SwingMVP;

/**
 * Configure Spring for MVP usage.
 * 
 * @author Marvin Bruns
 *
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Import(SwingMVP.class)
public @interface EnableSwingMVP {
}