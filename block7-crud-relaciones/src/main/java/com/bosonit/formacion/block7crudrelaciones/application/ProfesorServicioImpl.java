package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Estudiante;
import com.bosonit.formacion.block7crudrelaciones.domain.Persona;
import com.bosonit.formacion.block7crudrelaciones.domain.Profesor;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.PersonaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServicioImpl implements ProfesorServicio{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Override
    public ProfesorOutputDto addProfesorToPersona(ProfesorInputDto profesorInputDto) {
        if (estudianteRepository.existsById(profesorInputDto.getId_persona())){
            throw new EntityNotFoundException("La persona ya está registrada como estudiante");
        } else {
            Persona persona = personaRepository
                    .findById(profesorInputDto.getId_persona())
                    .orElseThrow();

            Profesor profesor = new Profesor(profesorInputDto);
            profesor.setPersona(persona);
            return profesorRepository
                    .save(profesor)
                    .profesor_PersonaToProfesor_ProfesorOutputDto();
        }
    }

    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesor) {
        profesorRepository.findById(profesor.getId_profesor())
                .orElseThrow(()-> new EntityNotFoundException("No se encontró el profesor con Id: "+profesor.getId_profesor()));
        return profesorRepository.save(new Profesor(profesor))
                .profesor_PersonaToProfesor_ProfesorOutputDto();
    }

    @Override
    public Iterable<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::profesor_PersonaToProfesor_ProfesorOutputDto).toList();
    }

    @Override
    public void deleteProfesorById(int id) {
        profesorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No se encontró el profesor con Id: "+id));
        personaRepository.deleteById(id);
    }

   /* @Override
    public void addEstudianteToProfesor(int id_estudiante, int id_profesor) {
        Estudiante estudiante = estudianteRepository.findById(id_estudiante).orElseThrow();
        Profesor profesor = profesorRepository.findById(id_profesor).orElseThrow();
        profesor.getEstudiante().add(estudiante);
        profesorRepository.save(profesor);
    }*/
}
