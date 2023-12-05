package com.bosonit.formacion.block7crudrelaciones.application;


import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;
import org.springframework.web.bind.annotation.*;


public interface ProfesorServicio {
    @PostMapping
    ProfesorOutputDto addProfesorToPersona (ProfesorInputDto profesorInputDto);
    @PutMapping
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesorInputDto);
    @GetMapping
    Iterable<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize);
    @DeleteMapping
    void deleteProfesorById (int id);
}
