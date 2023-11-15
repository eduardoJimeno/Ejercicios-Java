package com.bosonit.formacion.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;

@SpringBootApplication
public class Block5CommandLineRunnerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
		System.out.println("Hello World");
	}
	@PostConstruct
	public void primera(){
		System.out.println("Hola desde la clase inicial");
	}

	CommandLineRunner segunda()
	{
		return p ->
				System.out.println("Hola desde la clase secundaria");
	}

	CommandLineRunner tercera()
	{
		return p ->
				System.out.println("Soy la tercera clase" + Arrays.toString(p));

	}
	@Override
	public void run(String... args) throws Exception {
		segunda().run(args);
		tercera().run(args);
	};
}
/** Primero se ejecuta @PostConstruct ya que como Init nos permite crear Beans que ejecuten tareas
 * antes de que el contenedor cargue todas las propiedades que necesita. Vemos como se imprime incluso
 * antes de cargar el servidor embebido.
 *
 * Posteriormente, después de cargar el contexto de la aplicación, Spring Boot llamará automáticamente
 * a la interfaz CommandLineRunner que tiene un método de ejecución (@FunctionalInterface). Las llamará
 * por orden dentro de nuestro código, imprimiendo antes la tercera si la hemos definido antes.
 * La usamos para ejecutar cierta lógica de negocio justo después de arrancar nuestra aplicación
 */