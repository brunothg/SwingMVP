package com.github.brunothg.swing.mvp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.github.brunothg.swing.mvp.annotation.EnableSwingMVP;
import com.github.brunothg.swing.mvp.components.SpringViewProvider;

/**
 * Utility and Spring Configuration class for MVP. Use {@link EnableSwingMVP} for configuration.
 * 
 * @author Marvin Bruns
 *
 */
@Configuration
@ComponentScan
@PropertySource("classpath:/com/github/brunothg/swing/mvp/library.properties")
public class SwingMVP implements Aware, ApplicationContextAware
{
	private static Logger LOG = LoggerFactory.getLogger(SwingMVP.class);
	public static final String LOGO_ASCII_ART = "\r\n   _____            _                _____         _               __  ____      _______  \r\n  / ____|          (_)              / ____|       (_)             |  \\/  \\ \\    / /  __ \\ \r\n | (___  _ __  _ __ _ _ __   __ _  | (_____      ___ _ __   __ _  | \\  / |\\ \\  / /| |__) |\r\n  \\___ \\| \'_ \\| \'__| | \'_ \\ / _` |  \\___ \\ \\ /\\ / / | \'_ \\ / _` | | |\\/| | \\ \\/ / |  ___/ \r\n  ____) | |_) | |  | | | | | (_| |  ____) \\ V  V /| | | | | (_| | | |  | |  \\  /  | |     \r\n |_____/| .__/|_|  |_|_| |_|\\__, | |_____/ \\_/\\_/ |_|_| |_|\\__, | |_|  |_|   \\/   |_|     \r\n        | |                  __/ |                          __/ |                         \r\n        |_|                 |___/                          |___/                          ";

	private static String version = "N/A";
	private ApplicationContext applicationContext;

	@Value("${com.github.brunothg.swing.mvp.version}")
	private void setVersion(String version)
	{
		SwingMVP.version = version;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		LOG.debug("Got application context");
		this.applicationContext = applicationContext;
		initialize();
	}

	private void initialize()
	{
		printWelcomeMessage();

		ApplicationContext ctx = getApplicationContext();
		ctx.getBean(SpringViewProvider.class);
	}

	public ApplicationContext getApplicationContext()
	{
		return this.applicationContext;
	}

	public static void printWelcomeMessage()
	{
		String message = SwingMVP.LOGO_ASCII_ART;
		message += "\r\n Version - " + getVersion();
		LOG.info(message);
	}

	public static String getVersion()
	{
		return SwingMVP.version;
	}

}
