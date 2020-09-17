package com.nancy.Airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.nancy.Airline.entity"})
public class AirlineApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}
	

}
