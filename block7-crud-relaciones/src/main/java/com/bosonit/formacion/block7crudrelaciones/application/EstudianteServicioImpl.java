package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.EstudianteInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.EstudianteSimpleOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Estudiante;
import com.bosonit.formacion.block7crudrelaciones.domain.Persona;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudrelaciones.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.PersonaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    AsignaturaRepository asignaturaRepository;
    //private static final Logger logger = (Logger) LoggerFactory.getLogger(EstudianteServicioImpl.class);

    @Override
    public EstudianteSimpleOutputDto addEstudianteToPersona(EstudianteInputDto estudianteInputDto) {
        //logger.info("Buscando persona con ID: {}", estudianteInputDto.getId_persona());
        if (profesorRepository.existsById(estudianteInputDto.getId_persona())) {
            throw  new UnprocessableEntityException("La persona ya está registrada como profesor");
        } else {
            Persona persona = personaRepository
                    .findById(estudianteInputDto.getId_persona())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con Id: " + estudianteInputDto.getId_persona()));
            Estudiante estudiante = new Estudiante(estudianteInputDto);
            estudiante.setPersona(persona);
            return estudianteRepository
                    .save(estudiante)
                    .estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto();
        }
    }

    @Override
    public Object getEstudianteToSimpleFullPersona(int id_estudiante, String outputType) {

        Estudiante estudiante = estudianteRepository
                .findById(id_estudiante)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante con ID " + id_estudiante + " no encontrado"));

        if ("simple".equals(outputType)) {
            return estudiante.estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto();
        } else if ("full".equals(outputType)) {
            return estudiante.estudiante_FullPersonaToEstudiante_EstudianteOutPutDto();
        } else {
            throw new UnprocessableEntityException("Tipo de salida no válido");
        }
    }

    @Override
    public Iterable<EstudianteSimpleOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return estudianteRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Estudiante::estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto).toList();

    }

    @Override
    public EstudianteSimpleOutputDto updateEstudiante(EstudianteInputDto estudiante) {
        estudianteRepository.findById(estudiante.getId_estudiante())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con Id: " + estudiante.getId_estudiante()));
        return estudianteRepository.save(new Estudiante(estudiante))
                .estudiante_SimplePersonaToEstudiante_EstudianteOutPutDto();
    }

    @Override
    public void deleteEstudianteById(int id) {
        estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con Id: " + id));
        estudianteRepository.deleteById(id);
    }

}

