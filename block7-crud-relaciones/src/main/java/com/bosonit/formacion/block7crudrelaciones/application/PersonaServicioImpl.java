package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Persona;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.PersonaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonaServicioImpl implements PersonaServicio{
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }

    @Override
    public PersonaRolOutputDto getPersonaById(int id_persona, boolean rol) {
        Persona persona = personaRepository.findById(id_persona)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con Id: " + id_persona));

        PersonaRolOutputDto personaRolOutputDto = persona.personaToPersonaRolOutputDto();
        if (rol) {
            if (estudianteRepository.existsById(persona.getId_persona())) {
                personaRolOutputDto.setRol("Estudiante");
            } else if (profesorRepository.existsById(persona.getId_persona())) {
                personaRolOutputDto.setRol("Profesor");
            } else {
                personaRolOutputDto.setRol("Ninguno");
            }
        }
        return personaRolOutputDto;
    }
    @Override
    public Iterable<PersonaRolOutputDto> getPersonasByName(String nombre, boolean rol) {
        return personaRepository.findByNameLike("%" + nombre + "%").stream()
                .map(persona -> {
                    PersonaRolOutputDto personaRolOutputDto = persona.personaToPersonaRolOutputDto();
                    if (rol) {
                        if (estudianteRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Estudiante");
                        } else if (profesorRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Profesor");
                        } else {
                            personaRolOutputDto.setRol("Ninguno");
                        }
                    }
                    return personaRolOutputDto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public Iterable<PersonaRolOutputDto> getAllPersonas(int pageNumber, int pageSize, boolean rol) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> {
                    PersonaRolOutputDto personaRolOutputDto = persona.personaToPersonaRolOutputDto();
                    if (rol) {
                        if (estudianteRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Estudiante");
                        } else if (profesorRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Profesor");
                        } else {
                            personaRolOutputDto.setRol("Ninguno");
                        }
                    }
                    return personaRolOutputDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona) {
        personaRepository.findById(persona.getId_persona())
                .orElseThrow(()-> new EntityNotFoundException("No se encontró la persona con Id: "+persona.getId_persona()));
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(int idPersona) {
        if (estudianteRepository.existsByPersonaId(idPersona) || profesorRepository.existsByPersonaId(idPersona)){
            throw new IllegalStateException("No se puede eliminar la persona ya que tiene roles de estudiante o profesor asociados.");
        }
        personaRepository.findById(idPersona)
                .orElseThrow(()-> new EntityNotFoundException("No se encontró la persona con Id: "+idPersona));
        personaRepository.deleteById(idPersona);
    }
}
