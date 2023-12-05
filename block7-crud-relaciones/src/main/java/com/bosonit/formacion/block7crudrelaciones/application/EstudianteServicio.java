package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.*;
import org.springframework.web.bind.annotation.PathVariable;


public interface EstudianteServicio {

    EstudianteSimpleOutputDto addEstudianteToPersona (EstudianteInputDto estudianteInputDto);
    EstudianteSimpleOutputDto getEstudianteToSimpleFullPersona (int id_estudiante, String outputType);
    Iterable<EstudianteSimpleOutputDto> getAllStudents(int pageNumber, int pageSize);
    EstudianteSimpleOutputDto updateEstudiante(EstudianteInputDto estudianteInputDto);
    void deleteEstudianteById (int id);
}
