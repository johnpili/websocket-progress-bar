package com.johnpili.websocketprogressbar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Autowired
	public ApplicationContext applicationContext;

	@Bean
	public ObjectMapper getObjectMapper()
	{
		return new ObjectMapper();
	}

	@Bean
	public MultipartResolver multipartResolver()
	{
		return new StandardServletMultipartResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler(
				"/vendors/**").addResourceLocations(
				"classpath:/static/vendors/");
	}

	@Bean
	public LayoutDialect layoutDialect()
	{
		return new LayoutDialect();
	}
}
