package com.github.brunothg.swing.mvp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.brunothg.swing.mvp.MVP;

@Configuration
@ComponentScan
public class TestConfiguration
{
	private static final Logger LOG = LoggerFactory.getLogger(TestConfiguration.class);

	static
	{
		LOG.info(MVP.LOGO_ASCII_ART);
	}

}
