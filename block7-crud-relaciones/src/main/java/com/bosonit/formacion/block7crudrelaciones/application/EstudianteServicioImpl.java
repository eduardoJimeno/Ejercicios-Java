package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.EstudianteInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.EstudianteSimpleOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Estudiante;
import com.bosonit.formacion.block7crudrelaciones.domain.Persona;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.PersonaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class EstudianteServicioImpl implements EstudianteServicio {
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public EstudianteSimpleOutputDto addEstudianteToPersona(EstudianteInputDto estudianteInputDto) {

        if (profesorRepository.existsById(estudianteInputDto.getId_persona()))
        {
            throw  new UnprocessableEntityException("La persona ya está registrada como profesor");
        }

        Persona persona = personaRepository
                .findById(estudianteInputDto.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con Id: " + estudianteInputDto.getId_persona()));
        Estudiante estudiante = new Estudiante(estudianteInputDto);
        estudiante.setPersona(persona);
        return estudianteRepository.save(estudiante).estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto();
    }

    @Override
    public EstudianteSimpleOutputDto getEstudianteToSimpleFullPersona(int id_estudiante, String outputType) {

        Estudiante estudiante = estudianteRepository
                .findById(id_estudiante)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante con ID " + id_estudiante + " no encontrado"));

        if ("simple".equals(outputType))
        {
            return estudiante.estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto();
        }
        if ("full".equals(outputType))
        {
            return estudiante.estudiante_FullPersonaToEstudiante_EstudianteOutPutDto();
        }
        throw new UnprocessableEntityException("Tipo de salida no válido");
        }

    @Override
    public Iterable<EstudianteSimpleOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return estudianteRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Estudiante::estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto).toList();

    }

    @Override
    public EstudianteSimpleOutputDto updateEstudiante(EstudianteInputDto estudianteInputDto) {
        estudianteRepository.findById(estudianteInputDto.getId_estudiante()).orElseThrow();
        return estudianteRepository.save(new Estudiante(estudianteInputDto)).estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto();
    }

    @Override
    public void deleteEstudianteById(int id) {
        estudianteRepository.findById(id).orElseThrow();
        estudianteRepository.deleteById(id);
    }


}

