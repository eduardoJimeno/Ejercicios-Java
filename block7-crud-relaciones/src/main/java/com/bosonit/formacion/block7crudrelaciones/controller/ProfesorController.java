package com.bosonit.formacion.block7crudrelaciones.controller;

import com.bosonit.formacion.block7crudrelaciones.application.ProfesorFeignClient;
import com.bosonit.formacion.block7crudrelaciones.application.ProfesorServicio;

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
    ProfesorServicio profesorServicio;
    @Autowired
    ProfesorFeignClient profesorFeignClient;


    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addProfesorToPersona(@RequestBody ProfesorInputDto profesorInputDto) {
            ProfesorOutputDto profesorOutputDto = profesorServicio.addProfesorToPersona(profesorInputDto);
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
    public void deletePersonaById(@PathVariable int id_profesor) {
        profesorServicio.deleteProfesorById(id_profesor);
    }

    @PutMapping("/{id_profesor}")
    public ResponseEntity<ProfesorOutputDto> updateProfesor(
            @PathVariable int id_profesor,
            @RequestBody(required = false) ProfesorInputDto profesorInputDto) {
        return ResponseEntity.ok(profesorServicio.updateProfesor( profesorInputDto));
    }

    @GetMapping("/{id_profesor}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable int id_profesor)
    {
        return ResponseEntity.ok(profesorFeignClient.getProfesorById(id_profesor));
    }

}
