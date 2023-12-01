package com.bosonit.formacion.block7crudrelaciones.controller;

import com.bosonit.formacion.block7crudrelaciones.application.AsignaturaServicio;
import com.bosonit.formacion.block7crudrelaciones.application.EstudianteServicioImpl;
import com.bosonit.formacion.block7crudrelaciones.application.PersonaServicio;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.EstudianteInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.EstudianteSimpleOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Estudiante;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteServicioImpl estudianteServicio;
    @Autowired
    AsignaturaServicio asignaturaServicio;

    @PostMapping
    public ResponseEntity<EstudianteSimpleOutputDto> addEstudianteToPersona(
            @RequestBody EstudianteInputDto estudianteInputDto){
            return ResponseEntity.status(200)
                    .body(estudianteServicio.addEstudianteToPersona(estudianteInputDto));
    }

    @GetMapping("/{id_estudiante}")
    public ResponseEntity<?> estudianteOutputDto(
            @RequestParam(value = "outputType", defaultValue = "simple") String outputType,
            @PathVariable int id_estudiante){
        return ResponseEntity.ok(estudianteServicio
                .getEstudianteToSimpleFullPersona(id_estudiante, outputType));
    }

    @GetMapping
    public Iterable<EstudianteSimpleOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return estudianteServicio.getAllStudents(pageNumber, pageSize);
    }

    @GetMapping("{id_estudiante}/asignaturas")
    public ResponseEntity<List<AsignaturaOutputDto>> getAsignaturasPorEstudiante(@PathVariable int id_estudiante) {
            List<AsignaturaOutputDto> asignaturas = asignaturaServicio
                    .getAsignaturasPorEstudiante(id_estudiante);
            if (asignaturas.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron asignaturas para el estudiante con ID: " + id_estudiante);
            }
            return ResponseEntity.ok(asignaturas);

    }

    @DeleteMapping("/{id_estudiante}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id_estudiante) {
            estudianteServicio.deleteEstudianteById(id_estudiante);
            return ResponseEntity.ok().body("Estudiante con id: " + id_estudiante + " borrado");
    }

}