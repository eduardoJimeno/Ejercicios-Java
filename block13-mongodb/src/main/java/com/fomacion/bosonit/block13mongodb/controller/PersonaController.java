package com.fomacion.bosonit.block13mongodb.controller;

import com.fomacion.bosonit.block13mongodb.application.PersonaServicio;
import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaDto;
import com.fomacion.bosonit.block13mongodb.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServicio personaServicio;

    @PostMapping
    public ResponseEntity<PersonaDto> addPersona(@RequestBody Persona persona)
    {
        return ResponseEntity.ok(personaServicio.addPersona(persona));
    }

    @GetMapping("/{idPersona}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable String idPersona)
    {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(idPersona));
    }

    @GetMapping
    public ResponseEntity<List<PersonaDto>> getAllPersonas(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size)
    {
        List<PersonaDto> personas = personaServicio.getAllPersonas(page, size);
        return ResponseEntity.ok(personas);
    }

    @PutMapping("/{idPersona}")
    public ResponseEntity<PersonaDto> updatePersona(
            @PathVariable String idPersona,
            @RequestBody(required = false) Persona persona) {

        return ResponseEntity.ok(personaServicio.updatePersona(idPersona, persona));
    }

    @DeleteMapping("/{idPersona}")
    public void deletePersonaById(@PathVariable String idPersona) {
        personaServicio.deletePersona(idPersona);
    }
}
