package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Asignatura;

import java.util.List;

public interface AsignaturaServicio {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);
    AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignaturaInputDto);
    void deleteAsignaturaById (int id);
    List<AsignaturaOutputDto> getAsignaturasPorEstudiante(int estudiante_id);
    AsignaturaOutputDto addEstudiantesToAsignatura(int asignatura_id, int estudiante_id);
    AsignaturaOutputDto removeEstudiantesToAsignatura(int asignatura_id, int estudiante_id);
}
