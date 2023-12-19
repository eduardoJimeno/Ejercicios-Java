package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Block7CrudValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

	@Autowired
	PersonaRepository personaRepository;
	@PostConstruct
	public void db(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date createdDate, createdDate2;
		Date terminationDate;

		try {
			createdDate = dateFormat.parse("2000-01-01");
			createdDate2 = dateFormat.parse("2001-01-01");
			terminationDate = dateFormat.parse("2000-05-01");
		} catch (ParseException e) {
			// Manejar la excepción de análisis de fecha aquí si es necesario
			return;
		}

		personaRepository.save(new Persona(new PersonaInputDto("usuario", "password", "a", "apellidos", "company@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario2", "password2", "c", "apellidos2", "company2@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario3", "password3", "f", "apellidos3", "company3@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario4", "password4", "b", "apellidos4", "company4@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario5", "password5", "e", "apellidos5", "company5@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario6", "password6", "d", "apellidos6", "company6@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario7", "password7", "g", "apellidos7", "company7@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario8", "password8", "h", "apellidos8", "company8@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario9", "password", "a", "apellidos", "company9@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario10", "password2", "c", "apellidos2", "company10@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario11", "password3", "f", "apellidos3", "company11@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario12", "password4", "b", "apellidos4", "company12@email.com", "personal@email.com", "ciudad", true, createdDate, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario13", "password5", "e", "apellidos5", "company13@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario14", "password6", "d", "apellidos6", "company14@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario15", "password7", "g", "apellidos7", "company15@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario16", "password8", "h", "apellidos8", "company16@email.com", "personal@email.com", "ciudad", true, createdDate2, "image", terminationDate))).personaToPersonaOutputDto();
	}
}
