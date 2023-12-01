package com.bosonit.formacion.block7crudrelaciones.controller;

import com.bosonit.formacion.block7crudrelaciones.application.ProfesorServicioImpl;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorServicioImpl profesorServicio;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addProfesorToPersona(
            @RequestBody ProfesorInputDto profesorInputDto) {
            ProfesorOutputDto profesorOutputDto = profesorServicio
                    .addProfesorToPersona(profesorInputDto);
            return ResponseEntity.ok(profesorOutputDto);
    }

    @GetMapping
    public Iterable<ProfesorOutputDto> getAllProfesores(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize){
        Iterable<ProfesorOutputDto> profesores = profesorServicio.getAllProfesores(pageNumber, pageSize);

        if (!profesores.iterator().hasNext()) {
            throw new EntityNotFoundException("No hay profesores registradas.");
        }
        return profesores;
    }

    @DeleteMapping("/{id_profesor}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id_profesor) {
        profesorServicio.deleteProfesorById(id_profesor);
        return ResponseEntity.ok().body("Persona con id: " + id_profesor + " borrada");
    }

    //Falta update
}
