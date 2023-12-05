package com.bosonit.formacion.block7crudrelaciones.controller;

import com.bosonit.formacion.block7crudrelaciones.application.PersonaServicio;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServicio personaServicio;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto){
        return ResponseEntity.ok(personaServicio.addPersona(personaInputDto));
    }

    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaRolOutputDto> getPersonaById(
            @PathVariable int id_persona,
            @RequestParam(defaultValue = "false") boolean rol)
    {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona, rol));
    }
    @GetMapping("/nombre/{name}")
    public Iterable<PersonaRolOutputDto> getAllPersonasByNameLike(
            @PathVariable String nombre,
            @RequestParam(defaultValue = "false") boolean rol){

        return personaServicio.getPersonasByName(nombre, rol);
    }
    @GetMapping
    public Iterable<PersonaRolOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "false") boolean rol){

        return personaServicio.getAllPersonas(pageNumber, pageSize, rol);
    }

    @DeleteMapping("/{id_persona}")
    public void deletePersonaById(@PathVariable int id_persona) {
        personaServicio.deletePersonaById(id_persona);
    }

    @PutMapping("/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto personaInputDto) {

        return ResponseEntity.ok(personaServicio.updatePersona(personaInputDto));

    }

}
