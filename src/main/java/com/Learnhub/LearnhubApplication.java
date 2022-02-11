package com.Learnhub;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.Learnhub.storage.StorageProperties;
import com.Learnhub.storage.StorageService;

@SpringBootApplication 
@EntityScan(basePackages = "com.Learnhub.entity")
@EnableConfigurationProperties(StorageProperties.class)
public class LearnhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnhubApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			//storageService.init();
		};
	}
	
}
