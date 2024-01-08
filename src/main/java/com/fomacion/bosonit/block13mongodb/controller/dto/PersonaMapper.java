package com.fomacion.bosonit.block13mongodb.controller.dto;

import com.fomacion.bosonit.block13mongodb.entity.Persona;
import org.mapstruct.Mapper;


@Mapper
public interface PersonaMapper {
    PersonaDto personaToPersonaDto(Persona persona);
    Persona personaDtoToPersona(PersonaDto personaDto);
}
