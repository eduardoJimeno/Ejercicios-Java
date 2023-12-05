package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Profesor;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorFeignClient implements MyFeign{

    @Autowired
    ProfesorRepository profesorRepository;
    @Override
    public ProfesorOutputDto getProfesorById(int id) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        return profesor.profesor_PersonaToProfesor_ProfesorOutputDto();
    }
}
