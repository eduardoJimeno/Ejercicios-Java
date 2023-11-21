package com.bosonit.formacion.block6personcontrollers.controller;

import com.bosonit.formacion.block6personcontrollers.entities.Ciudad;
import com.bosonit.formacion.block6personcontrollers.entities.Persona;
import com.bosonit.formacion.block6personcontrollers.service.CiudadServicio;
import com.bosonit.formacion.block6personcontrollers.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/controlador1")
public class Controlador1 {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private CiudadServicio ciudadServicio;

    @GetMapping(value = "/addPersona" )
    public Persona addPersona(
            @RequestHeader("nombre") String nombre,
            @RequestHeader("poblacion") String poblacion,
            @RequestHeader("edad") int edad)
    {
        return  personaService.crearPersona(nombre, poblacion, edad);
    }

    @PostMapping(value = "/addCiudad")
    public Ciudad addCiudad(
            @RequestParam("nombre") String nombre,
            @RequestParam("numeroHabitantes") int numeroHabitantes){
        return ciudadServicio.crearCiudad(nombre, numeroHabitantes);
    }
}
