package com.jartiste.stockvisionai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StockvisionaiApplication implements CommandLineRunner {


	@Value( "${jwt.secret-key}")
	private String jwtSecret;

	
	public static void main(String[] args) {
		SpringApplication.run(StockvisionaiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		log.info("JWT Secret : {}", jwtSecret);
	}


}
