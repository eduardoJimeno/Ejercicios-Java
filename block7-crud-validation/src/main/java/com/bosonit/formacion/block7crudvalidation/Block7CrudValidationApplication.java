package com.bosonit.formacion.block7crudvalidation;

<<<<<<< HEAD
=======
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.security.JWTAuthorizationFilter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> bfb929555f864b1d9cda33f4baa9b7c4b4f1dc7d
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

<<<<<<< HEAD
=======
import java.util.Date;

>>>>>>> bfb929555f864b1d9cda33f4baa9b7c4b4f1dc7d
@SpringBootApplication
public class Block7CrudValidationApplication {

	public static void main(String[] args) {

		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

<<<<<<< HEAD
=======
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig {

		@Bean
		SecurityFilterChain web(HttpSecurity http) throws Exception {
			http
					.csrf(csrf -> csrf.ignoringRequestMatchers(
							new AntPathRequestMatcher("/login")))
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeHttpRequests(authorize -> authorize
							.requestMatchers("/login").permitAll()
							.requestMatchers(HttpMethod.GET).hasAnyRole("ADMIN", "USER")
							.requestMatchers(HttpMethod.POST, "/login").permitAll()
							.requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
							.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
							.anyRequest().authenticated());

			return http.build();
		}
	}

	@Autowired
	PersonaRepository personaRepository;
	@PostConstruct
	public void db(){

		personaRepository.save(new Persona(new PersonaInputDto("usuario", "password", "a", "apellidos", "company@email.com", "personal@email.com", "ciudad", true, true, new Date(), "image", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario2", "password2", "c", "apellidos2", "company2@email.com", "personal2@email.com", "ciudad2", false, true, new Date(), "image2", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario3", "password3", "f", "apellidos3", "company3@email.com", "personal3@email.com", "ciudad3", false, true, new Date(), "image3", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario4", "password4", "b", "apellidos4", "company4@email.com", "personal4@email.com", "ciudad4", false, true, new Date(), "image4", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario5", "password5", "e", "apellidos5", "company5@email.com", "personal5@email.com", "ciudad5", false, true, new Date(), "image5", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario6", "password6", "d", "apellidos6", "company6@email.com", "personal6@email.com", "ciudad6", false, true, new Date(), "image6", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario7", "password7", "g", "apellidos7", "company7@email.com", "personal7@email.com", "ciudad7", false,true, new Date(), "image7", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario8", "password8", "h", "apellidos8", "company8@email.com", "personal8@email.com", "ciudad8", false,true, new Date(), "image8", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario9", "password", "a", "apellidos9", "company9@email.com", "personal9@email.com", "ciudad9", false,true, new Date(), "image9", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario10", "password2", "c", "apellidos10", "company10@email.com", "personal10@email.com", "ciudad10", false,true, new Date(), "image10", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario11", "password3", "f", "apellidos11", "company11@email.com", "personal11@email.com", "ciudad11", false,true, new Date(), "image11", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario12", "password4", "b", "apellidos12", "company12@email.com", "personal12@email.com", "ciudad12", false,true, new Date(), "image12", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario13", "password5", "e", "apellidos13", "company13@email.com", "personal13@email.com", "ciudad13", false,true, new Date(), "image13", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario14", "password6", "d", "apellidos14", "company14@email.com", "personal14@email.com", "ciudad14", false,true, new Date(), "image14", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario15", "password7", "g", "apellidos15", "company15@email.com", "personal15@email.com", "ciudad15", false,true, new Date(), "image15", null))).personaToPersonaOutputDto();
		personaRepository.save(new Persona(new PersonaInputDto("usuario16", "password8", "h", "apellidos16", "company16@email.com", "personal16@email.com", "ciudad16", false,true, new Date(), "image16", null))).personaToPersonaOutputDto();
	}
>>>>>>> bfb929555f864b1d9cda33f4baa9b7c4b4f1dc7d
}
