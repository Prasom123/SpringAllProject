package com.heraizen.springiplstats.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModelConfig {
 
	@Bean 
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
