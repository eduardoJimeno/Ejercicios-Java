package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;

public interface ProfesorServicio {
    ProfesorOutputDto addProfesorToPersona (ProfesorInputDto profesorInputDto);
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesor);
    Iterable<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize);
    void deleteProfesorById (int id);
    //void addEstudianteToProfesor (int id_estudiante, int id_profesor);
}
