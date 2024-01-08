package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServicio;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServicio personaServicio;


    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto) {
        return ResponseEntity.ok().body(personaServicio.addPersona(personaInputDto));
    }

    @GetMapping("persona/{id_persona}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id_persona) {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona));
    }

    @GetMapping("persona/nombre/{name}")
    public Iterable<PersonaOutputDto> getAllPersonasByNameLike(@PathVariable String nombre) {
        Iterable<PersonaOutputDto> personas = personaServicio.getPersonasByName(nombre);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No se encontraron personas con el nombre: " + nombre);
        }
        return personas;
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping({"/getall", "persona"})
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        Iterable<PersonaOutputDto> personas = personaServicio.getAllPersonas(pageNumber, pageSize);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No hay personas registradas.");
        }
        return personaServicio.getAllPersonas(pageNumber, pageSize);
    }

    @DeleteMapping("persona/{id_persona}")
    public void deletePersonaById(@PathVariable int id_persona) {
        personaServicio.deletePersonaById(id_persona);
    }

    @PutMapping("persona/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto personaInputDto) {

        return ResponseEntity.ok(personaServicio.updatePersona(personaInputDto));
    }




}