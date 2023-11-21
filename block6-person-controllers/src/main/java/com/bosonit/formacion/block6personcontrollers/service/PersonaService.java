package com.bosonit.formacion.block6personcontrollers.service;

import com.bosonit.formacion.block6personcontrollers.entities.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    private final List<Persona> personas = new ArrayList<>();
    public Persona crearPersona(String nombre, String poblacion, int edad){
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setPoblacion(poblacion);
        persona.setEdad(edad);

        personas.add(persona);
        return persona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

}
