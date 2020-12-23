package com.heraizen.springftlexample.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

@Configuration
public class AppConfig {

	@Bean
	public FreeMarkerConfigurationFactory freeMarkerConfiguration() {
		FreeMarkerConfigurationFactory freeMarkerConfigurationFactory = new FreeMarkerConfigurationFactory();
		freeMarkerConfigurationFactory.setTemplateLoaderPath("classpath:/templates");
		freeMarkerConfigurationFactory.setDefaultEncoding("UTF-8");
		return freeMarkerConfigurationFactory;
	}
	
	@Bean
	public ModelMapper modalMapper() {
		return new ModelMapper();
	}
}
