package com.bosonit.formacion.block7crudrelaciones.controller;

import com.bosonit.formacion.block7crudrelaciones.application.AsignaturaServicioImpl;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaInputDto;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaServicioImpl asignaturaServicio;

    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(
            @RequestBody AsignaturaInputDto asignaturaInputDto) {
            AsignaturaOutputDto asignaturaOutputDto = asignaturaServicio
                    .addAsignatura(asignaturaInputDto);
            return ResponseEntity.ok(asignaturaOutputDto);

    }

    @GetMapping("/estudiante/{estudiante_id}/asignaturas")
    public ResponseEntity<List<AsignaturaOutputDto>> getAsignaturasPorEstudiante(
            @PathVariable int estudiante_id) {
            List<AsignaturaOutputDto> asignaturas = asignaturaServicio
                    .getAsignaturasPorEstudiante(estudiante_id);
            return ResponseEntity.ok(asignaturas);
    }

    @DeleteMapping("/{id_asignatura}")
    public ResponseEntity<String> deleteAsignaturaById(@PathVariable int id_asignatura) {
            asignaturaServicio.deleteAsignaturaById(id_asignatura);
            return ResponseEntity.ok("Asignatura con id: " + id_asignatura + " borrada");
    }
    @PostMapping("/{asignaturaId}/addEstudiantes")
    public ResponseEntity<AsignaturaOutputDto> addEstudiantesToAsignatura(
            @PathVariable int asignaturaId,
            @RequestBody List<Integer> estudiantesIds) {
            AsignaturaOutputDto asignaturaOutputDto = null;
            for (Integer estudianteId : estudiantesIds) {
                asignaturaOutputDto = asignaturaServicio
                        .addEstudiantesToAsignatura(asignaturaId, estudianteId);
            }
            return ResponseEntity.ok(asignaturaOutputDto);
    }
    @PostMapping("/{asignaturaId}/removeEstudiantes")
    public ResponseEntity<AsignaturaOutputDto> removeEstudiantesToAsignatura(
            @PathVariable int asignaturaId,
            @RequestBody List<Integer> estudiantesIds) {
            AsignaturaOutputDto asignaturaOutputDto = null;
            for (Integer estudianteId : estudiantesIds) {
                asignaturaOutputDto = asignaturaServicio
                        .removeEstudiantesToAsignatura(asignaturaId, estudianteId);
            }
            return ResponseEntity.ok(asignaturaOutputDto);

    }
}
