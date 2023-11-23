package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServicioImpl;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.exceptions.LanzarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServicioImpl personaServicio;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona){
        if (persona.getUsuario() == null) {throw new LanzarException("El nombre de usuario no puede ser nulo"); }
        if (persona.getUsuario().length() > 10) {throw new LanzarException("La nombre de usuario no puede ser superior a 10 caracteres."); }
        if (persona.getUsuario().length() < 6) {throw new LanzarException("La nombre de usuario no puede ser inferior a 6 caracteres."); }
        if (persona.getPassword() == null) {throw new LanzarException("La contraseña no puede estar vacía"); }
        if (persona.getCompany_email() == null) {throw new LanzarException("Escriba el email de su compañía"); }
        if (persona.getCity() == null) {throw new LanzarException("El campo ciudad no puede ser nulo"); }
        if (persona.getCreated_date() == null) {throw new LanzarException("El campo de fecha de creación no puede ser nulo");}
        return ResponseEntity.status(200).body(personaServicio.addPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id)
    {
    try {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id));
    } catch (Exception e){
        return ResponseEntity.notFound().build();
    }
    }
    @GetMapping("/nombre/{name}")
    public Iterable<PersonaOutputDto> getAllPersonasByNameLike(@PathVariable String nombre){
        return personaServicio.getPersonasByName(nombre);
    }
    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize){
        return personaServicio.getAllPersonas(pageNumber, pageSize);
    }
}
