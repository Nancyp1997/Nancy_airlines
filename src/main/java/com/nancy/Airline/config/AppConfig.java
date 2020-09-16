package com.nancy.Airline.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration

@ComponentScan("com.nancy.Airline")
public class AppConfig implements WebMvcConfigurer {
	
	// define bean for RestTemplate
	// 	this is used to make client REST calls
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}