package com.digitalsinage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.digitalsinage.properties.DevEnvProperties;

@SpringBootApplication
@EnableConfigurationProperties(DevEnvProperties.class)
public class AppStart {
	private static final Logger logger = LogManager.getLogger(AppStart.class);

	public static void main(String[] args) {
		logger.info("inside : main");
		SpringApplication.run(AppStart.class, args);
		logger.info("AppStart : start");
	}
}
