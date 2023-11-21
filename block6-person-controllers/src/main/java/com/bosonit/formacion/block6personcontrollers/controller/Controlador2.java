package com.bosonit.formacion.block6personcontrollers.controller;

import com.bosonit.formacion.block6personcontrollers.entities.Persona;
import com.bosonit.formacion.block6personcontrollers.entities.Ciudad;
import com.bosonit.formacion.block6personcontrollers.service.CiudadServicio;
import com.bosonit.formacion.block6personcontrollers.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/controlador2")
public class Controlador2 {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private CiudadServicio ciudadServicio;

    @GetMapping(value = "/getPersona")
    public ResponseEntity<Persona> obtenerPersonaDeControlador1(){

        List<Persona> personas = personaService.getPersonas();

        if (!personas.isEmpty()){
            Persona persona = personas.get(personas.size()-1); //obtengo la última persona añadida a la lista
            persona.setEdad(persona.getEdad()*2);

            return ResponseEntity.ok(persona);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/getCiudades")
    public ResponseEntity<List<Ciudad>> obtenerListaCiudades(){
        List<Ciudad> ciudades = ciudadServicio.getCiudades();

        if(!ciudades.isEmpty()){
            return ResponseEntity.ok(ciudades);
        }
        return ResponseEntity.notFound().build();
    }
}
