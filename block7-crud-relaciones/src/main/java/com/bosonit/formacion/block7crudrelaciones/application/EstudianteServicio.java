package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.*;


public interface EstudianteServicio {

    EstudianteSimpleOutputDto addEstudianteToPersona (EstudianteInputDto estudianteInputDto);
    Object getEstudianteToSimpleFullPersona (int id_estudiante, String outputType);
    Iterable<EstudianteSimpleOutputDto> getAllStudents(int pageNumber, int pageSize);
    EstudianteSimpleOutputDto updateEstudiante(EstudianteInputDto estudiante);
    void deleteEstudianteById (int id);
}
