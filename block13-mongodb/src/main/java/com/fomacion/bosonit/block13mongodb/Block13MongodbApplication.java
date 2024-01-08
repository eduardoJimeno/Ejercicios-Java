package com.fomacion.bosonit.block13mongodb;

import com.fomacion.bosonit.block13mongodb.application.PersonaServicio;
import com.fomacion.bosonit.block13mongodb.entity.Persona;
import com.fomacion.bosonit.block13mongodb.repository.PersonaRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@SpringBootApplication
@EnableMongoRepositories
public class Block13MongodbApplication implements CommandLineRunner {

	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	PersonaServicio personaServicio;

	public static void main(String[] args) {

		String connectionString = "mongodb+srv://wtrioja:Betagwe44@cluster0.nhqph3a.mongodb.net/?retryWrites=true&w=majority";

		MongoClient mongoClient = MongoDBSingleton.getMongoClient(connectionString);

		SpringApplication.run(Block13MongodbApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		personaRepository.deleteAll();

		personaServicio.addPersona(new Persona(null, "usuario", "password", "name", "surname", "company@email.com", "personal@email.com", "ciudad", true, new Date(), "imagenUrl", null));
		personaServicio.addPersona(new Persona(null, "usuario2", "password2", "name2", "surname2", "company2@email.com", "personal2@email.com", "ciudad2", true, new Date(), "imagenUrl2", null));
		personaServicio.addPersona(new Persona(null, "usuario3", "password3", "name3", "surname3", "company3@email.com", "personal3@email.com", "ciudad3", true, new Date(), "imagenUrl3", null));
		personaServicio.addPersona(new Persona(null, "usuario4", "password4", "name4", "surname4", "company4@email.com", "personal4@email.com", "ciudad4", true, new Date(), "imagenUrl4", null));
		personaServicio.addPersona(new Persona(null, "usuario5", "password5", "name5", "surname5", "company5@email.com", "personal5@email.com", "ciudad5", true, new Date(), "imagenUrl5", null));

	}
}
