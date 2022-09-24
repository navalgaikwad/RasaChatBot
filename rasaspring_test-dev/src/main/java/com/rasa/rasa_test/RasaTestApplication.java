package com.rasa.rasa_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.rasa.rasa_test.service.Service;


@SpringBootApplication 
public class RasaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RasaTestApplication.class, args);
	}

}
