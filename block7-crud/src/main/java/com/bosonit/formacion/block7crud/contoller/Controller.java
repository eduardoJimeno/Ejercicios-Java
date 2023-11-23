package com.bosonit.formacion.block7crud.contoller;

import com.bosonit.formacion.block7crud.application.PersonaServiceImpl;
import com.bosonit.formacion.block7crud.contoller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.contoller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crud.exception.PersonaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) {
        return
                ResponseEntity.status(201).body(personaService.addPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public Iterable<PersonaOutputDto> getAllPersonasByNameLike(@PathVariable String nombre){
            return personaService.getAllPersonasByNombreLike(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id) {
        try {
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body("Persona con id: " + id + " borrada");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id,
            @RequestBody(required = false) PersonaInputDto persona) {

        try {
            PersonaOutputDto existingPersona = personaService.getPersonaById(id);

            if (existingPersona == null){
               throw new PersonaNotFoundException("No se encontró ninguna persona con el Id: " +id);
            }

            PersonaInputDto existingPersonaInput = new PersonaInputDto();
            existingPersonaInput.setId(existingPersona.getId());
            existingPersonaInput.setNombre(existingPersona.getNombre());
            existingPersonaInput.setPoblacion(existingPersona.getPoblacion());
            existingPersonaInput.setEdad(existingPersona.getEdad());
            if (persona.getNombre() != null) {
                existingPersonaInput.setNombre(persona.getNombre());
            }
            if (persona.getEdad() != 0){
                existingPersonaInput.setEdad(persona.getEdad());
            }
            if (persona.getPoblacion() != null) {
                existingPersonaInput.setPoblacion(persona.getPoblacion());
            }
            PersonaOutputDto updatePersona = personaService.updatePersona(existingPersonaInput);
            if(updatePersona != null){
                return ResponseEntity.ok().body(updatePersona);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}