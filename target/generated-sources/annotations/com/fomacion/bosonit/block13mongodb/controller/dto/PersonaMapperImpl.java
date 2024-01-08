package com.fomacion.bosonit.block13mongodb.controller.dto;

import com.fomacion.bosonit.block13mongodb.entity.Persona;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-20T09:31:31+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaDto personaToPersonaDto(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaDto personaDto = new PersonaDto();

        personaDto.setIdPersona( persona.getIdPersona() );
        personaDto.setUsuario( persona.getUsuario() );
        personaDto.setName( persona.getName() );
        personaDto.setSurname( persona.getSurname() );
        personaDto.setCompanyEmail( persona.getCompanyEmail() );
        personaDto.setPersonalEmail( persona.getPersonalEmail() );
        personaDto.setCity( persona.getCity() );
        personaDto.setActive( persona.getActive() );
        personaDto.setCreatedDate( persona.getCreatedDate() );
        personaDto.setImagenUrl( persona.getImagenUrl() );
        personaDto.setTerminationDate( persona.getTerminationDate() );

        return personaDto;
    }

    @Override
    public Persona personaDtoToPersona(PersonaDto personaDto) {
        if ( personaDto == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setIdPersona( personaDto.getIdPersona() );
        persona.setUsuario( personaDto.getUsuario() );
        persona.setName( personaDto.getName() );
        persona.setSurname( personaDto.getSurname() );
        persona.setCompanyEmail( personaDto.getCompanyEmail() );
        persona.setPersonalEmail( personaDto.getPersonalEmail() );
        persona.setCity( personaDto.getCity() );
        persona.setActive( personaDto.getActive() );
        persona.setCreatedDate( personaDto.getCreatedDate() );
        persona.setImagenUrl( personaDto.getImagenUrl() );
        persona.setTerminationDate( personaDto.getTerminationDate() );

        return persona;
    }
}
