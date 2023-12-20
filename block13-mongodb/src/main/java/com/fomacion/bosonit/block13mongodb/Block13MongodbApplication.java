package com.fomacion.bosonit.block13mongodb;

import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaMapper;
import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaDto;
import com.fomacion.bosonit.block13mongodb.entity.Persona;
import jakarta.annotation.PostConstruct;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@SpringBootApplication
@EnableMongoRepositories
public class Block13MongodbApplication {

	PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);

	public static void main(String[] args) {
		SpringApplication.run(Block13MongodbApplication.class, args);
	}

	@PostConstruct
	public void run (){
		Persona persona = new Persona("1", "usuario", "contraseña", "name",  "surname", "company@email.com", "personal@email.com", "city", true, new Date(), "imagenUrl", null);
		PersonaDto personaDto = mapper.personaToPersonaDto(persona);
		System.out.println("Objeto persona:");
		System.out.println(persona);
		System.out.println("Objeto personaDto");
		System.out.println(personaDto);
	}
}
