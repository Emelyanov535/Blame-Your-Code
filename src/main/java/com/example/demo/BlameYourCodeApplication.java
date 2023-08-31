package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BlameYourCodeApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(BlameYourCodeApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BlameYourCodeApplication.class, args);
	}

}
