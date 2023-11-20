package com.bosonit.formacion.block5profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Block5ProfileApplication implements CommandLineRunner {

	private final AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfileApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("My app name: {}. Url: {}", appConfig.getName(), appConfig.getUrl());

	}
}
