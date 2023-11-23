package com.bosonit.formacion.block7crud.application;

import com.bosonit.formacion.block7crud.contoller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.contoller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crud.domain.Persona;
import com.bosonit.formacion.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow()
                .personaToPersonaOutputDto();
    }
    @Override
    public Iterable<PersonaOutputDto> getAllPersonasByNombreLike(String nombre) {
        return personaRepository.findByNombreLike("%" + nombre + "%").stream()
                .map(Persona::personaToPersonaOutputDto)
                .toList();
    }

    @Override
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona) {
        personaRepository.findById(persona.getId()).orElseThrow();
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }
}
