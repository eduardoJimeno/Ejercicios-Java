package com.fomacion.bosonit.block13mongodb.application;

import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaDto;
import com.fomacion.bosonit.block13mongodb.entity.Persona;
import org.springframework.data.domain.Page;

public interface PersonaServicio {
    PersonaDto addPersona(Persona persona);
    PersonaDto getPersonaById(String idPersona);
    Page<PersonaDto> getAllPersonas(int page, int size);
    PersonaDto updatePersona(String idPersona, Persona persona);
    void deletePersona(String idPersona);

}
