package com.inani.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class InaniBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(InaniBankApplication.class, args);
	}
}